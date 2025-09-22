package com.repsrv.csweb.core.model.account.imports;


import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImportHistory {

	@JsonProperty("excelname")
	private String excelName;
	
	@JsonProperty("userid")
	private String userId;
	
	@JsonProperty("changeticket")
	private String changeTicketNum;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("division")
	private String division;
	
	@JsonProperty("library")
	private String library;
	
	@JsonProperty("seq")
	private String seq;
	
	@JsonProperty("submitteddate")
	private String submittedDate;
	
	@JsonProperty("lastmodified")
	private String lastModified;
	
	@JsonProperty("numofaddrs")
	private String addressCount;
	
	@JsonProperty("numoferrors")
	private String errorsCount;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("accounttab")
	private String accountTab;
	
	@JsonProperty("containertab")
	private String containerTab;
	
	@JsonProperty("sitetab")
	private String siteTab;
	
	@JsonProperty("ratetab")
	private String rateTab;
	
	@JsonProperty("routetab")
	private String routeTab;
	
}
