package com.repsrv.csweb.core.model.account.openmarket;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class OpenMarketDeleteRequest extends BaseRequest {

	private OpenMarketDelRequest openMarketDelRequest;

	public OpenMarketDeleteRequest(CSWebAction action, String requestedUser, OpenMarketDelRequest openMarketDelRequest) {
		super(requestedUser, action);
		
		Assert.notNull(openMarketDelRequest, "Delete Request cannot be null");
		
		this.openMarketDelRequest = openMarketDelRequest;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.openMarketDelRequest;
	}

}
	
	


