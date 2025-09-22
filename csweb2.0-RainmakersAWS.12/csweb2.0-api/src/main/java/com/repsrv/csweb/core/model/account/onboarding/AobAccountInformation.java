package com.repsrv.csweb.core.model.account.onboarding;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.google.gson.annotations.SerializedName;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.onboarding.validators.AobValidSheetRowData;
import com.repsrv.csweb.core.gson.ExcludeGson;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({
"Company #","Customer Account","National Acct #","Customer Name", "Care Of","Address Line 1","Address Line 2","Address Line 3","City","State","Postal Code","Country",
"Cust. Alias","Telephone #","Fax #","Inv Grp Code","Major Class Code","Cust. Cat. Code","Suspendable Flag","Acct Open Date",
"Charge Fuel Fee Flag", "Charge Env Fee", "Auto Manual Flag", "Invoice Destination", "Review Policy",
"Edi Flag", "Parent Id", "Acct Broker", "Cbs Loc. StoreId", "Acct Group Code", "Cmib", "Email Address"})
@AobValidSheetRowData(sheetId = SheetId.ACCOUNT)
@Valid
@ExcelSheet("Account Information")

public class AobAccountInformation extends Row {
 
	private static final String TAB_NAME = "Account Information";

	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("CUCO")
	private String companyNumber;

	@SerializedName(value="customerAccount")
	@JsonProperty("Customer Account")
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$",  message = "Account number must be 7 numeric characters or less in length")
	@ExcelCellName("CUCUNO")
	private String accountNumber;

  @SerializedName(value="nationalAccountNumber")
	@JsonProperty("National Acct #")
	@Digits(integer = 7, fraction = 0, message = "National Account Number must be 7 numeric characters or less in length")
	@ExcelCellName("CUNANO")
	private String nationalAccountNumber;

	@SerializedName(value="customerName")
	@JsonProperty("Customer Name")
	@NotBlank(message = "Customer Name is required")
	@Size(max = 30, message = "Customer name longer than 30 chars")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s\\&]+$", message = "Customer name can only consist of alpha numeric, space, - : & and # characters")
	@ExcelCellName("CUNAME")
	private String customerName;

	@SerializedName(value="careOfField")
	@JsonProperty("Care Of")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Care Of Field can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Care Of Field longer than 30 chars")
	@ExcelCellName("CUCROF")
	private String careOf;
	
	@SerializedName(value="addressLine1")
	@JsonProperty("Address Line 1")

	@Size(max = 30, message = "Address Line 1 cannot be more than 30 digits")
	@ExcelCellName("CUADR1")
	private String addressLine1;

	@SerializedName(value="addressLine2")
	@JsonProperty("Address Line 2")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Address Line 2 can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Address Line 2 cannot be more than 30 digits")
	@ExcelCellName("CUADR2")
	private String addressLine2;
	
	@SerializedName(value="addressLine3")
	@JsonProperty("Address Line 3")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Address LIne 3 can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Address Line 3 cannot be more than 30 digits")
	@ExcelCellName("CUADR3")
	private String addressLine3;

	@SerializedName(value="city")
	@JsonProperty("City")
	@Pattern(regexp = "^(\\w+(\\s\\w+)*)$" , message = "City can only contain alphanumeric and space characters")
	@Size(max = 20, message = "City max length is 20 characters")
	@NotBlank(message = "City is required")	
	@ExcelCellName("CUCITY")
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only contain alpha numeric characters")
	@Size(max = 3, min = 2, message = "State must be 2 or more characters and not exceed 3 in length")
	@NotBlank(message = "State is required")
	@ExcelCellName("CUSTAT")
	private String state;
	
	@SerializedName(value="postalCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^[a-zA-Z0-9-]*$", message = "Postal code format invalid")
	@Size(max = 10, message = "Postal Code can't be longer than 10 chars")
	@ExcelCellName("CUPOST")
	private String postalCode;

	@SerializedName(value="country")
	@JsonProperty("Country")
	@Size(max =3, min = 3, message = "Country code invalid")
	@NotBlank(message = "Country is required")
	@ExcelCellName("CUCTRY")
	private String country;

	@SerializedName(value="customerAlias")
	@JsonProperty("Cust. Alias")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#&]*$", message = "Customer Alias can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Customer Alias name longer than 30 chars")
	@ExcelCellName("CUALIA")
	private String customerAlias;

	@SerializedName(value="telephoneNumber")
	@JsonProperty("Telephone #")
	@Pattern(regexp ="^\\(?(\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})|d{10}$", message = "Telephone invalid must be (DDD) DDD-DDDD or 10 digits only ")
	@Size(max = 14, message = "Telephone cannot be more than 14 characters")	
	@NotBlank(message = "Telephone number is required")
	@ExcelCellName("CUTEL")
	private String telephoneNumber;
	
	@SerializedName(value="faxNumber")
	@JsonProperty("Fax #")
	@Pattern(regexp = "^\\(?(\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})|d{10}|\\s*|$", message = "Fax invalid format (DDD) DDD-DDDD ")
	@Size(max = 14, message = "Fax Number cannot be more than 14 characters")
	@ExcelCellName("CCCRCF")
	private String faxNumber;
	
	@SerializedName(value="invoiceGroupCode")
	@JsonProperty("Inv Grp Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Invoice Group Code invalid")
	@NotBlank(message = "Invoice Group code is required")
	@ExcelCellName("CUINGP")
	private String invoiceGroupCode;
	
	@SerializedName(value="majorClassCode")
	@JsonProperty("Major Class Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Account Class Code invalid")
	@NotBlank(message = "Account Clsas Code is required")
	@ExcelCellName("CUMACL")
	private String accountClassCode;
	
    @SerializedName(value="customerCategoryCode")
	@JsonProperty("Cust. Cat. Code")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Customer Category Code can only contain alpha characters")
    @Size(max = 5, message = "Customer Category Code must be 5 alpha numeric characters or less in length")
	@NotBlank(message = "Customer Category Code is required")
	@ExcelCellName("CUCATG")
	private String customerCategoryCode;

	@SerializedName(value="suspendableFlag")
	@JsonProperty("Suspendable Flag")
	@Pattern(regexp = "^[NYny]$", message = "Si Eligible Flag must be N or Y")
	@NotBlank(message = "SI Eligible Flag is required")
	@ExcelCellName("CUSUSF")
	private String siEligibleFlag;
	
	@SerializedName(value="accountOpenDate")
	@JsonProperty("Acct Open Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
	message = "Account Open Date invalid")
	@NotBlank(message = "Account Open Date is required")
	@ExcelCellName("CCOPN8")
	private String accountOpenDate;

	@SerializedName(value="chargeFeeFlag")
	@JsonProperty("Charge Fuel Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Fuel Fee Flag must be N or Y")
	@NotBlank(message = "Charge Fuel Fee Flag is required")
	@ExcelCellName("IFFLG2")
	private String chargeFuelFeeFlag;
	
	@SerializedName(value="chargeEnvironmentalFee")
	@JsonProperty("Charge Env Fee")
	@Pattern(regexp = "^[NYny]$", message = "Charge Environmental Fee Flag must be N or Y")
	@NotBlank(message = "Charge Environmental Fee is required")
	@ExcelCellName("IFEFEE")
	private String chargeEnvironmentalFee;

	@SerializedName(value="autoManualFlag")
	@JsonProperty("Auto Manual Flag")
	@Pattern(regexp = "^[AMam]$", message = "Invoice Auto Manual FLag must be either A or M")
	@NotBlank(message = "Invoice Auto Manual Flag is required")
	@ExcelCellName("CUAMFG")
	private String invoiceAutoManualFlag;

	@SerializedName(value="invoiceDestination")
	@JsonProperty("Invoice Destination")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invoice Destination can only contain letters and numbers")
	@Size(max = 3, message = "Invoice Destination code max length is 3")
	@ExcelCellName("CUINDS")
	private String invoiceDestination ;
	
	@SerializedName(value="reviewPolicy")
	@JsonProperty("Review Policy")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Ops Risk Code can only contain letters and numbers")
	@Size(max = 5, message = "Ops Risk code max length is 5")
	@ExcelCellName("CCRVPC")
	private String opsRiskCode ;

	@SerializedName(value="ediFlag")
	@JsonProperty("Edi Flag")
	@NotBlank(message = "EDI Flag is required")
	@Pattern(regexp = "^[yYnNbB]$", message = "EDI can only be N,Y, or B")
	@ExcelCellName("IFFLG3")
	private String ediFlag;

	@SerializedName(value="parentId")
	@JsonProperty("Parent Id")
	@Pattern(regexp = "^[0-9]*$", message = "CBS Parent Id can only contain numeric characters")
    @Size(max = 9, message =  "CBS Parent ID must be 9 numeric characters or less in length")
	@ExcelCellName("UAPARID")
	private String cbsParentId;
	
	@SerializedName(value="accountBroker")
	@JsonProperty("Acct Broker")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Broker Code can only contain alpha numeric characters")
	@Size(max = 5, message = "Broker Code must be 5 alpha numeric characters or less in length")
	@ExcelCellName("IFBROK")
	private String brokerCode;
	
	@SerializedName(value="cbsLocationStoreId")
	@JsonProperty("Cbs Loc. StoreId")
    @Pattern(regexp = "^[a-zA-Z0-9-.&* ]*$", message = "Invalid CBS Location Id, can only accept alpha numeric characters and - , ., &, * ")
	@Size(max = 20, message = "CBS Location Id cannot be more than 10 chars")
	@ExcelCellName("CUUFL4/CUUFL2")
	private String cbsLocationId;
	
	@SerializedName(value="accountGroupCode")
	@JsonProperty("Acct Group Code")
	@Pattern(regexp = "^[1-4]$" , message = "Account Group Code can only be 1,2,3 or 4")
	@Size(max = 1, message = "Account Group Code max length is 1 characters")
	@NotBlank(message = "Account Group Code is required")	
	@ExcelCellName("UAACGRPID")
	private String accountGroupCode;
	
	@SerializedName(value="cmib")
	@JsonProperty("Cmib")
    @Pattern(regexp = "^[YyNn]$", message = "CMIB can only be Y or N")
    @NotBlank(message = "CMIB is required")	
	@ExcelCellName("UAFLAG3")
	private String cmib;
	
	@SerializedName(value="emailAddress")
	@JsonProperty("Email Address")
	@Pattern(message="Email invalid", 
		regexp = "^([a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})|\\s|$")
	@ExcelCellName("CST_EMB1")
	private String emailAddress ;

	@ExcludeGson
	@Valid
	private List<AobSiteInformation> aobSites;
	
	
	@ExcelCell(36)
	@SerializedName(value="ACCTERROR")
	private String uploadError = "";

	public List<AobSiteInformation> getAobSites() {
		return aobSites;
	}
	public void setAobSites(List<AobSiteInformation> aobSites) {
		this.aobSites = aobSites;
	}

    public String getCompanyNumber() {
		return companyNumber.trim()	;
	}
	public void setCompanyNumber(String companyNumber) {
		if (companyNumber.length() == 2){
			this.companyNumber = "0"+companyNumber.trim();
		} else if (companyNumber.length() == 1) {
			this.companyNumber = "00"+companyNumber.trim();
		}
		this.companyNumber = companyNumber.trim();
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getChargeFuelFeeFlag() {
		return chargeFuelFeeFlag;
	}
	public void setChargeFuelFeeFlag(String chargeFuelFeeFlag) {
		this.chargeFuelFeeFlag = chargeFuelFeeFlag;
	}

	public String getChargeEnvironmentalFee() {
		return chargeEnvironmentalFee;
	}
	public void setChargeEnvironmentalFee(String chargeEnvironmentalFee) {
		this.chargeEnvironmentalFee = chargeEnvironmentalFee;
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
	public String getNationalAccountNumber() {
        return nationalAccountNumber;
    }

    public void setNationalAccountNumber(String nationalAccountNumber) {
        this.nationalAccountNumber = nationalAccountNumber;
    }

    public String getEdiFlag() {
        return ediFlag;
    }

    public void setEdiFlag(String ediFlag) {
        this.ediFlag = ediFlag;
    }

    public String getCbsParentId() {
        return cbsParentId;
    }

    public void setCbsParentId(String cbsParentId) {
        this.cbsParentId = cbsParentId;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getCbsLocationId() {
        return cbsLocationId;
    }

    public void setCbsLocationId(String cbsLocationId) {
        this.cbsLocationId = cbsLocationId;
    }

    public String getAccountGroupCode() {
        return accountGroupCode;
    }

    public void setAccountGroupCode(String accountGroupCode) {
        this.accountGroupCode = accountGroupCode;
    }

    public String getCmib() {
        return cmib;
    }

    public void setCmib(String cmib) {
        this.cmib = cmib;
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

	 
	public String getCustomerAlias() {
		return customerAlias;
	}

	 
	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}

	 
	public String getAccountOpenDate() {
		return accountOpenDate;
	}

	 
	public void setAccountOpenDate(String accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public String getCustomerCategoryCode() {
		return customerCategoryCode;
	}

	public void setCustomerCategoryCode(String customerCategoryCode) {
		this.customerCategoryCode = customerCategoryCode;
	}

	@JsonIgnore
	public String getParentChildKey() {
		return this.companyNumber + "-" + this.accountNumber; 
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
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
}
