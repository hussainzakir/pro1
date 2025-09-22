package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.RateCreate;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeRateCreateRequest extends BaseRequest{
	
	private RateCreate rateCreate;
	
	public AaeRateCreateRequest(CSWebAction action, String requestedUser, RateCreate rateCreate) {
		super(requestedUser, action);
		
		Assert.notNull(rateCreate, "RateCreate cannot be null");
		
		this.rateCreate = rateCreate;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.rateCreate;
	}

}

