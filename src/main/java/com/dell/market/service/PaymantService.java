package com.dell.market.service;

//import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import java.util.stream.Collectors;
//import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.dell.market.model.Payment;
import com.dell.market.model.Response;
import com.dell.market.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.RazorpayClient;

//import com.razorpay.RazorpayException;


@Service
public class PaymantService {
	
	Logger log=LoggerFactory.getLogger(PaymantService.class);
	
	private RazorpayClient client;
	
	//@Autowired
	//PaymentRepository repo;
	
	@Value("${payment.key_id}")
	private String key;
	
	@Value("${payment.key_secret}")
	private String secret;
	
	@Value("${retail.base_url}")
	private String baseURL;
	
	@Value("${retail.endpoint}")
	private String enpoint;
	
	@Value("${retail.username}")
	private String username;
	
	ObjectMapper mapper=null,mapper1=null;
	
	Response result=null;
	long start=0,end=0;
	
	public Response createOrder(Payment payment)
	{
	    mapper1=new ObjectMapper();
	   //Map<String,Object> map=null;
	     String in=null;
	   try {
		 //map=mapper1.convertValue(payment,new TypeReference<Map<String,Object>>(){});
		 in=mapper1.writeValueAsString(payment);
		//log.info("Before Formated String: "+in);
		//String str=formattedPayload(in);
		//log.info("After Formated String: "+in);
	     payment=mapper1.readValue(in, Payment.class);	
		start=System.currentTimeMillis();
		JSONObject object=new JSONObject();		
		log.info("Request Payload: "+payment.toString());
		//Map<String,Object> map=new HashMap<>();
		object.put("amount",payment.getAmount()*100);
		object.put("receipt",payment.getReceipt());
		object.put("currency",payment.getCurrency());
		
		try {
			client=new RazorpayClient(key, secret);
			String res=client.orders.create(object).toString();
			mapper=new ObjectMapper();
			result=mapper.readValue(res, Response.class);	
			log.info("Response Payload: "+result.toString());
			if(payment.getAmount()<=200)
			{
				//double pay=payment.getAmount();
				//log.info("Call is going as Async as amount is less than 200");
				result.getNotes().add("Async");
				asyncTransaction(payment,result);
				end=System.currentTimeMillis();
				log.info("Call is going as Async with responsetime: "+(end-start)+" ms");
			}else {
		//		repo.save(payment);
				result.getNotes().add("Sync");
				//log.info("Call is going as Sync with amount:: "+payment.getAmount());
				end=System.currentTimeMillis();
				log.info("Call is going as Sync with responsetime: "+(end-start)+" ms");
				return result;
			}			
		} 
			catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}


	
	public void asyncTransaction(Payment pay,Response result) {	
	  try {
			CompletableFuture<Response> com=CompletableFuture.supplyAsync(()->{
				log.info("Call is going as Async in future interface");
			//	repo.save(pay);
				try {
					return result;					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.error("Error occurred during async call");
				throw new RuntimeException("Transaction failed");
			});
		
			log.info("Call is going as Async with asyncresponse "+com.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String formattedPayload(String str) {
		String str1=null,str2=null;
		int start=0,end=0;
		
		for(int i=0;i<str.length();i++) {			
			char ch=str.charAt(i);
			if(ch==':') {
				start=i;
			}
			if(ch==',' || ch=='}') {
				end=i;
				str1=str.substring(start+1,end);
				str2=str1.replaceAll("[^a-zA-Z0-9\"\\.]","");
				str=str.replaceAll(str1,str2);	
			}
		}
		return str;	
	}
	     
}
