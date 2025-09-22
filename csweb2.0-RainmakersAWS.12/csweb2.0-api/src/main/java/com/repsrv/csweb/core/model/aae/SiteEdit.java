package com.repsrv.csweb.core.model.aae;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SiteEdit {
	
	private String stagingId;
	
	private String companyNumber;
	
	private String site;
	
	private String siteName;
	
	private String addressNumber;
	
	private String addressName;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private String streetDirection;
	
	private String siteAddress1;
	
	private String siteAddress2;
	
	private String streetNumber;
	
	private String streetType;
	
	private String addressSuite;
	
	private String serviceContact;
	
	private String serviceContactTitle;
	
	private String naics;
	
	private String phoneNumber;
	
	private String phoneExtension;
	
	private String faxNumber;
	
	private String contractPriceIndex;
	
	@JsonProperty("contract#")
	private String contractNumber;
	
	private String csaTerm;
	
	private String termDescription;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	private String reviewDate;
	
	private String closeDate;
	
	@JsonProperty("CPIIndicator")
	private String cpiIndicator;
	
	private String contractStatus;
	
	private String contractStatusDesc;
	
	private String purchaseOrder;
	
	private String authorizedBy;
	
	private String authorizedByTitle;
	
	private String sicCode;
	
	private String sicCodeDesc;
	
	private String salesRep;
	
	private String salesRepName;
	
	private String sharedContainers;
	
	private String territoryCode;
	
	private String territoryCodeDesc;
	
	private String taxCode;
	
	private String taxCodeDesc;
	
	private String taxExempt;
	
	private String lastUpdatedDate;
	
	private String originalStartDate;
	
	private String siteContactName;
	
	private String siteContactTitle;
	
	private String siteContactEmail;
	
	private String siteContactAreaCode1;
	
	private String siteContactTelephone1;
	
	private String siteContactTelephoneExtn1;
	
	private String siteContactType1;
	
	private String siteContactAreaCode2;
	
	private String siteContactTelephone2;

	private String siteContactTelephoneExtn2;
	
	private String siteContactType2;
	
	private String siteContactFaxAreaCode;
	
	private String siteContactFaxNumber;
	
	private String preDirectional;

}
