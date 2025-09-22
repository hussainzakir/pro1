package com.repsrv.csweb.core.model.aae;

import java.util.List;

public class ErrorResponse {
	
	private String entity;
	private String status;
	
	private List<Error> errors;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
