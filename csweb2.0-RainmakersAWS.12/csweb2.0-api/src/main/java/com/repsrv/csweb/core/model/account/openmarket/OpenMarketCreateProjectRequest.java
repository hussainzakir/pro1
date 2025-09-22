package com.repsrv.csweb.core.model.account.openmarket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class OpenMarketCreateProjectRequest extends BaseRequest{

        private OpenMarketProjectCreationDTO data;
    
    @JsonIgnore
    private String projectId;
	
	public OpenMarketCreateProjectRequest(CSWebAction action, String requestedUser, OpenMarketProjectCreationDTO data) {
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
