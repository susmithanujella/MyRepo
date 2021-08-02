package com.centime.CentimeDemo.exception;


public class RestApiException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public RestApiException() {
		super();
	}

	public RestApiException(String message) {
		super(message);
	}
	
	public RestApiException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public RestApiException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public RestApiException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}