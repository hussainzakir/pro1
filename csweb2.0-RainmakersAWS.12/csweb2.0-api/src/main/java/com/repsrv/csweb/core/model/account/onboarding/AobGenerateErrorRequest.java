package com.repsrv.csweb.core.model.account.onboarding;

import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class AobGenerateErrorRequest {
    
	@JsonProperty("excelname")
	private String excelName;
	
	@JsonProperty("projectid")
	private String projectId;
}
