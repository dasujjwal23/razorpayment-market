package com.dell.market.service;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import com.dell.market.ApiException;
import com.dell.market.entity.ResponseMessage;
import com.dell.market.entity.db1.PaymentRequest;
import com.dell.market.repository.db1.RazorPayRepo;


@Service
public class RazorPayUpdateService {
	
	@Autowired
	RazorPayRepo razorPayRepo;
	
	@Autowired
	ResponseMessage message;
	
	Logger log=LoggerFactory.getLogger(RazorPayUpdateService.class);

	public ResponseMessage razorUpdate(PaymentRequest payment,HttpHeaders headers,String reference_id) throws ApiException
	{	
		Optional<PaymentRequest> newpayment=null;
		String vuuid=headers.get("uuid").toString().replace("[","").replace("]","");
	    try {
			newpayment=Optional.ofNullable(razorPayRepo.findByReferenceId(reference_id));
			log.info("Resquest is fetched from DB :: {}",newpayment.get());
			if(newpayment.isPresent())
			{
				newpayment.get().setAccept_partial(payment.isAccept_partial());
				newpayment.get().setAmount(payment.getAmount());
				newpayment.get().setCallback_method(payment.getCallback_method());
				newpayment.get().setCallback_url(payment.getCallback_url());	
				newpayment.get().setCurrency(payment.getCurrency());
				newpayment.get().setDescription(payment.getDescription());
				newpayment.get().setExpire_by(payment.getExpire_by());
				newpayment.get().setFirst_min_partial_amount(payment.getFirst_min_partial_amount());
				newpayment.get().getNotes().setPolicy_name(payment.getNotes().getPolicy_name());
				newpayment.get().setReminder_enable(payment.isReminder_enable());
				//newpayment.setCustomer(payment.getCustomer());
				newpayment.get().getNotify().setEmail(payment.getNotify().isEmail());
				newpayment.get().getNotify().setSms(payment.getNotify().isSms());
				
				if(payment.getCustomer()!=null && payment.getCustomer().getEmail()!=null) {
					//Customer custmer=razorPayRepo.findByCid(newpayment.get().getCustomer().getCid());			
					//custmer.setEmail(payment.getCustomer().getEmail());
					newpayment.get().getCustomer().setEmail(payment.getCustomer().getEmail());
				}
				
				if(payment.getCustomer()!=null && payment.getCustomer().getContact()!=null) {
					//Customer custmer=razorPayRepo.findByCid(newpayment.get().getCustomer().getCid());			
					//custmer.setEmail(payment.getCustomer().getContact());
					newpayment.get().getCustomer().setContact(payment.getCustomer().getContact());
				}
				if(payment.getCustomer()!=null && payment.getCustomer().getName()!=null) {
					//Customer custmer=razorPayRepo.findByCid(newpayment.get().getCustomer().getCid());			
					//custmer.setEmail(payment.getCustomer().getName());
					newpayment.get().getCustomer().setName(payment.getCustomer().getName());
				}
				razorPayRepo.save(newpayment.get());
				
				log.info("Resquest is updated to DB :: {}",newpayment.get());
				
				message.setMessage("Success");
				message.setStatus("200");
				message.setUuid(vuuid);
				message.setDescription("RazorPay Payment Request Updated Successfully");
			}	
	   }catch(Exception ex) {
		    log.error("Exception occurred in RazorPayUpdateService.razorUpdate method : "+ex.getMessage());
		     //ErrorResponse error=new ErrorResponse("500","Internal Server Error",ex.getMessage());
		     throw ex;
		   // throw new RuntimeException("Exception occurred in RazorPayUpdateService.razorUpdate method ");
	   }
	    
		return message;
	}
	
}
