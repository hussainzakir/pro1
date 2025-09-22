package com.repsrv.csweb.core.support.exception.massuploads;

import java.util.Collection;

import com.repsrv.csweb.core.massuploads.template.RowErrorLog;
import com.repsrv.csweb.core.model.massupload.MuTemplate;

public class TemplateValidationException extends Exception{

	private Collection<RowErrorLog> errors;
	private MuTemplate template;
	
	public TemplateValidationException() {
		super();
	}

	public TemplateValidationException(String message, Collection<RowErrorLog> errors, MuTemplate template) {
		super(message);
		this.errors = errors;
		this.template = template;
	}

	public Collection<RowErrorLog> getErrors() {
		return errors;
	}

	public MuTemplate getTemplate() {
		return template;
	}




}
