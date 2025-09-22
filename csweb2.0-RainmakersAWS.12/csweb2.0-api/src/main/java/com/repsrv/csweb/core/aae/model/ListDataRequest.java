package com.repsrv.csweb.core.aae.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class ListDataRequest extends BaseRequest {

    private Map<String, String> payload;

    public ListDataRequest(CSWebAction action, String requestedUser, String division) {
		super(requestedUser, action);
        this.payload = new HashMap<>();
        this.payload.put("companyNumber", division);
	}

    @Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
        return this.payload;
	}
}
