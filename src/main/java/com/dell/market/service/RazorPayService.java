package com.dell.market.service;


import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.dell.market.dto.RequestDto;
import com.dell.market.ApiException;
//import com.dell.market.entity.Customer;
import com.dell.market.dto.Customer;
import com.dell.market.entity.ErrorOutResponse;
//import com.dell.market.entity.Notes;
//import com.dell.market.entity.Notify;
import com.dell.market.dto.Notes;
import com.dell.market.dto.Notify;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.entity.db2.CustomerEntity; 
import com.dell.market.entity.response.PaymentResponse;
import com.dell.market.repository.db1.RazorPayRepo;
import com.dell.market.repository.db2.CustomerMongoRepo;
import com.dell.market.util.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class RazorPayService {
	
	Logger log=LoggerFactory.getLogger(RazorPayService.class);

	private static final String SAMPLE_SERVICE = "PayAPI";
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private RazorPayRepo razorPayRepo;
	
	@Autowired
	private CustomerMongoRepo mongoRepo;
		
	@Value("${payment.key_id}")
	private String key;
	
	@Value("${payment.key_secret}")
	private String secret;
	
	@Value("${retail.base_url}")
	private String baseURL;
	
	@Value("${retail.endpoint}")
	private String endpoint;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RequestDto requestdto=null;
	
	@Autowired
	ResponseMessage message;
	
	//PayloadDTO payloaddto;
	
	ErrorOutResponse error;
	
	ErrorResponse errornew;
	
	 String in=null;
	
	//CompletableFuture<Object> com=null;
	CompletableFuture<ResponseEntity<ResponseMessage>> con=null;
	
	Object obj=null;
	
	String vuuid=null;
	
	@CircuitBreaker(name=SAMPLE_SERVICE,fallbackMethod="fallbackRazorAPIResponse")
	@TimeLimiter(name=SAMPLE_SERVICE)	
	public CompletableFuture<ResponseEntity<ResponseMessage>> createOrder(PaymentRequest req,HttpHeaders headers) throws Exception
	{
		return CompletableFuture.supplyAsync(() -> {
			 ResponseEntity<ResponseMessage> rp=null;
			 try {
				 rp=createOrderProcess(req,headers);
			 } catch (Exception e) {
				log.error("Exception occurred in inner RazorPayService.createOrder method : "+e.getMessage());
				throw new RuntimeException(e);
			 }
			 return rp;
	    });
			
	}

	public String genericMethodMask(PaymentRequest req) {
		Gson gson = new Gson();
        // Convert the JSON string to a Java object
		String in = gson.toJson(req);
		//in = in.replaceAll("(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")", "****");
       // in = in.replaceAll("(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":\")[^\"]+?(?=\")", "****");
		in = in.replaceAll("(?<=\"reference_id\":\")\\w+(?=\\w{4})", "*");
        in = in.replaceAll("(?<=\"expire_by\":)\\d+(?=(,|\\s|}))", "****");
       // in = in.replaceAll("(?<=creditCardNumber=)\\d+(?=(,|\\s|}))|(?<=\"creditCardNumber\":)\\d+(?=(,|\\s|}))", "****");
		return in;
	}

	@Async("asyncTaskExecutor")
	public RequestDto paymentRequestUpdateAsync(PaymentRequest req) throws ApiException {
		
	try {
		//CustomerEntity centity=callingMongoDB(req.getCustomer().getEmail());
		CustomerEntity centity=callingMongoDB(req.getCustomer().getEmail());
	    requestdto.setAmount(req.getAmount());
	    requestdto.setCurrency(req.getCurrency());
	    requestdto.setAccept_partial(req.isAccept_partial());
	    requestdto.setFirst_min_partial_amount(req.getFirst_min_partial_amount());
	    requestdto.setExpire_by(req.getExpire_by());
	    requestdto.setReference_id(req.getReference_id());
	    requestdto.setDescription(req.getDescription());  
		  
	    Customer customer=new Customer(centity.getName(),
	    		centity.getContact(),centity.getEmail()
	    		);
	    
	    requestdto.setCustomer(customer);
	    
	    requestdto.setReminder_enable(req.isReminder_enable());

        Notes note=new Notes(req.getNotes().getPolicy_name());
	    
	    requestdto.setNotes(note);
	    
	    Notify nt=new Notify(req.getNotify().isSms(),req.getNotify().isEmail());
	    
	    requestdto.setNotify(nt);

	    requestdto.setCallback_method(req.getCallback_method());
	    requestdto.setCallback_url(req.getCallback_url());
	    
		}catch(Exception e) {
			throw e;
		}    
	    return requestdto;
	}

	public RequestDto paymentRequestUpdateSync(PaymentRequest req) throws ApiException {
		
		//CustomerEntity centity=callingMongoDB(req.getCustomer().getEmail());
	  try {
		CustomerEntity centity=callingMongoDB(req.getCustomer().getEmail());
	    requestdto.setAmount(req.getAmount());
	    requestdto.setCurrency(req.getCurrency());
	    requestdto.setAccept_partial(req.isAccept_partial());
	    requestdto.setFirst_min_partial_amount(req.getFirst_min_partial_amount());
	    requestdto.setExpire_by(req.getExpire_by());
	    requestdto.setReference_id(req.getReference_id());
	    requestdto.setDescription(req.getDescription());  	
	  
	    Customer customer=new Customer(centity.getName(),
	    		centity.getContact(),centity.getEmail()
	    		);
	    
	    requestdto.setCustomer(customer);
	    
	    requestdto.setReminder_enable(req.isReminder_enable());

        Notes note=new Notes(req.getNotes().getPolicy_name());
	    
	    requestdto.setNotes(note);
	    
	    Notify nt=new Notify(req.getNotify().isSms(),req.getNotify().isEmail());
	    
	    requestdto.setNotify(nt);

	    requestdto.setCallback_method(req.getCallback_method());
	    requestdto.setCallback_url(req.getCallback_url());
	  }catch(Exception e) {
		  throw e;
	  }
     return requestdto;
	}

	public CustomerEntity callingMongoDB(String email){
		Optional<CustomerEntity> entity=null;
		  try {
			  if(email!=null) {
				   log.info("MongoDB Call is succcessfully completed..");
				   entity=Optional.ofNullable(this.mongoRepo.findByEmail(email));
				   log.info("Mongo DB Request Received at Mongo Layer :"+entity);
			  }else {
				  ErrorResponse error=new ErrorResponse("500","Internal Server Error", "Email id is null, cannot fetch data from MongoDB");
				  log.error("Exception occurred while fetching data to Mongo database : "+error.getDescription());
				  throw new NullPointerException(error.getDescription());
			  }
			}
			catch(Exception e)
			{
				log.error("Error occurred while fetching data to Mongo database : "+e.getMessage());
				throw e;
			}
		  return entity.get();
	}

	@Transactional
	public PaymentRequest callingdb(PaymentRequest req) {
		
		try {
		   log.info("DB Call is succcessfully completed..");
		   req=this.razorPayRepo.save(req);
		}
		catch(Exception e)
		{
			log.error("Error occurred while saving data to H2 database : "+e.getMessage());
		}	
		return req;
	}
	
	public CompletableFuture<ResponseEntity<?>> fallbackANDTimeRazorAPIResponse(Exception e) throws ApiException
	{
		log.error("Timeout observed while connecting to backed at service layer :"+message);
		//return CompletableFuture.supplyAsync(()->error);
		return CompletableFuture.supplyAsync(()->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Timed out"));		
	}

	CompletableFuture<ResponseEntity<ResponseMessage>> fallbackRazorAPIResponse(Throwable t) throws Throwable
	{
		ErrorResponse error=new ErrorResponse("500","Internal Server Error", "Server is unavailable or Server is taking too much time to respond");
		message.setStatus(error.getStatus());
		message.setMessage(error.getType());
		message.setUuid(vuuid);
		message.setDescription(error.getDescription());
		log.error("Timeout observed while connecting to backend at service layer :"+message);
		//throw new ApiException(new ErrorResponse("500","Internal Server Error", "Server is unavialbale"));
		//return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message));
		throw t;
	}
	
	CompletableFuture<ResponseEntity<ResponseMessage>> fallbackTimeRazorAPIResponse(PaymentRequest req,HttpHeaders headers,Throwable t) throws ApiException
	{
		
		ErrorResponse error=new ErrorResponse("500","Internal Server Error", "Server is taking too much time to respond");
		message.setStatus(error.getStatus());
		message.setMessage(error.getType());
		message.setUuid(vuuid);
		message.setDescription(error.getDescription());
		log.error("Timeout observed while connecting to backed at service layer :"+message);
		//return CompletableFuture.supplyAsync(()->error);
		//return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message));*/
		throw new ApiException(new ErrorResponse("500","Internal Server Error", "Server is taking too much time to respond"));		
	}
	
	private HttpHeaders getHeaders() {
		String auth = key + ":" + secret;				
		byte[] encodedAuth=Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		HttpHeaders  headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);	
		headers.set("Authorization", authHeader);		
		return headers;
	}
	
	
	public ResponseEntity<ResponseMessage> createOrderProcess(PaymentRequest req,HttpHeaders headers) throws Exception
	{
		try {
		  //  log.info("Backend url is: "+baseURL.concat(endpoint));
		    vuuid=headers.get("uuid").toString().replace("[","").replace("]","");
		   // System.out.println("Requested header::"+headers.get("uuid").toString());
		    log.info("Actual Request Received at service layer with Thread: "+Thread.currentThread().getName()+" : "+genericMethodMask(req));
		    if(req.getAmount()>500)
			{
				requestdto=paymentRequestUpdateSync(req);
			}else {
				requestdto=CompletableFuture.supplyAsync(() ->{
					RequestDto respo=null;
					try {
						respo=paymentRequestUpdateAsync(req);
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						log.error("Exception occurred in RazorPayService.createOrderProcess method for async call : "+e.getMessage());
					}
					return respo;
				}).get();
			}
		    	    
		    String val=String.valueOf(requestdto.getAmount());			    
		    if(!val.endsWith("9"))
		    {											   
			    log.info("Trimmed Request Received at success service layer :"+requestdto);				    					    
			    ResponseEntity<String> res=template.exchange(baseURL+""+endpoint,HttpMethod.POST,
						new HttpEntity<RequestDto>(this.requestdto,getHeaders()),String.class);
				log.debug("String Response Received at service layer :"+res.getBody());
			  	PaymentResponse re=mapper.readValue(res.getBody(), PaymentResponse.class);				  	
			  	//req.setExpire_by(re.getExpire_by());
			  	//req.getNotify().setSms(re.isWhatsapp_link());	
			  	if(re.getCustomer()!=null) {
				  	req.getCustomer().setContact(re.getCustomer().getContact());
				  	req.getCustomer().setName(re.getCustomer().getName());
			  	}
			  	//calling H2 Database
			  	//req=callingdb(req);		
			  	log.info("DB Populated data Received at service layer :"+callingdb(req));
				//Gson gson = new Gson();
	            // Convert the JSON string to a Java object
				//PaymentResponse re = gson.fromJson(res.getBody(), PaymentResponse.class);
				log.debug("Razor Response Received at service layer :"+res.getBody());
				message.setStatus("200");
				message.setMessage("Success");
				message.setUuid(vuuid);
				message.setDescription("Message is successfully published");
				log.info("Response Received at service layer :"+message);
				return ResponseEntity.status(HttpStatus.OK).body(message);	
		    }else {
		    	//throw new ApiException(new ErrorResponse("500","Internal Server Error", "Amount endinging with 9 not acceptable"));
		    	log.error("Amount endinging with 9 not acceptable");
				message.setStatus("400");
				message.setMessage("Bad Request");
				message.setUuid(vuuid);
				message.setDescription("Amount endinging with 9 not acceptable");
				log.info("Response Received at service layer :"+message);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}					
		  }catch(Exception ex) {
			  log.error("Actual Exception is identified : "+ex); 
			  if(ex.getMessage().contains("{")) {
					try {					
						 in=ex.getMessage().substring(ex.getMessage().indexOf("{"),ex.getMessage().length()-1);//82
						 log.error("Exception occurred in RazorPayService.createOrder method for response : "+in);
						// Gson gson = new Gson();
						if(in!=null) {
						  error = mapper.readValue(in, ErrorOutResponse.class);
						    message.setStatus("400");
							message.setMessage(error.getError().getCode());
							message.setUuid(vuuid);
							message.setDescription(error.getError().getDescription());
						  log.error("Error Response Received at service layer :"+message);
						  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
					  }	
					} catch (Exception  e) {
						log.error("DB Request Received at service layer :"+requestdto);
						log.error("Exception is occurred due to innner  database message :: "+e.getMessage());
						throw e;
					}
			   }	
			  log.error("Exception is occurred due to outer database message :: "+ex.getMessage());
			  throw ex;
		   }
	}
}
