package com.dell.market.controller;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dell.market.ApiException;
import com.dell.market.dto.RequestDto;
import com.dell.market.entity.ErrorOutResponse;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.entity.response.PaymentResponse;
import com.dell.market.model.Payment;
import com.dell.market.model.Response;
import com.dell.market.service.AsyncCallService;
import com.dell.market.service.PaymantService;
import com.dell.market.service.PaymentException;
import com.dell.market.service.RazorPayService;
import com.dell.market.service.RazorPayUpdateService;
import com.dell.market.service.SyncCallService;
import com.dell.market.service.TaskExecutionTime;
import com.dell.market.util.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@Tag(name="Payment Controller",description="has the endpoints of rest endpoints")
public class PaymanetController {
	
	Logger log=LoggerFactory.getLogger(PaymanetController.class);

	String errorOutClass="ErrorOutResponse";
	
	String responseClass="PaymentResponse";
	
	@Autowired
	PaymantService service;
	
	@Autowired
	RazorPayService payService;
	
	@Autowired
	SyncCallService syncCallService;
	
	@Autowired
	AsyncCallService ayncCallService;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RazorPayUpdateService razorPayUpdateService;

	List<String> uuidlist=new ArrayList<>();
	
	@Operation(
			operationId="createOrder",
			summary="Adding a Transaction",
			description="This is the process of adding a oder into razorpay"
			)
	@ApiResponses({
			@ApiResponse(responseCode="201",
			              description="creates a successful order",
			              content=@Content(
			            		  mediaType="application/json",
			            		  schema=@Schema(implementation=Response.class)
			            		  )
		            ),
			@ApiResponse(responseCode="400",
            description="order Failed",
            content=@Content(
          		  mediaType="application/json",
          		  schema=@Schema(implementation=Response.class)
          		  ))}
			    )
	@PostMapping(value="/order")
	public ResponseEntity<?> createOrder(@RequestBody Payment payment)
	{
		Response response;
		try {
			response = this.service.createOrder(payment);
			if(response!=null && response.getNotes().get(0).equalsIgnoreCase("Sync"))
				return ResponseEntity.status(HttpStatus.OK).body(response);
			else {
				//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create payment");
				return ResponseEntity.status(HttpStatus.OK).build();
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	@Operation(
			operationId="createOrderPay",
			summary="Adding a Payment Mode",
			description="This is the process of adding a oder into razorpay"
			)
	@ApiResponses({
			@ApiResponse(responseCode="200",
			              description="creates a successful payment",
			            		  content=@Content(
					            		  mediaType="application/json",
					            		  schema=@Schema(implementation=Object.class)
					            		  )
		            ),
			@ApiResponse(responseCode="400",
            description="payment failed",
          		  content=@Content(
		            		  mediaType="application/json",
		            		  schema=@Schema(implementation=Object.class)
		            		  )
                   )}
			 )  
	
	@PostMapping(value="/razorpay",produces ="application/json",consumes ="application/json")
	public ResponseEntity<ResponseMessage> createOrderPay(@RequestHeader(value="Accept", required=true) String Accept,
			                                              @RequestHeader(value="UUID", required=true) String uuid,
			                         @RequestBody @Valid PaymentRequest payment) throws ApiException,PaymentException,JsonMappingException, JsonProcessingException
	{
		long startTime = System.currentTimeMillis();
		uuid=validationUUID(uuid);
		ResponseEntity<ResponseMessage> resp=null;
		HttpHeaders headers=new HttpHeaders();
		headers.set("Accept",Accept);
		headers.set("uuid", uuid);
		//System.out.println("Requested header::"+headers.get("Accept"));
		try {
				if(payment.getAmount()>500) {
				     resp=this.syncCallService.syncCall(payment,headers);
				     long endTime = System.currentTimeMillis();
				     log.info("Response Time of PaymanetController.createOrderPay Sync method : "+(endTime - startTime)+" ms ");
				     return resp;
				}else {
					   this.ayncCallService.asyncCall(payment,headers);	
					   long endTime = System.currentTimeMillis();
					   log.info("Response Time of PaymanetController.createOrderPay Async method : "+(endTime - startTime)+" ms ");
					     
					}
		} catch (Exception ex) {
			long endTime = System.currentTimeMillis();
			log.error("Exception occurred in PaymanetController.createOrderPayUpdate method with Response Time : "+(endTime - startTime)+" ms , Exception : "+ex.getMessage());
			ErrorResponse error=new ErrorResponse("500","Internal Server Error", ex.getMessage());
			throw new ApiException(error);
		}	
		return ResponseEntity.status(HttpStatus.OK).build();
	} 
	

	@Operation(
			operationId="createOrderPayUpdate",
			summary="Updating a Payment Mode",
			description="This is the process of adding a oder into razorpay"
			)
	@ApiResponses({
			@ApiResponse(responseCode="200",
			              description="updates a successful payment",
			            		  content=@Content(
					            		  mediaType="application/json",
					            		  schema=@Schema(implementation=Object.class)
					            		  )
		            ),
			@ApiResponse(responseCode="500",
            description="No Value Found",
          		  content=@Content(
		            		  mediaType="application/json",
		            		  schema=@Schema(implementation=Object.class)
		            		  )
                   )}
			 )  
	@PutMapping(value="/razorpay/{reference_id}",produces ="application/json",consumes ="application/json")
	public ResponseEntity<ResponseMessage> createOrderPayUpdate(@RequestHeader(value="Accept", required=true) String Accept,
			                                              @RequestHeader(value="UUID", required=true) String uuid,
			                         @RequestBody PaymentRequest payment,@PathVariable String reference_id) throws ApiException,PaymentException,JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException
	{
		 long startTime = System.currentTimeMillis();
		 uuid=validationUUID(uuid);
		 HttpHeaders headers=null;
		 ResponseEntity<ResponseMessage> resp=null;
		 try {
			  if(headers==null) {
				headers=new HttpHeaders();
				headers.set("Accept",Accept);
				headers.set("uuid", uuid);
				resp=ResponseEntity.ok(this.razorPayUpdateService.razorUpdate(payment,headers,reference_id));
				long endTime = System.currentTimeMillis();
				log.info("Response Time of PaymanetController.createOrderPayUpdate method : "+(endTime - startTime)+" ms ");
			   }	
		  }catch (Exception ex) {
			 long endTime = System.currentTimeMillis();
			 log.error("Exception occurred in PaymanetController.createOrderPayUpdate method with Response Time : "+(endTime - startTime)+" ms , Exception : "+ex.getMessage());
			 //ErrorResponse error=new ErrorResponse("500","Internal Server Error",ex.getMessage());
		     throw new ApiException(new ErrorResponse("500","Internal Server Error",ex.getMessage()));
		  }
	     return resp;
	}
	
	public String validationUUID(String uuid) {
		if(!uuidlist.contains(uuid))
		{
			uuidlist.add(uuid);
		}else {
			//uuidlist.add(uuid);
			uuid=UUID.randomUUID().toString() ;
		}
		return uuid;
	}
	
	public void validateAge(int age) {
	      if (age < 0) {
	         throw new IllegalArgumentException("Age cannot be negative");
	         }
	      }
	
  }
  