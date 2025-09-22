package com.repsrv.csweb.core.model.account.aob3;

import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class Aob3GenerateErrorRequest {
    
	@JsonProperty("excelname")
	private String excelName;
	
	@JsonProperty("projectid")
	private String projectId;
}
