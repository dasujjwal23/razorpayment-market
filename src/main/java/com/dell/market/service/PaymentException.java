package com.dell.market.service;

import com.dell.market.entity.ErrorOutResponse;

public class PaymentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorOutResponse response;

	
	public ErrorOutResponse getResponse() {
		return response;
	}

	public void setResponse(ErrorOutResponse response) {
		this.response = response;
	}

	public PaymentException(ErrorOutResponse response) {
		super();
		this.response = response;
	}

	public PaymentException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
