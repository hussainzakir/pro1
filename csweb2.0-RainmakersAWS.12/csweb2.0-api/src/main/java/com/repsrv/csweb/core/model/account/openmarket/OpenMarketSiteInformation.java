package com.repsrv.csweb.core.model.account.openmarket;

import java.util.Collections;
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
@JsonPropertyOrder({"Company #","Cust. Account", "Site", "Site Name","Address #", "Pre Directional","Address Name", "Street Type", 
"Post Directional", "Suite", "Address Line 2", "City","State", "Postal Code", "Area Code", "Contact Phone", "Phone Extension", "Territory",
"Sales Rep #", "Start Date", "Close Date", "Tax Exemption #", "Longitude", "Latitude", "Grid"})

@Getter
@Setter

@OpenMarketValidSheetRowData(sheetId = SheetId.SITE)
@ExcelSheet("Site Information")
@Valid
public class OpenMarketSiteInformation extends Row{

	public static final String LENGCONT_REQ = "Length of Contract is required";
	public static final String LENGCONT_MAX_NUM_LENGTH = "Length of Contract can only be 3 numeric characters";
	public static final String CONTREVDATE_MAX_NUM_LENGTH = "Contract Review Date invalid";
	public static final String PRE_DIREC_UNIQUE = "Pre Directional must be N, E, W, S, NE, NW, SE, or SW";
	public static final String NAICS_REQ = "Site Code is required";
	public static final String NAICS_MAX_LENGTH = "Site Code cannot be longer than 6";
	public static final String AUTH_NAME_ALPHA_NUM = "Authorized Name can only consist of alpha numeric, space, hyphen, hash, ampersand, colon and period characters";	
	public static final String AUTH_NAME_MAX_LENGTH = "Authorized Name cannot be longer than 25 chars";
	public static final String SERV_CONT_ALPHA_NUM = "Service Contact can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SERV_CONT_MAX_LENGTH = "Service Contact cannot be longer than 25 chars";
	public static final String SUITE_NUM_ALPHA_NUM = "Suite Number can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SUITE_NUM_MAX_LENGTH = "Suite Number cannot be longer than 5 chars";
	public static final String ADD_LINE_TWO_ALPHA_NUM = "Address Line 2 can only consist of alpha numeric, space, hyphen, colon, hash, ampersand characters";
	public static final String ADD_LINE_TWO_MAX_LENGTH = "Address Line 2 cannot be longer than 30 chars";
	public static final String SITE_NAME_NEEDED_CHARS = "Site Name can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SITE_NAME_MAX_LENGTH = "Site Name cannot be longer than 30 chars";
	
	private static final String TAB_NAME = "Site Information";
	
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("ASCOMP")
	private String companyNumber;

	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@Pattern(regexp = "^\\d{1,7}$", message = "Account number must be 7 numeric characters or less in length")
	@NotBlank(message = "Site Account Number is required")
	@ExcelCellName("ASACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 5 numeric characters or less in length")
	@ExcelCellName("ASSITE")
	private String siteNumber;
	
	@SerializedName(value="siteName")
	@JsonProperty("Site Name")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]+", message = SITE_NAME_NEEDED_CHARS)
	@NotBlank(message = "Site Name is required")
	@Size(max = 30, message = SITE_NAME_MAX_LENGTH)
	@ExcelCellName("ASSNAM")
	private String siteName;
	
	@SerializedName(value="addressNumber")
	@JsonProperty("Address #")
	@Pattern(regexp = "^[a-zA-Z0-9-\\/\\s#:]+$", message = "Address Number can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 15, message = "Address number cannot be longer than 15 chars")
	@ExcelCellName("ASADNO")
	private String addressNumber;
	
	@SerializedName(value="preDirectional")
	@JsonProperty("Pre Directional")
	@Pattern(regexp = "^(?:N|E|W|S|NE|NW|SE|SW)?$", message = PRE_DIREC_UNIQUE)
	@ExcelCellName("ATTXT2")
	private String preDirectional;
	
	@SerializedName(value="addressName")
	@JsonProperty("Address Name")
	@Pattern(regexp = "^[a-zA-Z0-9-\\/\\s]+$", message = "Address name can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 30, message = "Address name cannot be longer than 30 chars")
	@NotBlank(message = "Site Address Name is required")
	@ExcelCellName("ASADNM")
	private String addressName;
	
	@SerializedName(value="streetType")
	@JsonProperty("Street Type")
	@Pattern(regexp = "^(\\w+([\\s-_]\\w+)*)|\\s|$", message = "Street type can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 4, message = "Street type cannot be longer than 4 chars")
	@ExcelCellName("ASSTTY")
	private String streetType;
	
	@SerializedName(value="streetDirection")
	@JsonProperty("Post Directional")
	@Size(max = 3, message = "Post Directional cannot be longer than 3 chars")
	@ExcelCellName("ASSTDR")
	private String postDirectional;

	@SerializedName(value="suite")
	@JsonProperty("Suite")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]*", message = SUITE_NUM_ALPHA_NUM)
	@Size(max = 5, message = SUITE_NUM_MAX_LENGTH)
	@ExcelCellName("ASSUIT")
	private String suiteNumber;
	
	@SerializedName(value="addressLine")
	@JsonProperty("Address Line 2")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:]*", message = ADD_LINE_TWO_ALPHA_NUM)
	@Size(max = 30, message = ADD_LINE_TWO_MAX_LENGTH)
	@ExcelCellName("ASADR2")
	private String addressLine2;

	@SerializedName(value="city")
	@JsonProperty("City")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "City can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 20, message = "City cannot be longer than 20 chars")
	@NotBlank(message = "Site City is required")
	@ExcelCellName("ASCITY")
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only consist of alpha characters")
	@Size(max = 3, message = "State cannot be longer than 3 chars")
	@NotBlank(message = "Site State is required")
	@ExcelCellName("ASSTPR")
	private String state;
	
	@SerializedName(value="zipCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^\\d{5}([\\-]?\\d{4})?$", message = "Postal code format invalid")
	@Size(max = 10, min = 5, message = "Postal code cannot be shorter than 5 or more than 10 characters")
	@NotBlank(message = "Site Postal Code is required")
	@ExcelCellName("ASZPPC")
	private String postalCode;
	
	@SerializedName(value="areaCode")
	@JsonProperty("Area Code")
	@Pattern(regexp = "^\\d{3}?$", message = "Area code can only be 3 numeric characters")
	@NotBlank(message = "Site Area Code is required")
	@ExcelCellName("ASARCD")
	private String areaCode;
	
	@SerializedName(value="contactPhone")
	@JsonProperty("Contact Phone")
	@Pattern(regexp = "^\\d{7}?$", message = "Contact phone format invalid")
	@Size(max = 7, min = 7, message = "Contact phone must be seven numeric characters")
	@NotBlank(message = "Contact Phone is required")
	@ExcelCellName("ASPHNE")
	private String contactPhone;
	
	@SerializedName(value="phoneExtension")
	@JsonProperty("Phone Extension")
	@Pattern(regexp = "^\\d{0,3}$", message = "Phone extension can only be up to 3 numeric characters")
	@ExcelCellName("ASPEXT")
	private String phoneExtension;
	
	@SerializedName(value="startDate")
	@JsonProperty("Start Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", message = "Start date invalid")
	@NotBlank(message = "Start Date is required")
	@ExcelCellName("ASSTDT")
	private String startDate;

	@SerializedName(value="closeDate")
	@JsonProperty("Close Date")
	@Pattern(regexp = "^((\\d{8})|\\s|)$", 
			message = "Close date invalid")
	@ExcelCellName("ASCLDT")
	private String closeDate;

	@SerializedName(value="salesRepNumber")
	@JsonProperty("Sales Rep #")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Sales Rep cannot be blank, can only be alphanumeric and no more than 10 characters in length")
	@Size(max = 10, message = "Sales Rep cannot be longer than 10 chars")
	@NotBlank(message = "Sales Rep Number is required")
	@ExcelCellName("ASSLNO")
	private String salesRepNumber;

	@SerializedName(value="territory")
	@JsonProperty("Territory")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Territory can only consist of alpha numeric characters")
	@Size(max = 3, message = "Territory cannot be longer than 3 chars")
	@ExcelCellName("ASTERR")
	private String territory;

	@SerializedName(value="taxExemptionNumber")
	@JsonProperty("Tax Exemption #")
	@Pattern(regexp = "^[yY]?$", message = "Tax Exempt can only be blank or 'Y'")
	@ExcelCellName("ASTXEX")
	private String taxExcempt;
	
	@SerializedName(value="longitude")
	@JsonProperty("Longitude")
	@NotBlank(message = "Longitude is required")
	@Pattern(regexp = "^[-.0-9]{1,11}$", message = "Longitude is required, must be 11 numeric characters or less in length and no spaces")
	@Digits(integer = 3, fraction = 9, message = "Longitude must have this format: '23.343112'")
	@ExcelCellName("ATLONG")
	private String longitude;
	
	@SerializedName(value="latitude")
	@JsonProperty("Latitude")
	@NotBlank(message = "Latitude is required")
	@Pattern(regexp = "^[-.0-9]{1,11}$", message = "Latitude is required, must be 11 numeric characters or less in length and no spaces")
	@Digits(integer = 3, fraction = 9, message = "Latitude must have this format: '23.343112'")
	@ExcelCellName("ATLATI")
	private String latitude;
	
	@SerializedName(value="grid")
	@JsonProperty("Grid")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,10}$", message = "Grid is required and can only be 10 alphanumeric characters max")
	@NotBlank(message = "Grid is required")
	@ExcelCellName("ATTXT3")
	private String grid;

	@ExcludeGson
	@Valid
	private List<OpenMarketContainerInformation> openMarketContainerInformation;

	@JsonIgnore
	private OpenMarketAccountInformation openMarketAccountInformation;

	public List<OpenMarketContainerInformation> getOpenMarketContainerInformation() {
		return openMarketContainerInformation == null ? Collections.emptyList() : openMarketContainerInformation;
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
	@Override
	public String getKey() {
		return companyNumber + "-" + accountNumber + "-" + siteNumber;
	}
	
	@JsonIgnore
	@Override
	public String getParentKey() {
		return this.companyNumber + "-" + this.accountNumber;
	}
	
	@JsonIgnore
	@Override
	public String getTabName() {
		return TAB_NAME;
	}

	@Override
	public String getKeyName() {
		return "Site Number";
	}
}
	
	
	

	
	
	

	