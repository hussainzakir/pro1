package com.repsrv.csweb.core.account;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.account.AccountContactEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class AccountContactEditRequest extends BaseRequest {

	private AccountContactEdit contactEdit;
	
	public AccountContactEditRequest(CSWebAction action, String requestedUser, AccountContactEdit contactEdit) {
		super(requestedUser, action);
		
		Assert.notNull(contactEdit, "AccountContactEdit cannot be null");
		Assert.notNull(contactEdit.getAccount(), "Account must be specified");
		Assert.notNull(contactEdit.getCompany(), "Company must be specified");
		Assert.notNull(contactEdit.getSite(), "Site must be specified");
		
		this.contactEdit = contactEdit;
	}
	
	@Override
	@JsonProperty(value = "customerContactInformation")
	public Object getRequestedObject() {
		return this.contactEdit;
	}

	
	

}
