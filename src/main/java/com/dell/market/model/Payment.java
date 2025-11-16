package com.dell.market.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(name="Request Body")
public class Payment {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int orderid;
	private String productname;
	private String currency;
	private double amount;
	
	
	private String receipt;
		
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public Payment(int orderid, String productname,double amount, String receipt,String currency) {
		super();
		this.orderid = orderid;
		this.productname = productname;
		this.currency=currency;
		this.amount = amount;
		this.receipt = receipt;
	}
	
	public Payment(double amount, String receipt,String currency) {
		super();
		this.currency=currency;
		this.amount = amount;
		this.receipt = receipt;
	}
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Payment [orderid=" + orderid + ", productname=" + productname + ", currency=" + currency + ", amount="
				+ amount + ", receipt=" + receipt + "]";
	}
	
	
}
