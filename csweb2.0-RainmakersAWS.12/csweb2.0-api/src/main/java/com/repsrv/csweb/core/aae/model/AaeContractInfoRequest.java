package com.repsrv.csweb.core.aae.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeContractInfoRequest extends BaseRequest {
		
	Map<String, String> payload;
	
	public AaeContractInfoRequest(CSWebAction action, String requestedUser, String quoteId) {
		super(requestedUser, action);
		payload = new HashMap<>();
		payload.put("quoteId", quoteId);
		
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.payload;
	}

}

