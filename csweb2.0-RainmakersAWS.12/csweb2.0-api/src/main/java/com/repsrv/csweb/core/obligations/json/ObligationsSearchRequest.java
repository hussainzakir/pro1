package com.repsrv.csweb.core.obligations.json;

import com.repsrv.csweb.core.model.json.BaseActionRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ObligationsSearchRequest extends BaseActionRequest {

	private Map<String, String> object = new HashMap<>();
	public ObligationsSearchRequest(String user, CSWebAction action, String regionCode, String obligationId,
									String accountingPeriodFrom, String accountingPeriodTo,
									String amountRangeFrom, String amountRangeTo) {
		super(user, action); //CSWebAction.GET_ALL
		Assert.notNull(regionCode, "regionCode cannot be null");
		this.object.put("regionCode", regionCode);
		this.object.put("obligationId", obligationId);
		this.object.put("accountingPeriodFrom", accountingPeriodFrom);
		this.object.put("accountingPeriodTo", accountingPeriodTo);
		this.object.put("amountRangeFrom", amountRangeFrom);
		this.object.put("amountRangeTo", amountRangeTo);
	}

	@Override
	@JsonProperty(value = "obligationInformation")
	public Object getRequestObject() {
		return this.object;
	}
}



