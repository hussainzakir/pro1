package com.repsrv.csweb.core.aae.model;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.SiteEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeSiteEditRequest extends BaseRequest{
	
	private SiteEdit siteEdit;
	
	public AaeSiteEditRequest(CSWebAction action, String requestedUser, SiteEdit siteEdit) {
		super(requestedUser, action);
		
		Assert.notNull(siteEdit,"SiteEdit cannot be null");
		
		this.siteEdit = siteEdit;
	}
	
	@Override
	@JsonProperty(value = "data")
	public Object getRequestedObject() {
		return this.siteEdit;
	}

}
