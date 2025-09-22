package com.repsrv.csweb.core.model.aae;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchParams {

	private String quoteID;
	
	private String oracleDivision;
	
	private String companyNumber;
	
	private String customerAccount;
	
	private String salesRep;
	
	private String statusCode;
	
	private String crc;
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private String includeHeld;
	
	private String includeDeleted;
	
	private String consolidatedAae;
	
	private String quoteType;
	
	private String createDate;
	
	private String assignee;

	private String formatType;

	private String requestUser;

	@Override
	public String toString() {
		return "SearchParams [quoteID=" + quoteID + ", oracleDivision=" + oracleDivision + ", companyNumber="
				+ companyNumber + ", customerAccount=" + customerAccount + ", salesRep=" + salesRep + ", statusCode="
				+ statusCode + ", crc=" + crc + ", effectiveFrom=" + effectiveFrom + ", effectiveTo=" + effectiveTo
				+ ", includeHeld=" + includeHeld + ", includeDeleted=" + includeDeleted + ", consolidatedAae="
				+ consolidatedAae + ", quoteType=" + quoteType  + ", createDate=" + createDate + ", assignee=" 
				+ assignee + ", formatType="+ formatType + ", requestUser="+ requestUser + "]";
	}

}
