package com.repsrv.csweb.core.aae.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuoteMetadata {

	@JsonProperty("projectID")
	private String projectId;
	
	@JsonProperty("qutoeNumber")
	private String quoteNumber;
	
	private String statusCode;
	
	private String assignedTo;
	
	private String quoteType;

	private String allowFinalize;

	private String allowUpdate;
	
	private String reason;
	
	private String formatType;
	
	@JsonProperty("lastvalidatedtimestamp")
	private String lastValidatedTimestamp;
	
}
