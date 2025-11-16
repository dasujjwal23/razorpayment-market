package com.dell.market.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dell.market.ApiException;
import com.dell.market.entity.ErrorOutResponse;
import com.dell.market.service.PaymentException;
import com.dell.market.util.ErrorResponse;


@RestControllerAdvice
public class PaymentControllerAdvice{
	
	Logger log=LoggerFactory.getLogger(PaymentControllerAdvice.class);

	 @ExceptionHandler
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public ErrorOutResponse customHandler(PaymentException exp)
	 {
		return exp.getResponse();
	 }
	 
	 @ExceptionHandler
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public String runcustomHandler(RuntimeException exp)
	 {
		return exp.getMessage();
	 }
	
	 @ExceptionHandler(ApiException.class)
	// @ResponseBody
	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 public ResponseEntity<ErrorResponse> handler(ApiException exp) {
		 
		 log.info("Inside the ApiException handler of Controller Advice");
		 ErrorResponse response=new ErrorResponse();
		 response.setStatus(exp.getResponse().getStatus());
		 response.setDescription(exp.getResponse().getDescription());;
         response.setType(exp.getResponse().getType());
		 return new ResponseEntity<ErrorResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String,String> handler(MethodArgumentNotValidException ex)
	{
		Map<String,String> errmap=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach( error ->{
			errmap.put(error.getField(),error.getDefaultMessage());
		});
		return errmap;
		
	}
}
