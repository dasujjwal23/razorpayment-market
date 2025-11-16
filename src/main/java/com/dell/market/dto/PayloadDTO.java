package com.dell.market.dto;

import com.dell.market.entity.db1.Customer;
import com.dell.market.entity.db1.Notes;
import com.dell.market.entity.db1.Notify;
import com.dell.market.entity.db1.PaymentRequest;

public class PayloadDTO extends PaymentRequest{

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return super.getAmount();
	}

	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		super.setAmount(amount);
	}

	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return super.getCurrency();
	}

	@Override
	public void setCurrency(String currency) {
		// TODO Auto-generated method stub
		super.setCurrency(currency);
	}

	@Override
	public boolean isAccept_partial() {
		// TODO Auto-generated method stub
		return super.isAccept_partial();
	}

	@Override
	public void setAccept_partial(boolean accept_partial) {
		// TODO Auto-generated method stub
		super.setAccept_partial(accept_partial);
	}

	@Override
	public int getFirst_min_partial_amount() {
		// TODO Auto-generated method stub
		return super.getFirst_min_partial_amount();
	}

	@Override
	public void setFirst_min_partial_amount(int first_min_partial_amount) {
		// TODO Auto-generated method stub
		super.setFirst_min_partial_amount(first_min_partial_amount);
	}

	@Override
	public int getExpire_by() {
		// TODO Auto-generated method stub
		return super.getExpire_by();
	}

	@Override
	public void setExpire_by(int expire_by) {
		// TODO Auto-generated method stub
		super.setExpire_by(expire_by);
	}

	@Override
	public String getReference_id() {
		// TODO Auto-generated method stub
		return super.getReference_id();
	}

	@Override
	public void setReference_id(String reference_id) {
		// TODO Auto-generated method stub
		super.setReference_id(reference_id);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		super.setDescription(description);
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return super.getCustomer();
	}

	@Override
	public void setCustomer(Customer customer) {
		// TODO Auto-generated method stub
		super.setCustomer(customer);
	}

	@Override
	public Notify getNotify() {
		// TODO Auto-generated method stub
		return super.getNotify();
	}

	@Override
	public void setNotify(Notify notify) {
		// TODO Auto-generated method stub
		super.setNotify(notify);
	}

	@Override
	public boolean isReminder_enable() {
		// TODO Auto-generated method stub
		return super.isReminder_enable();
	}

	@Override
	public void setReminder_enable(boolean reminder_enable) {
		// TODO Auto-generated method stub
		super.setReminder_enable(reminder_enable);
	}

	@Override
	public Notes getNotes() {
		// TODO Auto-generated method stub
		return super.getNotes();
	}

	@Override
	public void setNotes(Notes notes) {
		// TODO Auto-generated method stub
		super.setNotes(notes);
	}

	@Override
	public String getCallback_url() {
		// TODO Auto-generated method stub
		return super.getCallback_url();
	}

	@Override
	public void setCallback_url(String callback_url) {
		// TODO Auto-generated method stub
		super.setCallback_url(callback_url);
	}

	@Override
	public String getCallback_method() {
		// TODO Auto-generated method stub
		return super.getCallback_method();
	}

	@Override
	public void setCallback_method(String callback_method) {
		// TODO Auto-generated method stub
		super.setCallback_method(callback_method);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
