package com.repsrv.csweb.core.model.account.aob3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class Aob3CreateProjectRequest extends BaseRequest{
    

    private Aob3ProjectCreationDTO data;
    
    @JsonIgnore
    private String projectId;
	
	public Aob3CreateProjectRequest(CSWebAction action, String requestedUser, Aob3ProjectCreationDTO data) {
		super(requestedUser, action);
		
	this.data = data;
    }

	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.data;
	}

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
}