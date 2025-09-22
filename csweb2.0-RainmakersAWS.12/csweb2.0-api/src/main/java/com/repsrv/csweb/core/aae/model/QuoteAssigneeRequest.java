package com.repsrv.csweb.core.aae.model;

import java.util.HashMap;
import java.util.Map;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

public class QuoteAssigneeRequest extends StoredProcCallResult{

	private String quoteAssignedTo;

	public enum ActionType {
        ASSIGN,UNASSIGN
    } 
	
	Map<String, String> payload;
	
	public QuoteAssigneeRequest(String requestingUser, String quoteId, 
			String assignee, ActionType actionType) {
		super();
		payload = new HashMap<>();
		payload.put("requestedUser", requestingUser);
		payload.put("quoteId", quoteId);
		payload.put("userName", assignee);
		payload.put("assignment", actionType.name());
	}
	
	public String getJson() {
		return getJson(payload);
	}

	public String getQuoteAssignedTo() {
		return quoteAssignedTo;
	}

	public void setQuoteAssignedTo(String quoteAssignedTo) {
		this.quoteAssignedTo = quoteAssignedTo;
	}
}
