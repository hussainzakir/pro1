package com.repsrv.csweb.core.model.json;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class BaseJsonResponse {

	private boolean hasError = false;
	private String errorMessage;
	private Collection<?> errors;
	
	public BaseJsonResponse() {}
	
	public boolean isHasError() {
		return hasError;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		
		this.hasError = StringUtils.isNotBlank(this.errorMessage);
	}
	
	public Collection<?> getErrors() {
		return errors;
	}

	public void setErrors(Collection errors) {
		this.errors = errors;
	}
	
	
}
