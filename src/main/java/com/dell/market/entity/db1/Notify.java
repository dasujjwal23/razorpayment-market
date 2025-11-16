package com.dell.market.entity.db1;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Notify {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("nid")
	public int nid;
	
	@JsonProperty("sms")
	public boolean sms;
	
	@JsonProperty("email")
    public boolean email;
    
     
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
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
	
	public Notify() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Notify [nid=" + nid + ", sms=" + sms + ", email=" + email + "]";
	}	
    
}
