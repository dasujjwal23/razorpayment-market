package com.dell.market.entity.db1;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("cid")
	public int cid;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("contact")
    public String contact;
	
	@JsonProperty("email")
//	@Email(message = "Invalid Email")
    public String email;
    
       
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
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
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", contact=" + contact + ", email=" + email + "]";
	}
	 
}
