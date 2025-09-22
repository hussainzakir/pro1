package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.ContainerEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeContainerEditRequest extends BaseRequest{
	
	private ContainerEdit containerEdit;
	
	public AaeContainerEditRequest(CSWebAction action, String requestedUser, ContainerEdit containerEdit) {
		super(requestedUser, action);
		
		Assert.notNull(containerEdit,"ContainerEdit cannot be null");
		
		this.containerEdit = containerEdit;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.containerEdit;
	}

}
