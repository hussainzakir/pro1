package com.repsrv.csweb.core.model.search;

import java.util.List;

import static com.repsrv.csweb.core.util.AS400DateUtil.format8DigitDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.util.AS400DateUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {

	private String site;
	
	private String siteName;
	
	private String city;
	
	private String addressName;
	
	private String stateProvince;
	
	private String postalCode;
	
	private String streetDirection;
	
	@JsonProperty("addressNbrName")
	private String addressNumberName;
	
	private int lastUpdateDate;
	
	private String serviceContact;
	
	private String contactTitle;
	
	private String phoneNumber;
	
	private int phoneExtension;
	
	private String contractPriceIndex;
	
	private String closeDate;
	
	@JsonProperty("contract#")
	private String contractNumber;
	
	@JsonProperty("Term")
	private String term;
	
	@JsonProperty("effectiveDate")
	private String effectiveDate;
	
	@JsonProperty("expiryDate")
	private String expiryDate;
	
	@JsonProperty("reviewDate")
	private String reviewDate;
	
	@JsonProperty("CPIIndicator")
	private String cpiIndicator;
	
	@JsonProperty("contractStatus")
	private String contractStatus;
	
	@JsonProperty("contractStatusDesc")
	private String contractStatusDesc;
	
	@JsonProperty("purchaseOrder")
	private String purchaseOrder;
	
	@JsonProperty("authorizedBy")
	private String authorizedBy;
	
	@JsonProperty("SICCode")
	private String sicCode;
	
	@JsonProperty("salesRep")
	private String salesRep;
	
	@JsonProperty("sharedContainers")
	private String sharedContainers;
	
	@JsonProperty("territoryCode")
	private String territoryCode;
	
	@JsonProperty("territoryCodeDesc")
	private String territoryCodeDesc;
	
	@JsonProperty("taxCode")
	private String taxCode;
	
	@JsonProperty("taxExempt")
	private String taxExempt;
	
	@JsonProperty("lastUpdated")
	private String lastUpdated;
	
	@JsonProperty("termDescription")
	private String termDescription;
	
	@JsonProperty("authorizedByTitle")
	private String authorizedByTitle;
	
	@JsonProperty("SICCodeDesc")
	private String sicCodeDesc;

	@JsonProperty("salesRepName")
	private String salesRepName;
	
	@JsonProperty("taxCodeDesc")
	private String taxCodeDesc;

	private String vendorNumber;

	private String vendorSubtype;
	
	private String siteAddress2;
	
	private String streetNumber;
	
	private String streetType;
	
	private String addressSuite;
	
	@JsonProperty("originalStartDate")
	private String originalStartDate;
	
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

	public int getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(int lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	public int getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(int phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getContractPriceIndex() {
		return contractPriceIndex;
	}

	public void setContractPriceIndex(String contractPriceIndex) {
		this.contractPriceIndex = contractPriceIndex;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = format8DigitDate(closeDate);
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
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

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = format8DigitDate(effectiveDate);
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = format8DigitDate(expiryDate);
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = format8DigitDate(reviewDate);
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

	public String getContractStatusDesc() {
		return contractStatusDesc;
	}

	public void setContractStatusDesc(String contractStatusDesc) {
		this.contractStatusDesc = contractStatusDesc;
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

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
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

	public String getTerritoryCodeDesc() {
		return territoryCodeDesc;
	}

	public void setTerritoryCodeDesc(String territoryCodeDesc) {
		this.territoryCodeDesc = territoryCodeDesc;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxExempt() {
		return taxExempt;
	}

	public void setTaxExempt(String taxExempt) {
		this.taxExempt = taxExempt;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = format8DigitDate(lastUpdated);
	}

	public String getTermDescription() {
		return termDescription;
	}

	public void setTermDescription(String termDescription) {
		this.termDescription = termDescription;
	}

	public String getAuthorizedByTitle() {
		return authorizedByTitle;
	}

	public void setAuthorizedByTitle(String authorizedByTitle) {
		this.authorizedByTitle = authorizedByTitle;
	}

	public String getSicCodeDesc() {
		return sicCodeDesc;
	}

	public void setSicCodeDesc(String sicCodeDesc) {
		this.sicCodeDesc = sicCodeDesc;
	}

	public String getSalesRepName() {
		return salesRepName;
	}

	public void setSalesRepName(String salesRepName) {
		this.salesRepName = salesRepName;
	}

	public String getTaxCodeDesc() {
		return taxCodeDesc;
	}

	public void setTaxCodeDesc(String taxCodeDesc) {
		this.taxCodeDesc = taxCodeDesc;
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

	public String getOriginalStartDate() {
		return originalStartDate;
	}

	public void setOriginalStartDate(String originalStartDate) {
		this.originalStartDate = AS400DateUtil.format8DigitDate(originalStartDate);
	}

	public String getVendorNumber() {
		return vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	public String getVendorSubtype() {
		return vendorSubtype;
	}

	public void setVendorSubtype(String vendorSubtype) {
		this.vendorSubtype = vendorSubtype;
	}
	
}
