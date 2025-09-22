package com.repsrv.csweb.core.aae.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class StatusCodeRequest extends BaseRequest{

    private Map<String, String> payload;

    public StatusCodeRequest(CSWebAction action, String requestedUser, String statusCd, String projectId, String note) {
		super(requestedUser, action);
        this.payload = new HashMap<>();
        this.payload.put("StatusCd", statusCd);
        this.payload.put("ProjectId", projectId);
        this.payload.put("notes", note);
	}

    @JsonIgnore
    String previousStatusCode;
	
	public String getPreviousStatusCode() {
        return previousStatusCode;
    }

    public void setPreviousStatusCode(String previousStatusCode) {
        this.previousStatusCode = previousStatusCode;
    }

    @Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
        return this.payload;
	}
}
