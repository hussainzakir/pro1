package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.RateEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeRateEditRequest extends BaseRequest{
	
	private RateEdit rateEdit;
	
	public AaeRateEditRequest(CSWebAction action, String requestedUser, RateEdit rateEdit) {
		super(requestedUser, action);
		
		Assert.notNull(rateEdit, "RateEdit cannot be null");
		
		this.rateEdit = rateEdit;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.rateEdit;
	}

}
