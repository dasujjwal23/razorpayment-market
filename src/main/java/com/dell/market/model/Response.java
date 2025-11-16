package com.dell.market.model;

import java.util.List;

public class Response {

	 private double amount;
     private double amount_paid;
     private List<String> notes;
     private long created_at;
     private double amount_due;
     private String currency;
     
     private String receipt;
     private String id;
     private String entity;
     private String offer_id;
     private int attempts;
     private String status;
     
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public List<String> getNotes() {
		return notes;
	}
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	public double getAmount_due() {
		return amount_due;
	}
	public void setAmount_due(double amount_due) {
		this.amount_due = amount_due;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Response [amount=" + amount + ", amount_paid=" + amount_paid + ", notes=" + notes + ", created_at="
				+ created_at + ", amount_due=" + amount_due + ", currency=" + currency + ", receipt=" + receipt
				+ ", id=" + id + ", entity=" + entity + ", offer_id=" + offer_id + ", attempts=" + attempts
				+ ", status=" + status + "]";
	}
	public Response(double amount, double amount_paid, List<String> notes, long created_at, double amount_due,
			String currency, String receipt, String id, String entity, String offer_id, int attempts, String status) {
		super();
		this.amount = amount;
		this.amount_paid = amount_paid;
		this.notes = notes;
		this.created_at = created_at;
		this.amount_due = amount_due;
		this.currency = currency;
		this.receipt = receipt;
		this.id = id;
		this.entity = entity;
		this.offer_id = offer_id;
		this.attempts = attempts;
		this.status = status;
	}
	public Response(double amount, String currency, String receipt) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.receipt = receipt;
	}
     
	
     
}
