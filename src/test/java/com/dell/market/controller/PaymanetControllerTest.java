package com.dell.market.controller;

import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.*;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dell.market.ApiException;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.Customer;
import com.dell.market.entity.db1.Notes;
import com.dell.market.entity.db1.Notify;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.service.AsyncCallService;
import com.dell.market.service.PaymentException;
import com.dell.market.service.RazorPayService;
import com.dell.market.service.SyncCallService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness =Strictness.LENIENT)
public class PaymanetControllerTest {
	
	@InjectMocks
	PaymanetController controller;
	
	@Mock
	SyncCallService syncCallService;
	
	@Mock
	AsyncCallService ayncCallService;

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
	
	//@Spy
	//HttpHeaders headers;
	
	@Test
	public void createOrderPaySyncTest() throws Exception
	{
		req=Mockito.spy(PaymentRequest.class);
		cos=Mockito.spy(Customer.class);
		note=Mockito.spy(Notify.class);
		no=Mockito.spy(Notes.class);
		HttpHeaders headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
		String Accept="application/json";
		String uuid="xyz-abc-cfg";
		req.setAmount(1000);	  
		req.setAccept_partial(true);
		req.setCallback_method("get");
		req.setCallback_url("http://test.com");
		req.setCurrency("INR");
		req.setExpire_by(1801097057);
		req.setFirst_min_partial_amount(0);
		req.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		req.setCustomer(cos);
		no.setPolicy_name("test");
		req.setNotes(no);
		req.setReference_id("TSsd2152");
		req.setReminder_enable(true);
		
		note.setEmail(true);
		note.setSms(true);
		req.setNotify(note);
	
		obj=Mockito.spy(Object.class);
		message=Mockito.spy(ResponseMessage.class);	
		
		message.setStatus("200");
		message.setMessage("Success");
		message.setUuid("xyz-abc-cfg");
		message.setDescription("Message is Successful");
		 
	    ResponseEntity<ResponseMessage> responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
		
	    Mockito.lenient().when(this.syncCallService.syncCall(Mockito.any(),Mockito.any())).thenReturn(responseEntity);
		
		//Mockito.doReturn(responseEntity).when(this.syncCallService).syncCall(req, headers);		
		ResponseEntity<ResponseMessage> responseEntity2=this.controller.createOrderPay(Accept,uuid,req);
	   // ResponseEntity<ResponseMessage> responseEntity1=this.syncCallService.syncCall(req,headers);
		Assertions.assertNotNull(responseEntity2.getBody());
		Assertions.assertEquals(responseEntity2.getBody().getStatus(), message.getStatus());
		
	}
	
	@Test
	public void createOrderPayAsyncTest() throws JsonMappingException, JsonProcessingException, PaymentException, ApiException, InterruptedException, ExecutionException
	{
		req=Mockito.spy(PaymentRequest.class);
		cos=Mockito.spy(Customer.class);
		note=Mockito.spy(Notify.class);
		no=Mockito.spy(Notes.class);
		HttpHeaders headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
		String Accept=headers.get("Accept").toString();
		String uuid=headers.get("uuid").toString();
		
		//req.setSid(0);
		req.setAmount(100);	  
		req.setAccept_partial(true);
		req.setCallback_method("get");
		req.setCallback_url("http://test.com");
		req.setCurrency("INR");
		req.setExpire_by(1801097057);
		req.setFirst_min_partial_amount(0);
		req.setDescription("dummy");
	    cos.setName("Ujjwal Das");
		cos.setEmail("user@gmail.com");
		cos.setContact("1111");
		req.setCustomer(cos);
		no.setPolicy_name("test");
		req.setNotes(no);
		req.setReference_id("TSsd2152");
		req.setReminder_enable(true);
		
		note.setEmail(true);
		note.setSms(true);
		req.setNotify(note);
	
		obj=Mockito.spy(Object.class);
		message=Mockito.spy(ResponseMessage.class);	       
		
		message.setStatus("200");
		message.setMessage("Success");
		message.setUuid("xyz-abc-cfg");
		message.setDescription("Message is Successful");
		
	   // ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		
		//Mockito.when(this.ayncCallService.asyncCall(req,headers)).verify(null);
		
		ResponseEntity<?> responseEntity1=this.controller.createOrderPay(Accept,uuid,req);
		//this.ayncCallService.asyncCall(req,headers);
		Assertions.assertNotNull(responseEntity1);
		
		//Mockito.verify(this.ayncCallService).asyncCall(req);
		
	}
	
	@Test
	public void validateAgeTest_Positive()
	{
		Assertions.assertDoesNotThrow(()->this.controller.validateAge(25));
	}
	
	@Test
	public void validateAgeTest_Negative()
	{
		IllegalArgumentException exception=Assertions.assertThrows(IllegalArgumentException.class,
				()->this.controller.validateAge(-1));
		
		Assertions.assertEquals("Age cannot be negative",exception.getMessage());
	}
}
