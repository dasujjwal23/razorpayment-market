package com.dell.market.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Notify {
	
	@JsonIgnore
	public int nid;
	
	@JsonProperty("sms")
	public boolean sms;
	
	@JsonProperty("email")
    public boolean email;
    
 
	public boolean isSms() {
		return sms;
	}
	public void setSms(boolean sms) {
		this.sms = sms;
	}
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public Notify(boolean sms, boolean email) {
		super();
		this.sms = sms;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Notify [sms=" + sms + ", email=" + email + "]";
	}
    
    
}
