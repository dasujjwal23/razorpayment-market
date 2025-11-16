package com.dell.market;

import com.dell.market.util.ErrorResponse;

public class ApiException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private ErrorResponse response;

	public ErrorResponse getResponse() {
		return response;
	}

	public void setResponse(ErrorResponse response) {
		this.response = response;
	}

	public ApiException(ErrorResponse response) {
		super();
		this.response = response;
	}

	public ApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApiException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
