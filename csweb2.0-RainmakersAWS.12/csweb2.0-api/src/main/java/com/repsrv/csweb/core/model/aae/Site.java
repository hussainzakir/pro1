package com.repsrv.csweb.core.model.aae;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
	
	private String siteStagingId;
	
	private String companyNumber;
	
	private String site;
	
	private String siteName;
	
	private String addressNumber;
	
	private String addressName;
	
	private String city;
	
	@JsonProperty("state")
	private String stateProvince;
	
	@JsonProperty("zipCode")
	private String postalCode;
	
	private String streetDirection;
	
	@JsonProperty("siteAddress1")
	private String addressNumberName;
	
	private String siteAddress2;
	
	private String streetNumber;
	
	private String streetType;
	
	private String addressSuite;
	
	private String serviceContact;
	
	@JsonProperty("serviceContactTitle")
	private String contactTitle;
	
	private String phoneNumber;
	
	private String phoneExtension;
	
	private String contractPriceIndex;
	
	@JsonProperty("contract#")
	private String contractNumber;
	
	@JsonProperty("csaTerm")
	private String term;
	
	private String termDescription;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	private String reviewDate;
	
	private String closeDate;
	
	@JsonProperty("CPIIndicator")
	private String cpiIndicator;
	
	private String contractStatus;
	
	@JsonProperty("contractStatusDesc")
	private String contractStatusDescription;
	
	private String purchaseOrder;
	
	private String authorizedBy;
	
	private String authorizedByTitle;
	
	private String sicCode;
	
	@JsonProperty("SICCodeDesc")
	private String sicCodeDescription;
	
	private String salesRep;
	
	private String salesRepName;
	
	private String sharedContainers;
	
	private String territoryCode;
	
	@JsonProperty("territoryCodeDesc")
	private String territoryCodeDescription;
	
	private String taxCode;
	
	@JsonProperty("taxCodeDesc")
	private String taxCodeDescription;
	
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
	
	private String faxNumber;
	
	private String naics;
	
	private String preDirectional;
	
	private List<Container> containers;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getAddressNumberName() {
		return addressNumberName;
	}

	public void setAddressNumberName(String addressNumberName) {
		this.addressNumberName = addressNumberName;
	}

	public String getSiteAddress2() {
		return siteAddress2;
	}

	public void setSiteAddress2(String siteAddress2) {
		this.siteAddress2 = siteAddress2;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getAddressSuite() {
		return addressSuite;
	}

	public void setAddressSuite(String addressSuite) {
		this.addressSuite = addressSuite;
	}

	public String getServiceContact() {
		return serviceContact;
	}

	public void setServiceContact(String serviceContact) {
		this.serviceContact = serviceContact;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getContractPriceIndex() {
		return contractPriceIndex;
	}

	public void setContractPriceIndex(String contractPriceIndex) {
		this.contractPriceIndex = contractPriceIndex;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTermDescription() {
		return termDescription;
	}

	public void setTermDescription(String termDescription) {
		this.termDescription = termDescription;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getCpiIndicator() {
		return cpiIndicator;
	}

	public void setCpiIndicator(String cpiIndicator) {
		this.cpiIndicator = cpiIndicator;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractStatusDescription() {
		return contractStatusDescription;
	}

	public void setContractStatusDescription(String contractStatusDescription) {
		this.contractStatusDescription = contractStatusDescription;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public String getAuthorizedByTitle() {
		return authorizedByTitle;
	}

	public void setAuthorizedByTitle(String authorizedByTitle) {
		this.authorizedByTitle = authorizedByTitle;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getSicCodeDescription() {
		return sicCodeDescription;
	}

	public void setSicCodeDescription(String sicCodeDescription) {
		this.sicCodeDescription = sicCodeDescription;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public String getSalesRepName() {
		return salesRepName;
	}

	public void setSalesRepName(String salesRepName) {
		this.salesRepName = salesRepName;
	}

	public String getSharedContainers() {
		return sharedContainers;
	}

	public void setSharedContainers(String sharedContainers) {
		this.sharedContainers = sharedContainers;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public String getTerritoryCodeDescription() {
		return territoryCodeDescription;
	}

	public void setTerritoryCodeDescription(String territoryCodeDescription) {
		this.territoryCodeDescription = territoryCodeDescription;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxCodeDescription() {
		return taxCodeDescription;
	}

	public void setTaxCodeDescription(String taxCodeDescription) {
		this.taxCodeDescription = taxCodeDescription;
	}

	public String getTaxExempt() {
		return taxExempt;
	}

	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getOriginalStartDate() {
		return originalStartDate;
	}

	public void setOriginalStartDate(String originalStartDate) {
		this.originalStartDate = originalStartDate;
	}

	public String getSiteContactName() {
		return siteContactName;
	}

	public void setSiteContactName(String siteContactName) {
		this.siteContactName = siteContactName;
	}

	public String getSiteContactEmail() {
		return siteContactEmail;
	}

	public void setSiteContactEmail(String siteContactEmail) {
		this.siteContactEmail = siteContactEmail;
	}

	public String getSiteContactAreaCode1() {
		return siteContactAreaCode1;
	}

	public void setSiteContactAreaCode1(String siteContactAreaCode1) {
		this.siteContactAreaCode1 = siteContactAreaCode1;
	}

	public String getSiteContactTelephone1() {
		return siteContactTelephone1;
	}

	public void setSiteContactTelephone1(String siteContactTelephone1) {
		this.siteContactTelephone1 = siteContactTelephone1;
	}

	public String getSiteContactTelephoneExtn1() {
		return siteContactTelephoneExtn1;
	}

	public void setSiteContactTelephoneExtn1(String siteContactTelephoneExtn1) {
		this.siteContactTelephoneExtn1 = siteContactTelephoneExtn1;
	}

	public String getSiteContactType1() {
		return siteContactType1;
	}

	public void setSiteContactType1(String siteContactType1) {
		this.siteContactType1 = siteContactType1;
	}

	public String getSiteContactAreaCode2() {
		return siteContactAreaCode2;
	}

	public void setSiteContactAreaCode2(String siteContactAreaCode2) {
		this.siteContactAreaCode2 = siteContactAreaCode2;
	}

	public String getSiteContactTelephone2() {
		return siteContactTelephone2;
	}

	public void setSiteContactTelephone2(String siteContactTelephone2) {
		this.siteContactTelephone2 = siteContactTelephone2;
	}

	public String getSiteContactTelephoneExtn2() {
		return siteContactTelephoneExtn2;
	}

	public void setSiteContactTelephoneExtn2(String siteContactTelephoneExtn2) {
		this.siteContactTelephoneExtn2 = siteContactTelephoneExtn2;
	}

	public String getSiteContactType2() {
		return siteContactType2;
	}

	public void setSiteContactType2(String siteContactType2) {
		this.siteContactType2 = siteContactType2;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public String getSiteStagingId() {
		return siteStagingId;
	}

	public void setSiteStagingId(String siteStagingId) {
		this.siteStagingId = siteStagingId;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getSiteContactTitle() {
		return siteContactTitle;
	}

	public void setSiteContactTitle(String siteContactTitle) {
		this.siteContactTitle = siteContactTitle;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getNaics() {
		return naics;
	}

	public void setNaics(String naics) {
		this.naics = naics;
	}

	public String getSiteContactFaxAreaCode() {
		return siteContactFaxAreaCode;
	}

	public void setSiteContactFaxAreaCode(String siteContactFaxAreaCode) {
		this.siteContactFaxAreaCode = siteContactFaxAreaCode;
	}

	public String getSiteContactFaxNumber() {
		return siteContactFaxNumber;
	}

	public void setSiteContactFaxNumber(String siteContactFaxNumber) {
		this.siteContactFaxNumber = siteContactFaxNumber;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getPreDirectional() {
		return preDirectional;
	}

	public void setPreDirectional(String preDirectional) {
		this.preDirectional = preDirectional;
	}
	
}
