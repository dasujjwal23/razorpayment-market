package com.dell.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableWebMvc
@OpenAPIDefinition(info=@Info(
		title="Razorpay payment gateway with springboot",
		version="1.0",
		description="This is the REST API with rest endpoints"
		)
,servers= {
		@Server(url="http://localhost:8081",
				description="Generated Server Url")
})
@EnableTransactionManagement
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
	
	@Bean
	public RestTemplate template()
	{
		return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper mapper()
	{
		return new ObjectMapper();
	}
}
