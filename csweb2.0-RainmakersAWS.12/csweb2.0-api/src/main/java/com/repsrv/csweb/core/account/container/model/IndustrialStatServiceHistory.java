package com.repsrv.csweb.core.account.container.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class IndustrialStatServiceHistory {

	@JsonProperty("Company")
	private String companyId;
	
	@JsonProperty("Acct_Number")
	private String accountId;
	
	@JsonProperty("Site")
	private String siteId;
	
	@JsonProperty("Date_Serviced")
	private String dateServiced;
	
	@JsonProperty("From_Acct_Number")
	private String fromAcctNumber;
	
	@JsonProperty("Travel_Distance")
	private String travelDistance;
	
	@JsonProperty("Service_Distance")
	private String serviceDistance;
	
	@JsonProperty("Service_Code")
	private String serviceCode;
	
	@JsonProperty("Disposal_Code")
	private String disposalCode;
	
	@JsonProperty("Disposal_Price_Code")
	private String disposalPriceCode; 
	
	@JsonProperty("Weight")
	private String weight;
	
	@JsonProperty("Weight_UM")
	private String weightUm;
	
	@JsonProperty("Route_Number")
	private String routeNumber;
	
	@JsonProperty("Travel_Time")
	private String travelTime;
	
	@JsonProperty("Start_Time")
	private String startTime;
	
	@JsonProperty("End_Time")
	private String endTime;
	
	@JsonProperty("Service_Time")
	private String serviceTime;
	
	@JsonProperty("Landfill_Time")
	private String landfillTime;

}
