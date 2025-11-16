package com.dell.market.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

import com.dell.market.dto.RequestDto;
import com.dell.market.entity.db1.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class AsyncCallService {

	Logger log=LoggerFactory.getLogger(AsyncCallService.class);
	
    String errorOutClass="ErrorOutResponse";
	
	String responseClass="PaymentResponse";
	
	@Autowired
	RazorPayService payService;
	
		
	@Async("asyncTaskExecutor")
	public void asyncCall(PaymentRequest payment,HttpHeaders headers) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException,Exception
	{	
		try {
			log.info("Async Method is executed with Thread : "+Thread.currentThread().getName());
			this.payService.createOrder(payment,headers);
		}
		catch(Exception e)
		{
			log.error("Exception in AsyncCallService asyncCall method : "+e.getMessage());
			throw e;
		}
	}    
	
}
