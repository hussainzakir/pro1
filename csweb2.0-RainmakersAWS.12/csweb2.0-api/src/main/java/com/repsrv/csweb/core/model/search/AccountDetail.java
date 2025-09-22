package com.repsrv.csweb.core.model.search;

import static com.repsrv.csweb.core.util.AS400DateUtil.format8DigitDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.account.AccountContact;
import com.repsrv.csweb.core.model.account.AccountSiteContact;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetail {

	private String company;
	
	private String account;
	
	private String suspended;
	
	private String attention;
	
	public String accountAlias;
	
	private int suspendStartDate;
	
	private String customerName;
	
	private String addressLine1, addressLine2, addressLine3;
	
	private String city;
	
	private String state;
	
	private String postalCode;
	
	@JsonProperty("Telephone")
	private String telephone;
	
	@JsonProperty("Fax")
	private String fax;
	
	@JsonProperty("phoneExtension")
	private int phoneExtension;
	
	@JsonProperty("parcelNo")
	private String parcelNo;
	
	@JsonProperty("accountClassTable")
	private String accountClassTable;
	
	@JsonProperty("accountClassDesc")
	private String accountClassDesc;
	
	@JsonProperty("cashTolerance")
	private String cashTolerance;
	
	@JsonProperty("cashToleranceDesc")
	private String cashToleranceDesc;
	
	@JsonProperty("chainCode")
	private String chainCode;
	
	@JsonProperty("chainCodeDescription")
	private String chainCodeDescription;
	
	@JsonProperty("customerCategory")
	private String customerCategory;
	
	@JsonProperty("custCategoryDesc")
	private String custCategoryDesc;

	@JsonProperty("invoiceDestination")
	private String invoiceDestination;
	
	@JsonProperty("invoiceDestinDesc")
	private String invoiceDestinationDesc;
	
	@JsonProperty("invoicePageBreak")
	private String invoicePageBreak;
	
	@JsonProperty("remitCode")
	private String remitCode;
	
	@JsonProperty("remitCodeDesc")
	private String remitCodeDesc;
	
	@JsonProperty("printMethod")
	private String printMethod;
	
	@JsonProperty("printMethodDesc")
	private String printMethodDesc;
	
	@JsonProperty("invoiceSiteTotals")
	private String invoiceSiteTotals;
	
	@JsonProperty("invoiceSiteTaxes")
	private String invoiceSiteTaxes;
	
	@JsonProperty("invoiceContainerTotals")
	private String invoiceContainerTotals;
	
	@JsonProperty("invoiceContainerTaxes")
	private String invoiceContainerTaxes;
	
	@JsonProperty("acquisitionCode")
	private String acquisitionCode;
	
	@JsonProperty("languageCode")
	private String languageCode;
	
	@JsonProperty("languageDesc")
	private String languageDesc;
	
	@JsonProperty("SIEligible")
	private String siEligible;
	
	@JsonProperty("SIStatus")
	private String siStatus;
	
	@JsonProperty("SIStartDate")
	private String siStartDate;
	
	@JsonProperty("invoiceGroupCode")
	private String invoiceGroupCode;
	
	@JsonProperty("invoiceGroupDescription")
	private String invoiceGroupDescription;
	
	@JsonProperty("creditPolicy")
	private String creditPolicy;
	
	@JsonProperty("originalStartDate")
	private String originalStartDate;
	
	@JsonProperty("lastInvoiceDate")
	private String lastInvoiceDate;
	
	@JsonProperty("dunningDate")
	private String dunningDate;
	
	@JsonProperty("dunningStage")
	private String dunningStage;
	
	@JsonProperty("creditAnalyst")
	private String creditAnalyst;
	
	@JsonProperty("userName")
	private String userName;

	@JsonProperty("sites")
	private List<Site> sites;
	
	@JsonProperty("brokerCode")
	private String brokerCode;
	
	@JsonProperty("EDIReq")
	private String ediRequired;

	@JsonProperty("chargeAdminFee")
	private String chargeAdminFee;
	
	@JsonProperty("adminFeeExc")
	private String adminFeeExc;
	
	@JsonProperty("adminFeeExcDesc")
	private String adminFeeExcDesc;

	@JsonProperty("chargeServIntFee")
	private String chargeServIntFee;
	
	@JsonProperty("servIntFeeExc")
	private String servIntFeeExc;
	
	@JsonProperty("servIntFeeDesc")
	private String servIntFeeDesc;
	
	@JsonProperty("chargeLateFee")
	private String chargeLateFee;
	
	@JsonProperty("lateFeeExc")
	private String lateFeeExc;
	
	@JsonProperty("lateFeeExcDesc")
	private String lateFeeExcDesc;
	
	@JsonProperty("chargeFuelFee")
	private String chargeFuelFee;
	
	@JsonProperty("fuelFeeExcDesc")
	private String fuelFeeExcDesc;
	
	@JsonProperty("fuelFeeExc")
	private String fuelFeeExc;
	
	@JsonProperty("chargeEnvrFee")
	private String chargeEnviroFee;
	
	@JsonProperty("EnvrFeeExc")
	private String envrFeeExc;
	
	@JsonProperty("EnvrFeeExcDesc")
	private String envrFeeExcDesc;
	
	@JsonProperty("lateFeePolicy")
	private String lateFeePolicy;
	
	@JsonProperty("lateFeePolicyDesc")
	private String lateFeePolicyDesc;
	
	@JsonProperty("quickPay")
	private String quickPay;

	@JsonProperty("NaFlag")
	private String naFlag;

	@JsonProperty("accountGroup")
	private String accountGroup;
	
	private String nextReviewDate;
	
	@JsonProperty("riskCodeDesc")
	private String riskDescription;
	
	@JsonProperty("ePortalCustomer")
	private String ePortalCustomer;
	
	private AccountContact accountContact;
	
	private AccountSiteContact accountSiteContact;
	
	public AccountDetail() {}
	
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

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public int getSuspendStartDate() {
		return suspendStartDate;
	}

	public void setSuspendStartDate(int suspendedStartDate) {
		this.suspendStartDate = suspendedStartDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(int phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getParcelNo() {
		return parcelNo;
	}

	public void setParcelNo(String parcelNo) {
		this.parcelNo = parcelNo;
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

	public String getChainCodeDescription() {
		return chainCodeDescription;
	}

	public void setChainCodeDescription(String chainCodeDescription) {
		this.chainCodeDescription = chainCodeDescription;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
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

	public String getInvoiceGroupCode() {
		return invoiceGroupCode;
	}

	public void setInvoiceGroupCode(String invoiceGroupCode) {
		this.invoiceGroupCode = invoiceGroupCode;
	}

	public String getInvoiceGroupDescription() {
		return invoiceGroupDescription;
	}

	public void setInvoiceGroupDescription(String invoiceGroupDescription) {
		this.invoiceGroupDescription = invoiceGroupDescription;
	}

	public String getCreditPolicy() {
		return creditPolicy;
	}

	public void setCreditPolicy(String creditPolicy) {
		this.creditPolicy = creditPolicy;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getEdiRequired() {
		return ediRequired;
	}

	public void setEdiRequired(String ediRequired) {
		this.ediRequired = ediRequired;
	}

	public String getChargeAdminFee() {
		return chargeAdminFee;
	}

	public void setChargeAdminFee(String chargeAdminFee) {
		this.chargeAdminFee = chargeAdminFee;
	}

	public String getChargeServIntFee() {
		return chargeServIntFee;
	}

	public void setChargeServIntFee(String chargeServIntFee) {
		this.chargeServIntFee = chargeServIntFee;
	}

	public String getChargeLateFee() {
		return chargeLateFee;
	}

	public void setChargeLateFee(String chargeLateFee) {
		this.chargeLateFee = chargeLateFee;
	}

	public String getChargeFuelFee() {
		return chargeFuelFee;
	}

	public void setChargeFuelFee(String chargeFuelFee) {
		this.chargeFuelFee = chargeFuelFee;
	}

	public String getChargeEnviroFee() {
		return chargeEnviroFee;
	}

	public void setChargeEnviroFee(String chargeEnviroFee) {
		this.chargeEnviroFee = chargeEnviroFee;
	}

	public String getLateFeePolicy() {
		return lateFeePolicy;
	}

	public void setLateFeePolicy(String lateFeePolicy) {
		this.lateFeePolicy = lateFeePolicy;
	}

	public String getLateFeePolicyDesc() {
		return lateFeePolicyDesc;
	}

	public void setLateFeePolicyDesc(String lateFeePolicyDesc) {
		this.lateFeePolicyDesc = lateFeePolicyDesc;
	}

	public String getNextReviewDate() {
		return nextReviewDate;
	}

	@JsonProperty("nextReviewDate")
	public void setNextReviewDate(String nextReviewDate) {
		this.nextReviewDate = format8DigitDate(nextReviewDate);
	}

	public String getAccountClassDesc() {
		return accountClassDesc;
	}

	public void setAccountClassDesc(String accountClassDesc) {
		this.accountClassDesc = accountClassDesc;
	}

	public String getCashToleranceDesc() {
		return cashToleranceDesc;
	}

	public void setCashToleranceDesc(String cashToleranceDesc) {
		this.cashToleranceDesc = cashToleranceDesc;
	}

	public String getInvoiceDestinationDesc() {
		return invoiceDestinationDesc;
	}

	public void setInvoiceDestinationDesc(String invoiceDestinationDesc) {
		this.invoiceDestinationDesc = invoiceDestinationDesc;
	}

	public String getRemitCodeDesc() {
		return remitCodeDesc;
	}

	public void setRemitCodeDesc(String remitCodeDesc) {
		this.remitCodeDesc = remitCodeDesc;
	}

	public String getPrintMethodDesc() {
		return printMethodDesc;
	}

	public void setPrintMethodDesc(String printMethodDesc) {
		this.printMethodDesc = printMethodDesc;
	}

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustCategoryDesc() {
		return custCategoryDesc;
	}

	public void setCustCategoryDesc(String custCategoryDesc) {
		this.custCategoryDesc = custCategoryDesc;
	}

	public String getAdminFeeExc() {
		return adminFeeExc;
	}

	public void setAdminFeeExc(String adminFeeExc) {
		this.adminFeeExc = adminFeeExc;
	}

	public String getAdminFeeExcDesc() {
		return adminFeeExcDesc;
	}

	public void setAdminFeeExcDesc(String adminFeeExcDesc) {
		this.adminFeeExcDesc = adminFeeExcDesc;
	}

	public String getServIntFeeExc() {
		return servIntFeeExc;
	}

	public void setServIntFeeExc(String servIntFeeExc) {
		this.servIntFeeExc = servIntFeeExc;
	}

	public String getServIntFeeDesc() {
		return servIntFeeDesc;
	}

	public void setServIntFeeDesc(String servIntFeeDesc) {
		this.servIntFeeDesc = servIntFeeDesc;
	}

	public String getLateFeeExc() {
		return lateFeeExc;
	}

	public void setLateFeeExc(String lateFeeExc) {
		this.lateFeeExc = lateFeeExc;
	}

	public String getLateFeeExcDesc() {
		return lateFeeExcDesc;
	}

	public void setLateFeeExcDesc(String lateFeeExcDesc) {
		this.lateFeeExcDesc = lateFeeExcDesc;
	}

	public String getFuelFeeExcDesc() {
		return fuelFeeExcDesc;
	}

	public void setFuelFeeExcDesc(String fuelFeeExcDesc) {
		this.fuelFeeExcDesc = fuelFeeExcDesc;
	}

	public String getFuelFeeExc() {
		return fuelFeeExc;
	}

	public void setFuelFeeExc(String fuelFeeExc) {
		this.fuelFeeExc = fuelFeeExc;
	}

	public String getEnvrFeeExc() {
		return envrFeeExc;
	}

	public void setEnvrFeeExc(String envrFeeExc) {
		this.envrFeeExc = envrFeeExc;
	}

	public String getEnvrFeeExcDesc() {
		return envrFeeExcDesc;
	}

	public void setEnvrFeeExcDesc(String envrFeeExcDesc) {
		this.envrFeeExcDesc = envrFeeExcDesc;
	}

	public String getQuickPay() {
		return quickPay;
	}

	public void setQuickPay(String quickPay) {
		this.quickPay = quickPay;
	}

	public String getAccountGroup() {
		return accountGroup;
	}

	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}

	public String getRiskDescription() {
		return riskDescription;
	}

	public void setRiskDescription(String riskDescription) {
		this.riskDescription = riskDescription;
	}

	public AccountSiteContact getAccountSiteContact() {
		return accountSiteContact;
	}

	public void setAccountSiteContact(AccountSiteContact accountSiteContact) {
		this.accountSiteContact = accountSiteContact;
	}

	public AccountContact getAccountContact() {
		return accountContact;
	}

	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}

	public String getePortalCustomer() {
		return ePortalCustomer;
	}

	public void setePortalCustomer(String ePortalCustomer) {
		this.ePortalCustomer = ePortalCustomer;
	}

	public String getNaFlag() {
		return naFlag;
	}

	public void setNaFlag(String naFlag) {
		this.naFlag = naFlag;
	}

	
}
