package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.AaeExceptionReport;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeExceptionReportRequest extends BaseRequest{
	
	private AaeExceptionReport exceptionReport;
	
	public AaeExceptionReportRequest(CSWebAction action, String requestedUser, AaeExceptionReport exceptionReport) {
		super(requestedUser, action);
		
		Assert.notNull(exceptionReport, "ErrorReport cannot be null");
		
		this.exceptionReport = exceptionReport;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.exceptionReport;
	}

}

