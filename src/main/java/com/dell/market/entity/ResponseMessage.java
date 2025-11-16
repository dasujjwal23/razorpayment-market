package com.dell.market.entity;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
	
	private String status;
	private String message;
	private String uuid;
	private String description;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ResponseMessage(String status, String message, String uuid,String description) {
		super();
		this.status = status;
		this.message = message;
		this.uuid=uuid;
		this.description = description;
	}
	
	public ResponseMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "{\"status\" : \"" + status + "\", \"message\" : \"" + message + "\", \"uuid\" : \"" + uuid + "\", \"description\" : \""
				+ description + "\"}";
	}
	
	
	
}
