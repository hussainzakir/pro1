package com.repsrv.csweb.core.account.servicerecordings.model;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Builder(builderMethodName = "internalBuilder")
@Slf4j
public class F2NoteRequest extends StoredProcCallResult{
	
	private String groupId = UUID.randomUUID().toString();
	
	private String jobName;
	
	private String infoproDivision; //company
	
	private String userName;
	
	private String clientId;
	
	private String easJobId;
	
	private String accountId;
	
	private String siteId;
	
	private String transactionCode;
	
	private String caseCreatedBy;//username same value
	
	private String requestDescription;
	
	private String resolutionComment;
	
	private String departmentCode;
	
	private String openClose;
	
	private String sourceSystem;
	
	private String sourceSystemId;
	
	private String subjectName;
	
	private String priority;
	
	private String escalation;
	
	private String route;
	
	private String driver;
	
	private String truck;
	
	private String planDate;
	
	
	public static F2NoteRequestBuilder builder(String userName) {
        return internalBuilder()
        		.caseCreatedBy(userName)
        		.userName(userName);
    }
	
	@JsonIgnore
	public String getJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("Failed to generate JSON string for request", e);
		}
		return null;
	}
	
}
