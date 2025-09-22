package com.repsrv.csweb.core.account;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.account.AccountDetailEdit;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;


public class AccountDetailEditRequest extends BaseRequest {

	private AccountDetailEdit detail;
	
	public AccountDetailEditRequest(CSWebAction action, String requestedUser, AccountDetailEdit detail) {
		super(requestedUser, action);
		
		Assert.notNull(detail, "AccountEditDetail cannot be null");
		Assert.notNull(detail.getAccount(), "Account must be specified");
		Assert.notNull(detail.getCompany(), "Company must be specified");
		
		this.detail = detail;
	}
	
	@Override
	@JsonProperty(value = "nameAddressInformation")
	public Object getRequestedObject() {
		return this.detail;
	}

	
	

}
