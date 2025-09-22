package com.repsrv.csweb.core.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class SuperPermRequest extends BaseRequest {

    public SuperPermRequest(String username) {
		super(username, CSWebAction.GET_ALL);
	}

    
    @JsonIgnore
	@Override
	public Object getRequestedObject() {
		return this;
	}


}
