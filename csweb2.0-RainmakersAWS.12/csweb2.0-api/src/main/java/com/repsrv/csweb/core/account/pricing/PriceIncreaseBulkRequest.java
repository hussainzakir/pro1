package com.repsrv.csweb.core.account.pricing;

import java.util.List;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.account.pricing.model.PriceIncreaseRow;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class PriceIncreaseBulkRequest extends BaseRequest{
	public static final int maxRecordsPerRequest = 500;

	private List<PriceIncreaseRow> records;
	
	public PriceIncreaseBulkRequest(CSWebAction action, String requestedUser, List<PriceIncreaseRow> records) {
		super(requestedUser, action);
		
		Assert.isTrue(records!=null && !records.isEmpty(), "PriceIncrease list cannot be empty");
		Assert.isTrue(records.size() <= maxRecordsPerRequest, "The max records to save is "+maxRecordsPerRequest);

		this.records = records;
	}
	
	@Override
	@JsonProperty(value = "pricingData")
	public Object getRequestedObject() {
		return this.records;
	}
	
}
