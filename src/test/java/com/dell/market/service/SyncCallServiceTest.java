package com.dell.market.service;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.Customer;
import com.dell.market.entity.db1.Notes;
import com.dell.market.entity.db1.Notify;
import com.dell.market.entity.db1.PaymentRequest;


@ExtendWith(MockitoExtension.class)
public class SyncCallServiceTest {

	@InjectMocks
	private SyncCallService syncCallService;
	
	@Mock
	RazorPayService payService;
	/*
	@Spy
	PaymentRequest request;
	
	@Spy
	Customer cost;
	
	@Spy
	Notify noti;
	
	@Spy
	Notes nos;

	@Spy
	ResponseMessage message;
	*/
	@Test
	public void syncCallTest() throws Exception
	{
		PaymentRequest req=Mockito.spy(PaymentRequest.class);
		Customer cos=Mockito.spy(Customer.class);
		Notify noti=Mockito.spy(Notify.class);
		Notes nos=Mockito.spy(Notes.class);
        HttpHeaders headers=Mockito.spy(HttpHeaders.class);
		
		headers.set("Accept","application/json");
		headers.set("uuid", "xyz-abc-cfg");
		
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
		nos.setPolicy_name("test");
		req.setNotes(nos);
		req.setReference_id("TSsd2152");
		req.setReminder_enable(true);
		
		noti.setEmail(true);
		noti.setSms(true);
		req.setNotify(noti);
	
		//obj=Mockito.spy(Object.class);
		ResponseMessage message=Mockito.spy(ResponseMessage.class);	
		
		message.setStatus("200");
		message.setMessage("Success");
		message.setDescription("Message is Successful");
		
		 CompletableFuture<ResponseEntity<ResponseMessage>> responseEntity = CompletableFuture.completedFuture(new ResponseEntity<>(message, HttpStatus.OK));
		 
		 Mockito.when(this.payService.createOrder(req,headers)).thenReturn(responseEntity);
		
		 ResponseEntity<ResponseMessage>responseEntity1 = CompletableFuture.completedFuture(this.syncCallService.syncCall(req,headers)).get();
		 
		 Assertions.assertNotNull(responseEntity1.getBody());
		
	}
}
