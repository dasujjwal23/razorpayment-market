package com.dell.market.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.dell.market.entity.response.*;

import com.dell.market.dto.RequestDto;
import com.dell.market.entity.ErrorOutResponse;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.Customer;
import com.dell.market.entity.db1.Notes;
import com.dell.market.entity.db1.Notify;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.entity.db2.CustomerEntity;
import com.dell.market.entity.response.PaymentResponse;
import com.dell.market.util.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness =Strictness.LENIENT)
public class RazorPayServiceTest {
	
	@Spy
	@InjectMocks
	RazorPayService payService;
	
	@Mock
	RestTemplate template;
	

	@Mock
	ObjectMapper mapper;
	
	@Spy
	PaymentRequest req;
	
	@Spy
	Customer cos;
	
	@Spy
	Notify note;
	
	@Spy
	Notes no;

	@Spy
	Object obj;
	
	@Spy
	ResponseMessage message;
	
	@Spy
	ErrorResponse errornew;
	
	@Spy
	RequestDto requestdto;
	
	@Spy
	PaymentResponse re;
	
	@Spy
	HttpHeaders headers;
	
	@Spy
	ErrorOutResponse error;
	
	@Spy
	com.dell.market.entity.Error err;
	
	//@Value("${retail.base_url}")
	private String baseURL="http://test.com";
	
	//@Value("${retail.endpoint}")
	private String endpoint="/test";
	
	
	@Test
	public void createOrderFailure_400() throws Exception
	{
		PaymentRequest req=Mockito.spy(PaymentRequest.class);
		CustomerEntity centity=Mockito.spy(CustomerEntity.class);
		ErrorOutResponse error=Mockito.spy(ErrorOutResponse.class);
		RequestDto dto=Mockito.spy(RequestDto.class);
		
		com.dell.market.entity.Error err=Mockito.spy(com.dell.market.entity.Error.class);
		PaymentResponse re=Mockito.spy(PaymentResponse.class);
		Customer cos=Mockito.spy(Customer.class);
		com.dell.market.entity.response.Customer cos1=Mockito.spy(com.dell.market.entity.response.Customer.class);
		Notify note=Mockito.spy(Notify.class);
		Notes no=Mockito.spy(Notes.class);
        headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
		//String Accept="application/json";
		//String uuid="xyz-abc-cfg";
		
		req.setAmount(1000);	  
		req.setAccept_partial(true);
		req.setCallback_method("Get");
		req.setCallback_url("http://test.com");
		req.setCurrency("INR");
		req.setExpire_by(1111111);
		req.setFirst_min_partial_amount(0);
		req.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		req.setCustomer(cos);
		no.setPolicy_name("test");
		req.setNotes(no);
		req.setReference_id("TSsd2712");
		req.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		req.setNotify(note);
		
		//dto
		dto.setAmount(1000);	  
		dto.setAccept_partial(true);
		dto.setCallback_method("Get");
		dto.setCallback_url("http://test.com");
		dto.setCurrency("INR");
		dto.setExpire_by(1111111);
		dto.setFirst_min_partial_amount(0);
		dto.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		dto.setCustomer(null);
		no.setPolicy_name("test");
		dto.setNotes(null);
		dto.setReference_id("TSsd2712");
		dto.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		dto.setNotify(null);
		
		centity.setContact("1111");
		centity.setCustid(1);
		centity.setEmail(req.getCustomer().getEmail());
		centity.setName("Ujjwal Das");
		
		err.setCode("BAD_REQUEST_ERROR");
		//err.setDescription("payment link with given reference_id: TSsd2712 already exists. Please create a payment link with a different reference_id");		
		err.setDescription("Someting went wrong");
		err.setMetadata(List.of("",""));
		err.setReason(null);
		err.setSource(null);
		err.setStep(null);
		error.setError(err);
		
	
		Mockito.lenient().doReturn(dto).when(this.payService).paymentRequestUpdateSync(Mockito.any(PaymentRequest.class));
		
		Mockito.lenient().doReturn(centity).when(this.payService).callingMongoDB(Mockito.any());
		
      //  String expectedResonse="{\"error\":{\"code\":\"BAD_REQUEST_ERROR\",\"description\":\"payment link with given reference_id: TSsd2712 already exists. Please create a payment link with a different reference_id\",\"metadata\":[],\"reason\":null,\"source\":null,\"step\":null}}";
		
		 String expectedResonse="{\"status\":\"BAD_REQUEST\"}";
		 
		//ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResonse, HttpStatus.BAD_REQUEST);
		
		Mockito.lenient().when(this.template.exchange(Mockito.anyString(),
				Mockito.eq(HttpMethod.POST),
				Mockito.any(HttpEntity.class),
				Mockito.eq(String.class)
				)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST,expectedResonse));
				
		
		//PaymentResponse res=Mockito.spy(PaymentResponse.class);
		//RequestDto requestdto=Mockito.spy(RequestDto.class);	
		
		Mockito.lenient().when(this.mapper.readValue(Mockito.anyString(),Mockito.eq(ErrorOutResponse.class))).thenReturn(error);
		
		//Mockito.lenient().doReturn(req).when(this.payService).callingdb(req);
				
		//Object obj=Mockito.spy(Object.class);
		ResponseMessage message=Mockito.spy(ResponseMessage.class);	
		
		message.setStatus("400");
		message.setMessage("BAD Request");
		message.setUuid("xyz-abc-cfg");
		message.setDescription("Something went wrrong");
				
		
        CompletableFuture<ResponseEntity<ResponseMessage>> responseEntity1=this.payService.createOrder(req,headers);
		
		Assertions.assertEquals(message.getStatus(),responseEntity1.get().getBody().getStatus());
	}
	
	@Test
	public void createOrderSuccess_200() throws Exception
	{
		
		PaymentRequest req=Mockito.spy(PaymentRequest.class);
		CustomerEntity centity=Mockito.spy(CustomerEntity.class);
		RequestDto dto=Mockito.spy(RequestDto.class);
	
		PaymentResponse re=Mockito.spy(PaymentResponse.class);
		Customer cos=Mockito.spy(Customer.class);
		com.dell.market.entity.response.Customer cos1=Mockito.spy(com.dell.market.entity.response.Customer.class);
		Notify note=Mockito.spy(Notify.class);
		Notes no=Mockito.spy(Notes.class);
        headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
		//String Accept="application/json";
		//String uuid="xyz-abc-cfg";
		
		req.setAmount(1000);	  
		req.setAccept_partial(true);
		req.setCallback_method("Get");
		req.setCallback_url("http://test.com");
		req.setCurrency("INR");
		req.setExpire_by(1111111);
		req.setFirst_min_partial_amount(0);
		req.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		req.setCustomer(cos);
		no.setPolicy_name("test");
		req.setNotes(no);
		req.setReference_id("TSsd2712");
		req.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		req.setNotify(note);
		
		cos1.setName("Ujjwal Das");
		cos1.setEmail("user@gmail.com");
		cos1.setContact("1111");
		
		re.setCustomer(cos1);
		
		//dto
		dto.setAmount(1000);	  
		dto.setAccept_partial(true);
		dto.setCallback_method("Get");
		dto.setCallback_url("http://test.com");
		dto.setCurrency("INR");
		dto.setExpire_by(1111111);
		dto.setFirst_min_partial_amount(0);
		dto.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		dto.setCustomer(null);
		no.setPolicy_name("test");
		dto.setNotes(null);
		dto.setReference_id("TSsd2712");
		dto.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		dto.setNotify(null);
		
		centity.setContact("1111");
		centity.setCustid(1);
		centity.setEmail(req.getCustomer().getEmail());
		centity.setName("Ujjwal Das");
		
	
		Mockito.lenient().doReturn(dto).when(this.payService).paymentRequestUpdateSync(Mockito.any(PaymentRequest.class));
		
		Mockito.lenient().doReturn(centity).when(this.payService).callingMongoDB(Mockito.any());
		
        String expectedResonse="{\"status\":\"SUCCESS\"}";
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResonse, HttpStatus.OK);
		
		Mockito.lenient().when(this.template.exchange(Mockito.anyString(),
				Mockito.eq(HttpMethod.POST),
				Mockito.any(HttpEntity.class),
				Mockito.eq(String.class)
				)).thenReturn(responseEntity);
	
		//PaymentResponse res=Mockito.spy(PaymentResponse.class);
		//RequestDto requestdto=Mockito.spy(RequestDto.class);	
		
		Mockito.lenient().when(this.mapper.readValue(Mockito.anyString(),Mockito.eq(PaymentResponse.class))).thenReturn(re);
		
		Mockito.lenient().doReturn(req).when(this.payService).callingdb(req);
				
		//Object obj=Mockito.spy(Object.class);
		ResponseMessage message=Mockito.spy(ResponseMessage.class);	
		
		message.setStatus("200");
		message.setMessage("Success");
		message.setUuid("xyz-abc-cfg");
		message.setDescription("Message is successfully published");
				
		
        CompletableFuture<ResponseEntity<ResponseMessage>> responseEntity1=this.payService.createOrder(req,headers);
		
		Assertions.assertEquals(message.getStatus(),responseEntity1.get().getBody().getStatus());
		
	}	
	
	@Test
	public void createOrderSuccess_200_Timeout() throws Exception
	{
		
		PaymentRequest req=Mockito.spy(PaymentRequest.class);
		CustomerEntity centity=Mockito.spy(CustomerEntity.class);
		RequestDto dto=Mockito.spy(RequestDto.class);
	
		PaymentResponse re=Mockito.spy(PaymentResponse.class);
		Customer cos=Mockito.spy(Customer.class);
		com.dell.market.entity.response.Customer cos1=Mockito.spy(com.dell.market.entity.response.Customer.class);
		Notify note=Mockito.spy(Notify.class);
		Notes no=Mockito.spy(Notes.class);
        headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
		//String Accept="application/json";
		//String uuid="xyz-abc-cfg";
		
		req.setAmount(1000);	  
		req.setAccept_partial(true);
		req.setCallback_method("Get");
		req.setCallback_url("http://test.com");
		req.setCurrency("INR");
		req.setExpire_by(1111111);
		req.setFirst_min_partial_amount(0);
		req.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		req.setCustomer(cos);
		no.setPolicy_name("test");
		req.setNotes(no);
		req.setReference_id("TSsd2712");
		req.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		req.setNotify(note);
		
		cos1.setName("Ujjwal Das");
		cos1.setEmail("user@gmail.com");
		cos1.setContact("1111");
		
		re.setCustomer(cos1);
		
		//dto
		dto.setAmount(1000);	  
		dto.setAccept_partial(true);
		dto.setCallback_method("Get");
		dto.setCallback_url("http://test.com");
		dto.setCurrency("INR");
		dto.setExpire_by(1111111);
		dto.setFirst_min_partial_amount(0);
		dto.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		dto.setCustomer(null);
		no.setPolicy_name("test");
		dto.setNotes(null);
		dto.setReference_id("TSsd2712");
		dto.setReminder_enable(false);
		note.setEmail(false);
		note.setSms(false);
		dto.setNotify(null);
		
		centity.setContact("1111");
		centity.setCustid(1);
		centity.setEmail(req.getCustomer().getEmail());
		centity.setName("Ujjwal Das");
		
	
		Mockito.lenient().doReturn(dto).when(this.payService).paymentRequestUpdateSync(Mockito.any(PaymentRequest.class));
		
		Mockito.lenient().doReturn(centity).when(this.payService).callingMongoDB(Mockito.any());
		
        String expectedResonse="{\"status\":\"SUCCESS\"}";
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResonse, HttpStatus.OK);
		
		Mockito.lenient().when(this.template.exchange(Mockito.anyString(),
				Mockito.eq(HttpMethod.POST),
				Mockito.any(HttpEntity.class),
				Mockito.eq(String.class)
				)).thenThrow(new RuntimeException("Timeout Exception"));
	
		//PaymentResponse res=Mockito.spy(PaymentResponse.class);
		//RequestDto requestdto=Mockito.spy(RequestDto.class);	
		
		Mockito.lenient().when(this.mapper.readValue(Mockito.anyString(),Mockito.eq(PaymentResponse.class))).thenReturn(re);
		
		Mockito.lenient().doReturn(req).when(this.payService).callingdb(req);
				
		//Object obj=Mockito.spy(Object.class);
		ResponseMessage message=Mockito.spy(ResponseMessage.class);	
		
		message.setStatus("500");
		message.setMessage("Success");
		message.setUuid("xyz-abc-cfg");
		message.setDescription("Message is successfully published");
				
		
        CompletableFuture<ResponseEntity<ResponseMessage>> responseEntity1=this.payService.createOrder(req,headers);
        
        //String result=responseEntity1.get(1,TimeUnit.SECONDS).getBody().getStatus();
		
		//Assertions.assertEquals(message.getStatus(),result);
        Assertions.assertEquals(message.getStatus(),responseEntity1.get().getBody().getStatus());
		
	}	
	
}
  