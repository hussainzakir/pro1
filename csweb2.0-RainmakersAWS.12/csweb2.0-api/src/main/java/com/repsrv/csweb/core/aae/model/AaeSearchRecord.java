package com.repsrv.csweb.core.aae.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AaeSearchRecord {

	  private long projectID;
	  
	  private String quoteID;
	  
	  private long oracleDivision;
	  
	  @JsonProperty("Company")
	  private String companyNumber;
	  
	  private String accountNumber;
	  
	  private String accountName;
	  
	  private String siteName;
	  
	  private String siteAddress;
	  
	  private String statusCode;
	  
	  private String createDate;

	  private String notesFound;
	  
	  private String effectiveDate; //could be number
	  
	  private String salesRep;
	  
	  private String sourceSystem;
	  
	  @JsonProperty("consolidatedAAE")
	  private String consolidatedAae;
	  
	  private String quoteType;
	  
	  private String assignedTo;
}
