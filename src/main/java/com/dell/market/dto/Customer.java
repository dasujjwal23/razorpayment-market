package com.dell.market.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Customer {

	@JsonIgnore
	public int cid;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("contact")
    public String contact;
	
	@JsonProperty("email")
    public String email;
    
       
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customer(String name, String contact, String email) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", contact=" + contact + ", email=" + email + "]";
	}
    
    
}
