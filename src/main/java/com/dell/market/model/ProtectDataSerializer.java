package com.dell.market.model;

import java.io.IOException;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class ProtectDataSerializer extends JsonSerializer<Object>{
	
	Logger log=LoggerFactory.getLogger(ProtectDataSerializer.class);

    @Override
  //  @Around("@annotation(com.dell.market.model.LogMask)")
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		//String pii=value.replaceAll("[^a-zA-Z0-9\"\\.]","");
		String pii=value.toString().replaceAll("\\w(?=\\w{4})","x");
		log.info("Log Masking is implmented ......");
    	//String pii=value.replaceAll("[a-zA-Z0-9]","x");
		gen.writeString(pii);
	}

}
   