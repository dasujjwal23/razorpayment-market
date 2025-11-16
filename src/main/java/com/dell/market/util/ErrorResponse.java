package com.dell.market.util;

public class ErrorResponse {

	private String status;
	private String type;
	private String description;
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", type=" + type + ", description=" + description + "]";
	}


	public ErrorResponse(String status, String type, String description) {
		super();
		this.status = status;
		this.type = type;
		this.description = description;
	}


	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
