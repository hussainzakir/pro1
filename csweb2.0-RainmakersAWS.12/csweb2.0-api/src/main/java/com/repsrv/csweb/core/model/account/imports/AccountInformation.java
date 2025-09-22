package com.repsrv.csweb.core.model.account.imports;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.leftPad;

import com.google.gson.annotations.SerializedName;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.imports.validators.resicontract.ValidSheetRowData;
import com.repsrv.csweb.core.gson.ExcludeGson;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({
"Row Index","Company #","Account #","Customer Name","Care Of","Add Line 1","Add Line 2","Add Line 3","City","State","Postal Code","Country",
"Cust. Alias","Telephone #","Fax #","Acq. Code","Inv Grp Code","Acct Class Code","Cust Cat Code","Eligible Flag","Acct Open Date",
"Acct Closed Date","Charge Inv Fee Flag","Inv Fee Exempt Code","Chg Svc Int Fee Flag","Svc Int Exempt Code","Chg Late Fee FLag",
"Late Fee Exempt Code","Late Pay Fee Policy","Next Review Date","Chg Fuel Fee Flag","Fuel Fee Exempt Code","Chg Env Fee","Env Fee Exempt Code",
"Frf & Erf On Admin","Inv Auto Man Flag","Inv Dest. Code","Ops Risk Code","Email Address"})
@ValidSheetRowData(sheetId = SheetId.ACCOUNT)
@Valid
@ExcelSheet("Account Information")
public class AccountInformation extends Row{
	
	private static final String TAB_NAME = "Account Information";
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCell(0)
	//@RepEntity(entity = EntityType.COMPANY)
	private String companyNumber;
	
	@SerializedName(value="customerNumber")
	@JsonProperty("Account #")
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$",  message = "Account number must be 7 numeric characters or less in length")
	@ExcelCell(1)
	//@RepEntity(entity = EntityType.ACCOUNT)
	private String accountNumber;

	@SerializedName(value="customerName")
	@JsonProperty("Customer Name")
	@NotBlank(message = "Customer Name is required")
	@Size(max = 30, message = "Customer name longer than 30 chars")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s\\&]+$", message = "Customer name can only be alpha numeric, spaces and hyphens")
	@ExcelCell(2)
	private String customerName;

	@SerializedName(value="careOfField")
	@JsonProperty("Care Of")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Care Of can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 30, message = "Customer name longer than 30 chars")
	@ExcelCell(3)
	private String careOf;
	
	@SerializedName(value="addressLine1")
	@JsonProperty("Add Line 1")
	@Size(max = 30, message = "Address Line 1 cannot be more than 30 digits")
	@ExcelCell(4)
	private String addressLine1;
	
	@SerializedName(value="addressLine2")
	@JsonProperty("Add Line 2")
	@Size(max = 30, message = "Address Line 2 cannot be more than 30 digits")
	@ExcelCell(5)
	private String addressLine2;
	
	@SerializedName(value="addressLine3")
	@JsonProperty("Add Line 3")
	@Size(max = 30, message = "Address Line 3 cannot be more than 30 digits")
	@ExcelCell(6)
	private String addressLine3;
	
	@SerializedName(value="city")
	@JsonProperty("City")
	@Pattern(regexp = "^(\\w+(\\s\\w+)*+)$" , message = "City can only contain alphanumeric and space characters")
	@Size(max = 20, message = "City max length is 20 characters")
	@NotBlank(message = "City is required")	
	@ExcelCell(7)
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only contain alpha numeric characters")
	@Size(max = 3, min = 2, message = "State cannot must be 2 or more chracters and not exceed 3 in length")
	@NotBlank(message = "State is required")
	@ExcelCell(8)
	private String state;
	
	@SerializedName(value="postalCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Postal code format invalid")
	@ExcelCell(9)
	private String postalCode;
	
	@SerializedName(value="country")
	@JsonProperty("Country")
	@Size(max =3, min = 3, message = "Country code invalid")
	@NotBlank(message = "Country is required")
	@ExcelCell(10)
	private String country;
	
	@SerializedName(value="customerAlias")
	@JsonProperty("Cust. Alias")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Customer Alias can only consist of alpha numeric, space, - :  and # characters")
	@Size(max = 30, message = "Customer Alias name longer than 30 chars")
	@ExcelCell(11)
	private String customerAlias;
	
	@SerializedName(value="telephoneNumber")
	@JsonProperty("Telephone #")
	@Pattern(regexp ="^((\\(?(\\d{3})\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})|\\d{10})$", message = "Telephone invalid must be (DDD) DDD-DDDD or 10 digits only ")
	@Size(max = 14, message = "Telephone cannot be more than 14 characters")	
	@NotBlank(message = "Telephone number is required")
	@ExcelCell(12)
	private String telephoneNumber;
	
	@SerializedName(value="creditFaxNumber")
	@JsonProperty("Fax #")
	@Pattern(regexp = "^((\\(?(\\d{3})\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})|\\d{10}|\\s*|)$", message = "Fax invalid format (DDD) DDD-DDDD ")
	@Size(max = 14, message = "Fax Number cannot be more than 14 characters")
	@ExcelCell(13)
	private String faxNumber;
	
	@SerializedName(value="acquisitionCode")
	@JsonProperty("Acq. Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Aquisition Code can only contain letters")
	@Size(max = 2, message = "Aquisition code max length is 2")
	@ExcelCell(14)
	private String acquisitionCode;
	
	@SerializedName(value="invoiceGroupCode")
	@JsonProperty("Inv Grp Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Invoice Group Code invalid")
	@NotBlank(message = "Invoice Group code is required")
	@ExcelCell(15)
	private String invoiceGroupCode;
	
	@SerializedName(value="majorClassCode")
	@JsonProperty("Acct Class Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Account Class Code invalid")
	@NotBlank(message = "Account Clsas Code is required")
	@ExcelCell(16)
	private String accountClassCode;
	
	@SerializedName(value="customerCategoryCode")
	@JsonProperty("Cust Cat Code")
	@Pattern(regexp = "^(RESI|CNTRT)$", message = "Customer Category Code must be RESI or CNTRT")
	@NotBlank(message = "Customer Category Code is required")
	@ExcelCell(17)
	private String customerCategoryCode;
	
	@SerializedName(value="suspendableFlag")
	@JsonProperty("Eligible Flag")
	@Pattern(regexp = "^[NYny]$", message = "Si Eligible Flag must be N or Y")
	@NotBlank(message = "Is Eligible Flag is required")
	@ExcelCell(18)
	private String siEligibleFlag;
	
	@SerializedName(value="accountOpenDate")
	@JsonProperty("Acct Open Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
	message = "Account Open Date invalid")
	@NotBlank(message = "Account Open Date is required")
	@ExcelCell(19)
	private String accountOpenDate;
	
	@SerializedName(value="accountCloseDate")
	@JsonProperty("Acct Closed Date")
	@Pattern(regexp = "^((\\d{8})|\\s*|)$", message = "Account closed date invalid")
	@ExcelCell(20)
	private String accountClosedDate;
	
	@SerializedName(value="chargeInvoiceFee")
	@JsonProperty("Charge Inv Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Inv Fee Flag must be N or Y")
	@NotBlank(message = "Charge Invoice Fee Flag is required")
	@ExcelCell(21)
	private String chargeInvoiceFeeFlag;
	
	@SerializedName(value="invoiceExemptCode")
	@JsonProperty("Inv Fee Exempt Code")
	@Pattern(regexp = "^(([a-zA-Z0-9]*)|\\s|)$", message = "Invoice Fee Exempt Code can only contain letters and numbers")
	@Size(max = 2, message = "Invoice Fee Exempt Code code max length is 2")
	@ExcelCell(22)
	private String invoiceFeeExemptCode;
	
	@SerializedName(value="chargeSuspensionFee")
	@JsonProperty("Chg Svc Int Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Service Interrupt Fee Flag must be N or Y")
	@NotBlank(message = "Charge Service Interrupt Fee Flag is required")
	@ExcelCell(23)
	private String chargeServiceInterruptFeeFlag;
	
	@SerializedName(value="serviceExemptCode")
	@JsonProperty("Svc Int Exempt Code")
	@Pattern(regexp = "^(([a-zA-Z0-9]+)|\\s|)$", message = "Service Int Exempt Code can only contain letters and numbers")
	@Size(max = 2, message = "Service Int Exempt Code code max length is 2")
	@ExcelCell(24)
	private String serviceIntExemptCode;
	
	@SerializedName(value="chargeLateFee")
	@JsonProperty("Chg Late Fee FLag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Late Fee Flag must be N or Y")
	@NotBlank(message = "Charge Late Fee flag is required")
	@ExcelCell(25)
	private String chargeLateFeeFlag;
	
	@SerializedName(value="lateFeeExemptCode")
	@JsonProperty("Late Fee Exempt Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Late Fee Exempt Code can only contain letters and numbers")
	@Size(max = 2, message = "Late Fee Exempt Code code max length is 2")
	@ExcelCell(26)
	private String lateFeeExemptCode;
	
	@SerializedName(value="lateFeePolicy")
	@JsonProperty("Late Pay Fee Policy")
	//business needs to be able to submit special characters for now. 3/11/2024   -Adnan Varela
	// @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Late Pay Fee Policy can only contain letters and numbers")
	@Size(max = 10, message = "Late Pay Fee Policy code max length is 10")
	@ExcelCell(27)
	private String latePayFeePolicy;
	
	@SerializedName(value="nextReviewDate")
	@JsonProperty("Next Review Date")
	@Pattern(regexp = "^(99999999|[0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
			message = "Next Review date invalid")
	@NotBlank(message = "Next Review Date is required")
	@ExcelCell(28)
	private String nextReviewDate;
	
	@SerializedName(value="chargeFeeFlag")
	@JsonProperty("Chg Fuel Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Fuel Fee Flag must be N or Y")
	@NotBlank(message = "Charge Fuel Fee Flag is required")
	@ExcelCell(29)
	private String chargeFuelFeeFlag;
	
	@SerializedName(value="fuelExemptCode")
	@JsonProperty("Fuel Fee Exempt Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Fuel Fee Exempt Code can only contain letters and numbers")
	@Size(max = 2, message = "Fuel Fee Exempt Code code max length is 2")
	@ExcelCell(30)
	private String fuelFeeExemptCode;
	
	@SerializedName(value="chargeEnvironmentalFee")
	@JsonProperty("Chg Env Fee")
	@Pattern(regexp = "^[NYny]$", message = "Charge Environmental Fee Flag must be N or Y")
	@NotBlank(message = "Charge Environmental Fee is required")
	@ExcelCell(31)
	private String chargeEnvironmentalFee;
	
	@SerializedName(value="environmentExemptCode")
	@JsonProperty("Env Fee Exempt Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Environment Fee Exempt Code can only contain letters and numbers")
	@Size(max = 2, message = "Environment Fee Exempt Code code max length is 2")
	@ExcelCell(32)
	private String environmentFeeExemptCode;
	
	@SerializedName(value="frfErfFlag")
	@JsonProperty("Frf & Erf On Admin")
	@Pattern(regexp = "^[NFEBnfeb]$", message = "FRF & ERF On Admin must be either N, F, E or B")
	@NotBlank(message = "FRF and ERF On Admin is required")
	@ExcelCell(33)
	private String frfAndErfOnAdmin ;
	
	@SerializedName(value="autoManualFlag")
	@JsonProperty("Inv Auto Man Flag")
	@Pattern(regexp = "^[AMamZzPp]$", message = "Invoice Auto Manual FLag must be A,M,Z or P")
	@NotBlank(message = "Invoice Auto Manual Flag is required")
	@ExcelCell(34)
	private String invoiceAutoManualFlag;
	
	@SerializedName(value="invoiceDestination")
	@JsonProperty("Inv Dest. Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invoice Destination can only contain letters and numbers")
	@Size(max = 3, message = "Invoice Destination code max length is 3")
	@ExcelCell(35)
	private String invoiceDestination ;
	
	@SerializedName(value="reviewPolicy")
	@JsonProperty("Ops Risk Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Ops Risk Code can only contain letters and numbers")
	@Size(max = 5, message = "Ops Risk code max length is 5")
	@ExcelCell(36)
	private String opsRiskCode ;
	
	@SerializedName(value="emailAddress")
	@JsonProperty("Email Address")
	@Pattern(message="Email invalid", regexp = "^(([a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})|\\s|)$")
	@ExcelCell(37)
	private String emailAddress ;
	
	@ExcludeGson
	@Valid
	private List<SiteInformation> sites;
	
	@ExcelCell(38)
	@SerializedName(value="ACCTERROR")
	private String uploadError = "";
	
	public AccountInformation() {}


	public String getCompanyNumber() {
		return companyNumber;
	}


	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = StringUtils.leftPad(companyNumber, 3, '0');
	}

	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCareOf() {
		return careOf;
	}


	public void setCareOf(String careOf) {
		this.careOf = careOf;
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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCustomerAlias() {
		return customerAlias;
	}


	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}


	public String getTelephoneNumber() {
		return telephoneNumber;
	}


	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}


	public String getFaxNumber() {
		return faxNumber;
	}


	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}


	public String getAcquisitionCode() {
		return acquisitionCode;
	}


	public void setAcquisitionCode(String acquisitionCode) {
		this.acquisitionCode = acquisitionCode;
	}


	public String getInvoiceGroupCode() {
		return invoiceGroupCode;
	}


	public void setInvoiceGroupCode(String invoiceGroupCode) {
		this.invoiceGroupCode = invoiceGroupCode;
	}


	public String getAccountClassCode() {
		return accountClassCode;
	}


	public void setAccountClassCode(String accountClassCode) {
		this.accountClassCode = accountClassCode;
	}


	public String getSiEligibleFlag() {
		return siEligibleFlag;
	}


	public void setSiEligibleFlag(String siEligibleFlag) {
		this.siEligibleFlag = siEligibleFlag;
	}


	public String getAccountOpenDate() {
		return accountOpenDate;
	}


	public void setAccountOpenDate(String accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}


	public String getAccountClosedDate() {
		return accountClosedDate;
	}


	public void setAccountClosedDate(String accountClosedDate) {
		this.accountClosedDate = accountClosedDate;
	}


	public String getChargeInvoiceFeeFlag() {
		return chargeInvoiceFeeFlag;
	}


	public void setChargeInvoiceFeeFlag(String chargeInvoiceFeeFlag) {
		this.chargeInvoiceFeeFlag = chargeInvoiceFeeFlag;
	}


	public String getInvoiceFeeExemptCode() {
		return invoiceFeeExemptCode;
	}


	public void setInvoiceFeeExemptCode(String invoiceFeeExemptCode) {
		this.invoiceFeeExemptCode = invoiceFeeExemptCode;
	}


	public String getChargeServiceInterruptFeeFlag() {
		return chargeServiceInterruptFeeFlag;
	}


	public void setChargeServiceInterruptFeeFlag(String chargeServiceInterruptFeeFlag) {
		this.chargeServiceInterruptFeeFlag = chargeServiceInterruptFeeFlag;
	}


	public String getServiceIntExemptCode() {
		return serviceIntExemptCode;
	}


	public void setServiceIntExemptCode(String serviceIntExemptCode) {
		this.serviceIntExemptCode = serviceIntExemptCode;
	}


	public String getChargeLateFeeFlag() {
		return chargeLateFeeFlag;
	}


	public void setChargeLateFeeFlag(String chargeLateFeeFlag) {
		this.chargeLateFeeFlag = chargeLateFeeFlag;
	}


	public String getLateFeeExemptCode() {
		return lateFeeExemptCode;
	}


	public void setLateFeeExemptCode(String lateFeeExemptCode) {
		this.lateFeeExemptCode = lateFeeExemptCode;
	}


	public String getLatePayFeePolicy() {
		return latePayFeePolicy;
	}


	public void setLatePayFeePolicy(String latePayFeePolicy) {
		this.latePayFeePolicy = latePayFeePolicy;
	}


	public String getNextReviewDate() {
		return nextReviewDate;
	}


	public void setNextReviewDate(String nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}


	public String getChargeFuelFeeFlag() {
		return chargeFuelFeeFlag;
	}


	public void setChargeFuelFeeFlag(String chargeFuelFeeFlag) {
		this.chargeFuelFeeFlag = chargeFuelFeeFlag;
	}


	public String getFuelFeeExemptCode() {
		return fuelFeeExemptCode;
	}


	public void setFuelFeeExemptCode(String fuelFeeExemptCode) {
		this.fuelFeeExemptCode = fuelFeeExemptCode;
	}


	public String getChargeEnvironmentalFee() {
		return chargeEnvironmentalFee;
	}


	public void setChargeEnvironmentalFee(String chargeEnvironmentalFee) {
		this.chargeEnvironmentalFee = chargeEnvironmentalFee;
	}


	public String getEnvironmentFeeExemptCode() {
		return environmentFeeExemptCode;
	}


	public void setEnvironmentFeeExemptCode(String environmentFeeExemptCode) {
		this.environmentFeeExemptCode = environmentFeeExemptCode;
	}


	public String getFrfAndErfOnAdmin() {
		return frfAndErfOnAdmin;
	}


	public void setFrfAndErfOnAdmin(String frfAndErfOnAdmin) {
		this.frfAndErfOnAdmin = frfAndErfOnAdmin;
	}


	public String getInvoiceAutoManualFlag() {
		return invoiceAutoManualFlag;
	}


	public void setInvoiceAutoManualFlag(String invoiceAutoManualFlag) {
		this.invoiceAutoManualFlag = invoiceAutoManualFlag;
	}


	public String getInvoiceDestination() {
		return invoiceDestination;
	}


	public void setInvoiceDestination(String invoiceDestination) {
		this.invoiceDestination = invoiceDestination;
	}


	public String getOpsRiskCode() {
		return opsRiskCode;
	}


	public void setOpsRiskCode(String opsRiskCode) {
		this.opsRiskCode = opsRiskCode;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public List<SiteInformation> getSites() {
		return sites;
	}

	public void setSites(List<SiteInformation> sites) {
		this.sites = sites;
	}

	@JsonIgnore
	public String getParentChildKey() {
		return this.companyNumber + "-" + this.accountNumber; 
	}

	@Override
	public String toString() {
		return "Account [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + ", customerName="
				+ customerName + ", careOf=" + careOf + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", addressLine3=" + addressLine3 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", customerAlias=" + customerAlias
				+ ", telephoneNumber=" + telephoneNumber + ", faxNumber=" + faxNumber + ", acquisitionCode="
				+ acquisitionCode + ", invoiceGroupCode=" + invoiceGroupCode + ", accountClassCode=" + accountClassCode
				+ ", siEligibleFlag=" + siEligibleFlag + ", accountOpenDate=" + accountOpenDate + ", accountClosedDate="
				+ accountClosedDate + ", chargeInvoiceFeeFlag=" + chargeInvoiceFeeFlag + ", invoiceFeeExemptCode="
				+ invoiceFeeExemptCode + ", chargeServiceInterruptFeeFlag=" + chargeServiceInterruptFeeFlag
				+ ", serviceIntExemptCode=" + serviceIntExemptCode + ", chargeLateFeeFlag=" + chargeLateFeeFlag
				+ ", lateFeeExemptCode=" + lateFeeExemptCode + ", latePayFeePolicy=" + latePayFeePolicy
				+ ", nextReviewDate=" + nextReviewDate + ", chargeFuelFeeFlag=" + chargeFuelFeeFlag
				+ ", fuelFeeExemptCode=" + fuelFeeExemptCode + ", chargeEnvironmentalFee=" + chargeEnvironmentalFee
				+ ", environmentFeeExemptCode=" + environmentFeeExemptCode + ", frfAndErfOnAdmin=" + frfAndErfOnAdmin
				+ ", invoiceAutoManualFlag=" + invoiceAutoManualFlag + ", invoiceDestination=" + invoiceDestination
				+ ", opsRiskCode=" + opsRiskCode + ", emailAddress=" + emailAddress + "]";
	}

	@JsonIgnore
	@Override
	public String getKey() {
		return companyNumber + "-" + accountNumber ;
	}

	@JsonIgnore
	@Override
	public String getParentKey() {
		return companyNumber;
	}
	
	@JsonIgnore
	@Override
	public String getTabName() {
		return TAB_NAME;
	}


	public String getUploadError() {
		return uploadError;
	}


	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}


	@Override
	public String getKeyName() {
		return "Account Number";
	}

	
}
