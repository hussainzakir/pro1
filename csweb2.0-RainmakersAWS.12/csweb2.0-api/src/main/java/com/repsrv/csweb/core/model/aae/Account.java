package com.repsrv.csweb.core.model.aae;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	
	private String accountStagingId;
	
	@JsonProperty("companyNumber")
	private String company;
	
	@JsonProperty("customerAccount")
	private String account;
	
	private String attention;
	
	private String accountAlias;
	
	private String customerName;
	
	private String streetType;
	
	private String streetDirection;
	
	@JsonProperty("addressNo")
	private String addressNumber;
	
	private String addressName;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String addressLine3;
	
	@JsonProperty("Telephone")
	private String telephone;
	
	private String phoneExtension;
	
	@JsonProperty("Fax")
	private String fax;
	
	private String city;
	
	private String state;
	
	private String postalCode;
	
	private String customerCategory;
	
	@JsonProperty("parcelNo")
	private String parcelNumber;
	
	private String accountClassTable;
	
	private String cashTolerance;
	
	private String chainCode;
	
	private String lastUpdated;
	
	private String invoiceDestination;
	
	private String invoicePageBreak;
	
	private String remitCode;
	
	private String printMethod;
	
	@JsonProperty("printMethodDesc")
	private String printMethodDescription;
	
	private String invoiceSiteTotals;
	
	private String invoiceSiteTaxes;
	
	private String invoiceContainerTotals;
	
	private String invoiceContainerTaxes;
	
	private String acquisitionCode;
	
	private String languageCode;
	
	@JsonProperty("languageDesc")
	private String languageDescription;
	
	@JsonProperty("SIEligible")
	private String siEligible;
	
	@JsonProperty("SIStatus")
	private String siStatus;
	
	@JsonProperty("SIStartDate")
	private String siStartDate;
	
	@JsonProperty("SIStatusDesc")
	private String siStatusDescription;
	
	private String invoiceGroupCode;
	
	private String creditPolicy;
	
	@JsonProperty("riskCodeDesc")
	private String riskCodeDescription;
	
	private String originalStartDate;
	
	private String lastInvoiceDate;
	
	private String dunningDate;
	
	private String dunningStage;
	
	private String creditAnalyst;
	
	@JsonProperty("chargeAdminFee")
	private String chargeAdministrativeFee;
	
	@JsonProperty("adminFeeExc")
	private String administrativeFeeExc;
	
	@JsonProperty("chargeServIntFee")
	private String chargeServiceIntFee;

	@JsonProperty("servIntFeeExc")
	private String serviceIntFeeExc;
	
	private String chargeLateFee;
	
	private String lateFeeExc;
	
	private String lateFeePolicy;
	
	private String chargeFuelFee;
	
	private String fuelFeeExc;
	
	private String nextReviewDate;
	
	@JsonProperty("chargeEnvrFee")
	private String chargeEnvironmentalFee;
	
	@JsonProperty("EnvrFeeExc")
	private String environmentalFeeExc;
	
	@JsonProperty("dupCopy")
	private String duplicateCopy;
	
	@JsonProperty("EDIReq")
	private String ediRequired;
	
	private String brokerCode;
	
	@JsonProperty("accountGroupID")
	private String accountGroupId;
	
	private String parentD;
	
	private String onlineAccountFlag;
	
	private String accountContactName;
	
	private String accountContactTitle;
	
	private String accountContactEmail;
	
	private String accountContactAreaCode1;
	
	private String accountContactTelephone1;
	
	private String accountContactTelephoneExtn1;
	
	private String accountContactType1;
	
	private String accountContactAreaCode2;
	
	private String accountContactTelephone2;
	
	private String accountContactTelephoneExtn2;
	
	private String accountContactType2;
	  
	@JsonProperty("ERFFRFFlag")
	private String erfFrfFlag;
	
	private String taxCode;
	
	private String distributionCode;
	
	private String onlinePaymentProfile;
	
	private String accountClassDesc;
	
	private String acquisitionCodeDesc;
	
	private String formatType;
	
	private List<Site> sites;
	
	public String getAccountStagingId() {
		return accountStagingId;
	}

	public void setAccountStagingId(String accountStagingId) {
		this.accountStagingId = accountStagingId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	
	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getParcelNumber() {
		return parcelNumber;
	}

	public void setParcelNumber(String parcelNumber) {
		this.parcelNumber = parcelNumber;
	}

	public String getAccountClassTable() {
		return accountClassTable;
	}

	public void setAccountClassTable(String accountClassTable) {
		this.accountClassTable = accountClassTable;
	}

	public String getCashTolerance() {
		return cashTolerance;
	}

	public void setCashTolerance(String cashTolerance) {
		this.cashTolerance = cashTolerance;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getInvoiceDestination() {
		return invoiceDestination;
	}

	public void setInvoiceDestination(String invoiceDestination) {
		this.invoiceDestination = invoiceDestination;
	}

	public String getInvoicePageBreak() {
		return invoicePageBreak;
	}

	public void setInvoicePageBreak(String invoicePageBreak) {
		this.invoicePageBreak = invoicePageBreak;
	}

	public String getRemitCode() {
		return remitCode;
	}

	public void setRemitCode(String remitCode) {
		this.remitCode = remitCode;
	}

	public String getPrintMethod() {
		return printMethod;
	}

	public void setPrintMethod(String printMethod) {
		this.printMethod = printMethod;
	}

	public String getPrintMethodDescription() {
		return printMethodDescription;
	}

	public void setPrintMethodDescription(String printMethodDescription) {
		this.printMethodDescription = printMethodDescription;
	}

	public String getInvoiceSiteTotals() {
		return invoiceSiteTotals;
	}

	public void setInvoiceSiteTotals(String invoiceSiteTotals) {
		this.invoiceSiteTotals = invoiceSiteTotals;
	}

	public String getInvoiceSiteTaxes() {
		return invoiceSiteTaxes;
	}

	public void setInvoiceSiteTaxes(String invoiceSiteTaxes) {
		this.invoiceSiteTaxes = invoiceSiteTaxes;
	}

	public String getInvoiceContainerTotals() {
		return invoiceContainerTotals;
	}

	public void setInvoiceContainerTotals(String invoiceContainerTotals) {
		this.invoiceContainerTotals = invoiceContainerTotals;
	}

	public String getInvoiceContainerTaxes() {
		return invoiceContainerTaxes;
	}

	public void setInvoiceContainerTaxes(String invoiceContainerTaxes) {
		this.invoiceContainerTaxes = invoiceContainerTaxes;
	}

	public String getAcquisitionCode() {
		return acquisitionCode;
	}

	public void setAcquisitionCode(String acquisitionCode) {
		this.acquisitionCode = acquisitionCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageDescription() {
		return languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}

	public String getSiEligible() {
		return siEligible;
	}

	public void setSiEligible(String siEligible) {
		this.siEligible = siEligible;
	}

	public String getSiStatus() {
		return siStatus;
	}

	public void setSiStatus(String siStatus) {
		this.siStatus = siStatus;
	}

	public String getSiStartDate() {
		return siStartDate;
	}

	public void setSiStartDate(String siStartDate) {
		this.siStartDate = siStartDate;
	}

	public String getSiStatusDescription() {
		return siStatusDescription;
	}

	public void setSiStatusDescription(String siStatusDescription) {
		this.siStatusDescription = siStatusDescription;
	}

	public String getInvoiceGroupCode() {
		return invoiceGroupCode;
	}

	public void setInvoiceGroupCode(String invoiceGroupCode) {
		this.invoiceGroupCode = invoiceGroupCode;
	}

	public String getCreditPolicy() {
		return creditPolicy;
	}

	public void setCreditPolicy(String creditPolicy) {
		this.creditPolicy = creditPolicy;
	}

	public String getRiskCodeDescription() {
		return riskCodeDescription;
	}

	public void setRiskCodeDescription(String riskCodeDescription) {
		this.riskCodeDescription = riskCodeDescription;
	}

	public String getOriginalStartDate() {
		return originalStartDate;
	}

	public void setOriginalStartDate(String originalStartDate) {
		this.originalStartDate = originalStartDate;
	}

	public String getLastInvoiceDate() {
		return lastInvoiceDate;
	}

	public void setLastInvoiceDate(String lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}

	public String getDunningDate() {
		return dunningDate;
	}

	public void setDunningDate(String dunningDate) {
		this.dunningDate = dunningDate;
	}

	public String getDunningStage() {
		return dunningStage;
	}

	public void setDunningStage(String dunningStage) {
		this.dunningStage = dunningStage;
	}

	public String getCreditAnalyst() {
		return creditAnalyst;
	}

	public void setCreditAnalyst(String creditAnalyst) {
		this.creditAnalyst = creditAnalyst;
	}

	public String getChargeAdministrativeFee() {
		return chargeAdministrativeFee;
	}

	public void setChargeAdministrativeFee(String chargeAdministrativeFee) {
		this.chargeAdministrativeFee = chargeAdministrativeFee;
	}

	public String getAdministrativeFeeExc() {
		return administrativeFeeExc;
	}

	public void setAdministrativeFeeExc(String administrativeFeeExc) {
		this.administrativeFeeExc = administrativeFeeExc;
	}
	
	public String getChargeServiceIntFee() {
		return chargeServiceIntFee;
	}

	public void setChargeServiceIntFee(String chargeServiceIntFee) {
		this.chargeServiceIntFee = chargeServiceIntFee;
	}

	public String getServiceIntFeeExc() {
		return serviceIntFeeExc;
	}

	public void setServiceIntFeeExc(String serviceIntFeeExc) {
		this.serviceIntFeeExc = serviceIntFeeExc;
	}

	public String getChargeLateFee() {
		return chargeLateFee;
	}

	public void setChargeLateFee(String chargeLateFee) {
		this.chargeLateFee = chargeLateFee;
	}

	public String getLateFeeExc() {
		return lateFeeExc;
	}

	public void setLateFeeExc(String lateFeeExc) {
		this.lateFeeExc = lateFeeExc;
	}

	public String getLateFeePolicy() {
		return lateFeePolicy;
	}

	public void setLateFeePolicy(String lateFeePolicy) {
		this.lateFeePolicy = lateFeePolicy;
	}

	public String getChargeFuelFee() {
		return chargeFuelFee;
	}

	public void setChargeFuelFee(String chargeFuelFee) {
		this.chargeFuelFee = chargeFuelFee;
	}

	public String getFuelFeeExc() {
		return fuelFeeExc;
	}

	public void setFuelFeeExc(String fuelFeeExc) {
		this.fuelFeeExc = fuelFeeExc;
	}

	public String getNextReviewDate() {
		return nextReviewDate;
	}

	public void setNextReviewDate(String nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	public String getChargeEnvironmentalFee() {
		return chargeEnvironmentalFee;
	}

	public void setChargeEnvironmentalFee(String chargeEnvironmentalFee) {
		this.chargeEnvironmentalFee = chargeEnvironmentalFee;
	}

	public String getEnvironmentalFeeExc() {
		return environmentalFeeExc;
	}

	public void setEnvironmentalFeeExc(String environmentalFeeExc) {
		this.environmentalFeeExc = environmentalFeeExc;
	}

	public String getDuplicateCopy() {
		return duplicateCopy;
	}

	public void setDuplicateCopy(String duplicateCopy) {
		this.duplicateCopy = duplicateCopy;
	}

	public String getEdiRequired() {
		return ediRequired;
	}

	public void setEdiRequired(String ediRequired) {
		this.ediRequired = ediRequired;
	}
	
	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getAccountGroupId() {
		return accountGroupId;
	}

	public void setAccountGroupId(String accountGroupId) {
		this.accountGroupId = accountGroupId;
	}

	public String getParentD() {
		return parentD;
	}

	public void setParentD(String parentD) {
		this.parentD = parentD;
	}

	public String getOnlineAccountFlag() {
		return onlineAccountFlag;
	}

	public void setOnlineAccountFlag(String onlineAccountFlag) {
		this.onlineAccountFlag = onlineAccountFlag;
	}

	public String getAccountContactName() {
		return accountContactName;
	}

	public void setAccountContactName(String accountContactName) {
		this.accountContactName = accountContactName;
	}

	public String getAccountContactTitle() {
		return accountContactTitle;
	}

	public void setAccountContactTitle(String accountContactTitle) {
		this.accountContactTitle = accountContactTitle;
	}

	public String getAccountContactEmail() {
		return accountContactEmail;
	}

	public void setAccountContactEmail(String accountContactEmail) {
		this.accountContactEmail = accountContactEmail;
	}

	public String getAccountContactAreaCode1() {
		return accountContactAreaCode1;
	}

	public void setAccountContactAreaCode1(String accountContactAreaCode1) {
		this.accountContactAreaCode1 = accountContactAreaCode1;
	}

	public String getAccountContactTelephone1() {
		return accountContactTelephone1;
	}

	public void setAccountContactTelephone1(String accountContactTelephone1) {
		this.accountContactTelephone1 = accountContactTelephone1;
	}

	public String getAccountContactTelephoneExtn1() {
		return accountContactTelephoneExtn1;
	}

	public void setAccountContactTelephoneExtn1(String accountContactTelephoneExtn1) {
		this.accountContactTelephoneExtn1 = accountContactTelephoneExtn1;
	}

	public String getAccountContactType1() {
		return accountContactType1;
	}

	public void setAccountContactType1(String accountContactType1) {
		this.accountContactType1 = accountContactType1;
	}

	public String getAccountContactAreaCode2() {
		return accountContactAreaCode2;
	}

	public void setAccountContactAreaCode2(String accountContactAreaCode2) {
		this.accountContactAreaCode2 = accountContactAreaCode2;
	}

	public String getAccountContactTelephone2() {
		return accountContactTelephone2;
	}

	public void setAccountContactTelephone2(String accountContactTelephone2) {
		this.accountContactTelephone2 = accountContactTelephone2;
	}

	public String getAccountContactTelephoneExtn2() {
		return accountContactTelephoneExtn2;
	}

	public void setAccountContactTelephoneExtn2(String accountContactTelephoneExtn2) {
		this.accountContactTelephoneExtn2 = accountContactTelephoneExtn2;
	}

	public String getAccountContactType2() {
		return accountContactType2;
	}

	public void setAccountContactType2(String accountContactType2) {
		this.accountContactType2 = accountContactType2;
	}

	public String getErfFrfFlag() {
		return erfFrfFlag;
	}

	public void setErfFrfFlag(String erfFrfFlag) {
		this.erfFrfFlag = erfFrfFlag;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getDistributionCode() {
		return distributionCode;
	}

	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}
	
	public String getOnlinePaymentProfile() {
		return onlinePaymentProfile;
	}

	public void setOnlinePaymentProfile(String onlinePaymentProfile) {
		this.onlinePaymentProfile = onlinePaymentProfile;
	}

	public String getAccountClassDesc() {
		return accountClassDesc;
	}

	public void setAccountClassDesc(String accountClassDesc) {
		this.accountClassDesc = accountClassDesc;
	}

	public String getAcquisitionCodeDesc() {
		return acquisitionCodeDesc;
	}

	public void setAcquisitionCodeDesc(String acquisitionCodeDesc) {
		this.acquisitionCodeDesc = acquisitionCodeDesc;
	}
	
	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

}
