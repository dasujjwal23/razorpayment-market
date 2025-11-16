package com.dell.market.service;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dell.market.dto.RequestDto;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class SyncCallService {

	Logger log=LoggerFactory.getLogger(SyncCallService.class);
	
	@Autowired
	RazorPayService payService;

	String errorOutClass="ErrorOutResponse";
	
	String responseClass="PaymentResponse";
	
	Object out=null;
	
	//@TaskExecutionTime
	public ResponseEntity<ResponseMessage> syncCall(PaymentRequest payment,HttpHeaders headers) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException,Exception
	{
		 try {
			log.info("Sync Method is executed with Thraed : "+Thread.currentThread().getName());
			return this.payService.createOrder(payment,headers).get();
		  }
		  catch(Exception e)
		  {
			  log.error("Exception in SyncCallService syncCall method : "+e.getMessage());
			  throw e;
		  }	
	 } 
	
}
