package com.repsrv.csweb.core.account;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.account.AccountSiteContactEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class SiteContactEditRequest extends BaseRequest {
	
	private AccountSiteContactEdit siteContactEdit;
	
	public SiteContactEditRequest(CSWebAction action, String requestedUser, AccountSiteContactEdit siteContactEdit) {
		super(requestedUser, action);
		
		Assert.notNull(siteContactEdit, "SiteContactEdit cannot be null");
		Assert.notNull(siteContactEdit.getAccount(), "Account must be specified");
		Assert.notNull(siteContactEdit.getCompany(), "Account must be specified");
		Assert.notNull(siteContactEdit.getSite(), "Account must be specified");
		
		this.siteContactEdit = siteContactEdit;
	}
	
	@Override
	@JsonProperty(value = "customerContactInformation")
	public Object getRequestedObject() {
		return this.siteContactEdit;
	}

}
