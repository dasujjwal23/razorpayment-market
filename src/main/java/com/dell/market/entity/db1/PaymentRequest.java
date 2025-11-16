package com.dell.market.entity.db1;


import java.io.Serializable;

import com.dell.market.model.LogMask;
import com.dell.market.model.MaskData;
import com.dell.market.model.ProtectDataSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//import jakarta.persistence.Entity;


@Entity
@Table
public class PaymentRequest implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("sid")
	public int sid;
	
	@JsonProperty("amount")
	@NotNull(message="Amount should not be null")
	public int amount=0;
	
	@JsonProperty("currency")
    public String currency=null;
	
	@JsonProperty("accept_partial")
    public boolean accept_partial=false;
	
	@JsonProperty("first_min_partial_amount")
    public int first_min_partial_amount=0;
	
//	@LogMask
	@JsonProperty("expire_by")
    public int expire_by=0;
		
	
	@JsonProperty("reference_id")
	@NotBlank(message="reference_id should not be empty")
    public String reference_id=null;
	
	@JsonProperty("description")
    public String description=null;
    
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JsonProperty("customer")
    public Customer customer;
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
   // @OneToOne
    @JsonProperty("notify")
    public Notify notify;
    
    @JsonProperty("reminder_enable")
    public boolean reminder_enable=false;
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JsonProperty("notes")
    public Notes notes;
    
    @JsonProperty("callback_url")
    public String callback_url=null;
    
    @JsonProperty("callback_method")
    public String callback_method=null;
    
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public boolean isAccept_partial() {
		return accept_partial;
	}
	public void setAccept_partial(boolean accept_partial) {
		this.accept_partial = accept_partial;
	}
	public int getFirst_min_partial_amount() {
		return first_min_partial_amount;
	}
	public void setFirst_min_partial_amount(int first_min_partial_amount) {
		this.first_min_partial_amount = first_min_partial_amount;
	}
	public int getExpire_by() {
		return expire_by;
	}
	public void setExpire_by(int expire_by) {
		this.expire_by = expire_by;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Notify getNotify() {
		return notify;
	}
	public void setNotify(Notify notify) {
		this.notify = notify;
	}
	public boolean isReminder_enable() {
		return reminder_enable;
	}
	public void setReminder_enable(boolean reminder_enable) {
		this.reminder_enable = reminder_enable;
	}
	public Notes getNotes() {
		return notes;
	}
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public String getCallback_method() {
		return callback_method;
	}
	public void setCallback_method(String callback_method) {
		this.callback_method = callback_method;
	}
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentRequest(int amount, String currency, boolean accept_partial, int first_min_partial_amount,
			int expire_by, String reference_id, String description, Customer customer, Notify notify,
			boolean reminder_enable, Notes notes, String callback_url, String callback_method) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.accept_partial = accept_partial;
		this.first_min_partial_amount = first_min_partial_amount;
		this.expire_by = expire_by;
		this.reference_id = reference_id;
		this.description = description;
		this.customer = customer;
		this.notify = notify;
		this.reminder_enable = reminder_enable;
		this.notes = notes;
		this.callback_url = callback_url;
		this.callback_method = callback_method;
	}
	
	@Override
	public String toString() {
		return "PaymentRequest [sid=" + sid + ", amount=" + amount + ", currency=" + currency + ", accept_partial="
				+ accept_partial + ", first_min_partial_amount=" + first_min_partial_amount + ", expire_by=" + expire_by
				+ ", reference_id=" + reference_id + ", description=" + description + ", customer=" + customer
				+ ", notify=" + notify + ", reminder_enable=" + reminder_enable + ", notes=" + notes + ", callback_url="
				+ callback_url + ", callback_method=" + callback_method + "]";
	}
	
	
    
}
