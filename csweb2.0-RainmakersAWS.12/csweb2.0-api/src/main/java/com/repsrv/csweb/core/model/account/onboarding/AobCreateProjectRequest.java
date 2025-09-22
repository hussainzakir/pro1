package com.repsrv.csweb.core.model.account.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class AobCreateProjectRequest extends BaseRequest{
    

    private AobProjectCreationDTO data;
    
    @JsonIgnore
    private String projectId;
	
	public AobCreateProjectRequest(CSWebAction action, String requestedUser, AobProjectCreationDTO data) {
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
