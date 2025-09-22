package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.AccountEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeAccountEditRequest extends BaseRequest{
	
	private AccountEdit accountEdit;
	
	public AaeAccountEditRequest(CSWebAction action, String requestedUser, AccountEdit accountEdit) {
		super(requestedUser, action);
		
		Assert.notNull(accountEdit,"AccountEdit cannot be null");
		
		this.accountEdit = accountEdit;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.accountEdit;
	}

}
