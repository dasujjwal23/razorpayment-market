package com.dell.market.service;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.dell.market.ApiException;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.repository.db1.RazorPayRepo;

@Service
public class RazorpayPatchService {
	
	@Autowired
	RazorPayRepo razorPayRepo;
	
	@Autowired
	ResponseMessage message;
	
	Logger log=LoggerFactory.getLogger(RazorpayPatchService.class);

	public ResponseMessage razorPatch(Map<String,Object> map,HttpHeaders headers,String reference_id) throws ApiException
	{	
		try {
			Optional<PaymentRequest> newpayment=Optional.ofNullable(this.razorPayRepo.findByReferenceId(reference_id));;
		    String vuuid=headers.get("uuid").toString().replace("[","").replace("]","");
	      
			log.info("Resquest is fetched from DB :: {}",newpayment.get());
			if(newpayment.isPresent())
			{
				map.forEach((key,value)->{
					Field field=ReflectionUtils.getRequiredField(PaymentRequest.class,key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, newpayment.get(), value);
				});
				PaymentRequest savpay=this.razorPayRepo.save(newpayment.get());
				log.info("Payment Request updated successfully with :: {} :: uuid :: {}",savpay,vuuid);
			}
			message.setStatus("200");
			message.setMessage("Success");
			message.setDescription("Payment Request updated successfully for reference_id :"+reference_id);
			message.setUuid(vuuid);
		} catch (Exception e) {
			log.error("Exception in updating Payment Request for reference_id :"+reference_id+" is :: {}",e.getMessage());
			throw e;
		}
	    return message;
	}
}

