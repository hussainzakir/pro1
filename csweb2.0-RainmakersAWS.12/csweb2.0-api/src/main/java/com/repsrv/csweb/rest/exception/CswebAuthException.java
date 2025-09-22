package com.repsrv.csweb.rest.exception;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

public class CswebAuthException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	private final String loginErrorType;
	private final List<String> errorMessages;
	
	public CswebAuthException(String error, List<String> errorMessages) {
		super(error);
		this.loginErrorType = error;
		this.errorMessages = errorMessages;
	}

	public String getLoginErrorType() {
		return loginErrorType;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

}
