package com.repsrv.csweb.core.model.account.openmarket;

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
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.openmarket.validators.OpenMarketValidSheetRowData;
import com.repsrv.csweb.core.gson.ExcludeGson;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;

import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({
"Company #","Account #","Customer Name","Care Of","Address Line 1","Address Line 2","Address Line 3","City","State","Postal Code","Country",
"Cust. Alias","Telephone #","Fax #","Acq. Code","Inv Grp Code","Acct Class Code","Cust Cat Code","Suspendable Flag","Acct Open Date",
"Acct Closed Date","Charge Inv Fee Flag","Chg Svc Int Fee Flag","Chg Late Fee Flag", "Late Pay Fee Policy","Next Review Date","Chg Fuel Fee Flag",
"Chg Env Fee", "Frf & Erf On Admin", "Auto Manual Flag","Invoice Destination","Ops Risk Code","Email Address"})
@OpenMarketValidSheetRowData(sheetId = SheetId.ACCOUNT)
@Valid
@ExcelSheet("Account Information")

@Setter
@Getter

public class OpenMarketAccountInformation extends Row{
	
	private static final String TAB_NAME = "Account Information";

	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("CUCO")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Account #")
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$",  message = "Account number must be 7 numeric characters or less in length")
	@ExcelCellName("CUCUNO")
	private String accountNumber;

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
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]+$", message = "Address Line 2 can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Address Line 2 cannot be more than 30 digits")
	@NotBlank(message = "Address Line 2 is required")
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
	@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Postal code format invalid")
	@NotBlank(message = "Postal Code is required")
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
	
	@SerializedName(value="acquisitionCode")
	@JsonProperty("Acq. Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Aquisition Code can only contain letters")
	@Size(max = 2, message = "Aquisition code max length is 2")
	@ExcelCellName("CUAQSN")
	private String acquisitionCode;
	
	@SerializedName(value="invoiceGroupCode")
	@JsonProperty("Inv Grp Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Invoice Group Code invalid")
	@NotBlank(message = "Invoice Group code is required")
	@ExcelCellName("CUINGP")
	private String invoiceGroupCode;
	
	@SerializedName(value="majorClassCode")
	@JsonProperty("Acct Class Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Account Class Code invalid")
	@NotBlank(message = "Account Class Code is required")
	@ExcelCellName("CUMACL")
	private String accountClassCode;
	
    @SerializedName(value="customerCategoryCode")
	@JsonProperty("Cust Cat Code")
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
	
	@SerializedName(value="accountCloseDate")
	@JsonProperty("Acct Closed Date")
	@Pattern(regexp = "^((\\d{8})|\\s*|)$", message = "Account closed date invalid")
	@ExcelCellName("CCACD8")
	private String accountClosedDate;
	
	@SerializedName(value="chargeInvoiceFee")
	@JsonProperty("Charge Inv Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Inv Fee Flag must be N or Y")
	@NotBlank(message = "Charge Invoice Fee Flag is required")
	@ExcelCellName("IFIFEE")
	private String chargeInvoiceFeeFlag;
	
	@SerializedName(value="chargeSuspensionFee")
	@JsonProperty("Chg Svc Int Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Service Interrupt Fee Flag must be N or Y")
	@NotBlank(message = "Charge Service Interrupt Fee Flag is required")
	@ExcelCellName("IFSFEE")
	private String chargeServiceInterruptFeeFlag;
	
	@SerializedName(value="chargeLateFee")
	@JsonProperty("Chg Late Fee FLag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Late Fee Flag must be N or Y")
	@NotBlank(message = "Charge Late Fee flag is required")
	@ExcelCellName("IFLFEE")
	private String chargeLateFeeFlag;
	
	@SerializedName(value="lateFeePolicy")
	@JsonProperty("Late Pay Fee Policy")
	//business needs to be able to submit special characters for now. 3/11/2024   -Adnan Varela
	// @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Late Pay Fee Policy can only contain letters and numbers")
	@Size(max = 10, message = "Late Pay Fee Policy code max length is 10")
	@ExcelCellName("IFLFPC")
	private String latePayFeePolicy;
	
	@SerializedName(value="nextReviewDate")
	@JsonProperty("Next Review Date")
	@Pattern(regexp = "^(99999999|[0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
			message = "Next Review date invalid")
	@NotBlank(message = "Next Review Date is required")
	@ExcelCellName("IFDAT1")
	private String nextReviewDate;
	
	@SerializedName(value="chargeFeeFlag")
	@JsonProperty("Chg Fuel Fee Flag")
	@Pattern(regexp = "^[NYny]$", message = "Charge Fuel Fee Flag must be N or Y")
	@NotBlank(message = "Charge Fuel Fee Flag is required")
	@ExcelCellName("IFFLG2")
	private String chargeFuelFeeFlag;

	@SerializedName(value="chargeEnvironmentalFee")
	@JsonProperty("Chg Env Fee")
	@Pattern(regexp = "^[NYny]$", message = "Charge Environmental Fee Flag must be N or Y")
	@NotBlank(message = "Charge Environmental Fee is required")
	@ExcelCellName("IFEFEE")
	private String chargeEnvironmentalFee;
	
	@SerializedName(value="frfErfFlag")
	@JsonProperty("Frf & Erf On Admin")
	@Pattern(regexp = "^[NFEBnfeb]$", message = "FRF & ERF On Admin must be either N, F, E or B")
	@NotBlank(message = "FRF and ERF On Admin is required")
	@ExcelCellName("FEAFLG")
	private String frfAndErfOnAdmin;

	@SerializedName(value="autoManualFlag")
	@JsonProperty("Auto Manual Flag")
	@Pattern(regexp = "^[AMam]$", message = "Invoice Auto Manual Flag must be either A or M")
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
	@JsonProperty("Ops Risk Code")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Ops Risk Code can only contain letters and numbers")
	@Size(max = 5, message = "Ops Risk code max length is 5")
	@ExcelCellName("CCRVPC")
	private String opsRiskCode;
	
	@SerializedName(value="emailAddress")
	@JsonProperty("Email Address")
	@Pattern(message="Email invalid", 
		regexp = "^([a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})|\\s|$")
	@Size(max = 60, message = "Email Address max length is 60")
	@ExcelCellName("CST_EMB1")
	private String emailAddress;
	
	@ExcludeGson
	@Valid
	private List<OpenMarketSiteInformation> openMarketSiteInformation;

    public String getCompanyNumber() {
		return companyNumber.trim();
	}
	public void setCompanyNumber(String companyNumber) {
		if (companyNumber.length() == 2){
			this.companyNumber = "0"+companyNumber.trim();
		} else if (companyNumber.length() == 1) {
			this.companyNumber = "00"+companyNumber.trim();
		}
		this.companyNumber = companyNumber.trim();
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

	@Override
	public String getKeyName() {
		return "Account Number";
	}
	
}
