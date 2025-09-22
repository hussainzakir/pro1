package com.repsrv.csweb.core.aae.model;

import java.util.HashMap;
import java.util.Map;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

public class QuoteMetadataRequest extends StoredProcCallResult{

	private Map<String, String> payload;
	
	public QuoteMetadataRequest(String quoteId, String userId){
		super();
		payload = new HashMap<>();
		payload.put("quoteID", quoteId);
		payload.put("requestedUser", userId);
	}
	
	public String getJson() {
		return getJson(payload);
	}
}
