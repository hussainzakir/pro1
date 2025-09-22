package com.repsrv.csweb.core.model.account.aob3;

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
import com.repsrv.csweb.core.gson.ExcludeGson;
import com.repsrv.csweb.core.model.account.imports.Row;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({
"Company #","Customer Account","OTC Account #", "Care Of","Address Line 1","City","State","Postal Code","Country",
"Inv Grp Code","Cust. Cat. Code", "Acct Open Date", "Charge Fuel Fee Flag", "Charge Env Fee", "Parent Id",
 "Cbs Loc. StoreId",  "Billing Reference", "Acquisition From"})
@Valid
@ExcelSheet("Account Information")

@Getter
@Setter

public class Aob3AccountInfo extends Row{
    
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

    @SerializedName(value="otcAccountNumber")
	@JsonProperty("OTC Account #")
	@NotBlank(message = "OTC Account Number is required")
	@Pattern(regexp = "^\\d{1,12}$",  message = "Account number must be 12 numeric characters or less in length")
	@ExcelCellName("CUORACCT")
	private String otcAccountNumber;

	@SerializedName(value="careOfField")
	@JsonProperty("Care Of")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#]*$", message = "Care Of Field can only consist of alpha numeric, space, - : & and # characters")
	@Size(max = 30, message = "Care Of Field longer than 30 chars")
	@ExcelCellName("CareOfField")
	private String careOf;
	
	@SerializedName(value="addressLine1")
	@JsonProperty("Address Line 1")
	@Size(max = 30, message = "Address Line 1 cannot be more than 30 digits")
	@ExcelCellName("AddresLine1")
	private String addressLine1;

	@SerializedName(value="city")
	@JsonProperty("City")
	@Pattern(regexp = "^(\\w+(\\s\\w+)*)$" , message = "City can only contain alphanumeric and space characters")
	@Size(max = 20, message = "City max length is 20 characters")
	@NotBlank(message = "City is required")	
	@ExcelCellName("City")
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only contain alpha numeric characters")
	@Size(max = 3, min = 2, message = "State must be 2 or more characters and not exceed 3 in length")
	@NotBlank(message = "State is required")
	@ExcelCellName("State")
	private String state;
	
	@SerializedName(value="postalCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^[a-zA-Z0-9-]*$", message = "Postal code format invalid")
	@Size(max = 10, message = "Postal Code can't be longer than 10 chars")
	@ExcelCellName("PostalCode")
	private String postalCode;

	@SerializedName(value="country")
	@JsonProperty("Country")
	@Size(max =3, min = 3, message = "Country code invalid")
	@NotBlank(message = "Country is required")
	@ExcelCellName("Country")
	private String country;
	
	@SerializedName(value="invoiceGroupCode")
	@JsonProperty("Inv Grp Code")
	@Pattern(regexp ="^[a-zA-Z0-9]$", message = "Invoice Group Code invalid")
	@NotBlank(message = "Invoice Group code is required")
	@ExcelCellName("CUINGP")
	private String invoiceGroupCode;
	
    @SerializedName(value="customerCategoryCode")
	@JsonProperty("Cust. Cat. Code")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Customer Category Code can only contain alpha characters")
    @Size(max = 5, message = "Customer Category Code must be 5 alpha numeric characters or less in length")
	@NotBlank(message = "Customer Category Code is required")
	@ExcelCellName("CUCATG")
	private String customerCategoryCode;
	
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

	@SerializedName(value="parentId")
	@JsonProperty("Parent Id")
	@Pattern(regexp = "^[0-9]*$", message = "CBS Parent Id can only contain numeric characters")
    @Size(max = 9, message =  "CBS Parent ID must be 9 numeric characters or less in length")
	@ExcelCellName("UAPARID")
	private String cbsParentId;
	
	@SerializedName(value="cbsLocationStoreId")
	@JsonProperty("Cbs Loc. StoreId")
    @Pattern(regexp = "^[a-zA-Z0-9-.&* ]*$", message = "Invalid CBS Location Id, can only accept alpha numeric characters and - , ., &, * ")
	@Size(max = 20, message = "CBS Location Id cannot be more than 10 chars")
	@ExcelCellName("CUUFL4")
	private String cbsLocationId;

    @SerializedName(value="billingReferenceBillTo")
	@JsonProperty("Billing Reference")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Billing Reference can only contain letters and numbers")
	@ExcelCellName("BillingReference")
	private String billingReference ;

    @SerializedName(value="acquisitionFrom")
	@JsonProperty("Acquisition From")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Acquisition From can only contain letters and numbers")
	@ExcelCellName("AcquisitionFrom")
	private String acquisitionFrom ;

	@JsonIgnore
	@Override
	public String getTabName() {
		return TAB_NAME;
	}

	@Override
	public String getKeyName() {
		return "Account Number";
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

	@ExcludeGson
	@Valid
	private List<Aob3SiteInfo> aob3Sites;
	

}
