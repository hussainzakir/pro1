package com.repsrv.csweb.core.account.imports3.model;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class Aob3ProjectDetailsRequest extends BaseRequest {

    @JsonIgnore 
    private String projectId;

    public Aob3ProjectDetailsRequest(CSWebAction action, String requestedUser, String projectId) {
        super(requestedUser, action);
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return Collections.singletonMap("projectId", projectId);
	}

}