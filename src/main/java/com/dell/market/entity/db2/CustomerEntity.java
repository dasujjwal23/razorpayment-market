package com.dell.market.entity.db2;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;


@Document(collection ="Test")
public class CustomerEntity {

	@Id
	@JsonProperty("custid")
	public int custid;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("contact")
    public String contact;
	
	@JsonProperty("email")
    public String email;
    	
	public int getCustid() {
		return custid;
	}
	
	public void setCustid(int custid) {
		this.custid = custid;
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
	public CustomerEntity(int custid,String name, String contact, String email) {
		super();
		this.custid=custid;
		this.name = name;
		this.contact = contact;
		this.email = email;
	}
	
	
	
	public CustomerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customer [cid=" + custid + ", name=" + name + ", contact=" + contact + ", email=" + email + "]";
	}
}
