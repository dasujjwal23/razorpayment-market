package com.dell.market.entity;

public class ErrorOutResponse {

	public Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public ErrorOutResponse(Error error) {
		super();
		this.error = error;
	}

	public ErrorOutResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ErrorResponse [error=" + error + "]";
	}
		
}
