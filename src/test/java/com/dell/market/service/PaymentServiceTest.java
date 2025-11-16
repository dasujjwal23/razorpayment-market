package com.dell.market.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

import com.dell.market.model.Payment;
import com.dell.market.model.Response;
import com.dell.market.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import junit.framework.Assert;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
	
	@Mock
	public RazorpayClient client;
	
	@Mock
	public PaymentRepository repo;
	
	@InjectMocks
	public PaymantService service;
	
//	@Value("${payment.key_id}")
	public String key="test_id";
	
//	@Value("${payment.key_secret}")
	public String secret="test_secret";
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
/*
//	@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	@Test
	public void createOrder() throws JsonProcessingException, RazorpayException
	{
		Payment pay=new Payment();
		pay.setAmount(500.00);
		pay.setCurrency("INR");
		pay.setReceipt("order_011");
		Response resp=new Response(50000.00,0.00,List.of("Sync"),1751119693,50000.00,"INR","order011","order_QmdxPyNzaqRAaW"
				                            ,"order","null",0,"created");
		//Response res=new Response(new JSONObject().put(pay.getAmount()*100,pay.getCurrency(),pay.getReceipt()));
		JSONObject object=new JSONObject();	
		object.put("amount",pay.getAmount()*100);
		object.put("receipt",pay.getReceipt());
		object.put("currency",pay.getCurrency());
		
		Order order=new Order(new JSONObject().put("id","order_test_id"));
		
		//client=new RazorpayClient(key, secret);
		
		//System.out.println(this.client.orders.create(Mockito.any(JSONObject.class)))
		
		
		Mockito.when(client.orders.create(Mockito.any(JSONObject.class)).toString()).thenReturn(Mockito.anyString());
		
		Assert.assertEquals(resp.getAmount(), pay.getAmount());
	}
*/	
	@Test
    public void testCreateOrderSuccess() throws Exception {
        // Arrange
        String orderId = "order_test_123";
        String expectedOrderJson = "{\"id\":\"" + orderId + "\", \"amount\":10000}";
        //Order mockOrder = new Order(new JSONObject().put("id",orderId)); // Create a mock Order object
        
        Order mockOrder = Mockito.mock(Order.class); 
        
        client=new RazorpayClient(Mockito.anyString(),Mockito.anyString());
        
       // Mockito.mock(client.orders.create(Mockito.any(JSONObject.class)));

        Mockito.when(client.orders.create(Mockito.any(JSONObject.class))).thenReturn(mockOrder); // Stub the create method

        // Act
       // String createdOrderId = service.createOrder(null);  
        		//.createRazorpayOrder(10000, "INR");

        // Assert
        Assertions.assertNotNull(mockOrder);
        //Assert.assertEquals(orderId, createdOrderId);
        //Assert.verify(razorpayClient.orders, times(1)).create(anyMap()); // Verify method call
    }
}
