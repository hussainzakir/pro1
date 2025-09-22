package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.AaeErrorReport;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeErrorReportRequest extends BaseRequest{
	
	private AaeErrorReport errorReport;
	
	public AaeErrorReportRequest(CSWebAction action, String requestedUser, AaeErrorReport errorReport) {
		super(requestedUser, action);
		
		Assert.notNull(errorReport, "ErrorReport cannot be null");
		
		this.errorReport = errorReport;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.errorReport;
	}

}
