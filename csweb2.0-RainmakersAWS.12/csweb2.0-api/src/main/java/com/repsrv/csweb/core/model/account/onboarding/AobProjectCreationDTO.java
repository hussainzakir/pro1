package com.repsrv.csweb.core.model.account.onboarding;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AobProjectCreationDTO {
    
	@JsonProperty("projectName")
    private String projectName;
	
	@JsonProperty("fileName")
	private String fileName;
	
	@JsonProperty("numberOfAccounts")
	private String numberOfAccounts;	
	
	@JsonProperty("numberOfSites")
	private String numberOfSites;
	
    @JsonProperty("numberOfContainers")
	private String numberOfContainers;
	
    @JsonProperty("numberOfRates")
	private String numberOfRates;
	

}
