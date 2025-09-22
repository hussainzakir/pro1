package com.repsrv.csweb.core.account;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.account.SiteDetailEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class SiteDetailEditRequest extends BaseRequest {
	
	private SiteDetailEdit detailEdit;
	
	public SiteDetailEditRequest(CSWebAction action, String requestedUser, SiteDetailEdit detailEdit) {
		super(requestedUser, action);
		
		Assert.notNull(detailEdit, "SiteDetailEdit cannot be null");
		Assert.notNull(detailEdit.getAccount(), "Account must be specified");
		Assert.notNull(detailEdit.getCompany(), "Company must be specified");
		Assert.notNull(detailEdit.getSite(), "Site must be specified");
		
		this.detailEdit = detailEdit;
	}
	
	@Override
	@JsonProperty(value = "siteAddressInformation")
	public Object getRequestedObject() {
		return this.detailEdit;
	}

}
