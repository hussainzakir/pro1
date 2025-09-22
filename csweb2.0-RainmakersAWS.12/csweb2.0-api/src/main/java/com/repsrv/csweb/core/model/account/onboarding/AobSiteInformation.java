package com.repsrv.csweb.core.model.account.onboarding;

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
@JsonPropertyOrder({"Company #","Cust. Account", "Site", "Site Name","Address #", "Pre Directional","Address Name", "Suite", "Street Type", 
"Post Directional", "Address Line 2", "City","State", "Postal Code", "Area Code", "Contact Phone", "Phone Extension", "Fax Number",
 "Start Date", "CSA Number", "Contract Status", "Length of Contract", "Review Date", "Contract Price Index", "Purchase Order #", 
 "Service Contact", "Service Contact Title", "Authorized Name", "Authorized Title", "Sales Rep #", "Territory", "Site Code", 
 "Tax Exemption #"})
@AobValidSheetRowData(sheetId = SheetId.SITE)
@ExcelSheet("Site Information")
@Valid

public class  AobSiteInformation extends Row {

	private static final String TAB_NAME = "Site Information";
	public static final String LENGCONT_REQ = "Length of Contract is required";
	public static final String LENGCONT_MAX_NUM_LENGTH = "Length of Contract can only be 3 numeric characters";
	public static final String CONTSTATUS_REQ = "Contract Status is required";
	public static final String CONTSTATUS_MAX_NUM_LENGTH = "Contract Status can only be 2 alpha numeric characters";
	public static final String CONTSTATUS_ALPHANUM_REQ = "Contract Status can can only consist of alpha numeric characters";
	public static final String CONTREVDATE_MAX_NUM_LENGTH = "Contract Review Date invalid";
	public static final String PONUM_ALPHA_NUM= "PO can only consist of alpha numeric characters";
	public static final String PONUM_MAX_NUM_LENGTH = "PO cannot be longer than 15 chars";
	public static final String CPI_REQ = "Cpi is required";
	public static final String CPI_UNIQUE = "Contract Price Index Flag must be Y or N";
	public static final String PRE_DIREC_UNIQUE = "Pre Directional must be N, E, W, S, NE, NW, SE, or SW";
	public static final String AUTH_TITLE_ALPHA_NUM = "Authorized Title  can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String AUTH_TITLE_MAX_LENGTH = "Authorized Title cannot be longer than 25 chars";
	public static final String NAICS_REQ = "Naics is required";
	public static final String NAICS_MAX_LENGTH = "Naics cannot be longer than 6";
	public static final String SHARED_CONT_FLAG_UNIQUE = " Shared container flag can only be blank or 'Y'";
	public static final String AUTH_NAME_ALPHA_NUM = "Authorized Name can only consist of alpha numeric, space, hyphen, hash, ampersand, colon and period characters";	
	public static final String AUTH_NAME_MAX_LENGTH = "Authorized Name cannot be longer than 25 chars";
	public static final String SERV_CONT_ALPHA_NUM = "Service Contact can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SERV_CONT_MAX_LENGTH = "Service Contact cannot be longer than 25 chars";
	public static final String SUITE_NUM_ALPHA_NUM = "Suite Number can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SUITE_NUM_MAX_LENGTH = "Suite Number cannot be longer than 5 chars";
	public static final String ADD_LINE_TWO_ALPHA_NUM = "Address Line 2 can only consist of alpha numeric, space, hyphen, colon, hash, ampersand characters";
	public static final String ADD_LINE_TWO_MAX_LENGTH = "Address Line 2 cannot be longer than 30 chars";
	public static final String SERV_CONT_TITLE_MAX_LENG = "Service contract title cannot be longer than 25 chars";
	public static final String SERV_CONT_TITLE_ALPHA_NUM = "Service contract title can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SITE_NAME_NEEDED_CHARS = "Site Name can only consist of alpha numeric, space, hyphen, colon, hash, ampersand and period characters";
	public static final String SITE_NAME_MAX_LENGTH = "Site Name cannot be longer than 25 chars";

	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("ASCOMP")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@Pattern(regexp = "^\\d{0,7}$", message = "Account number must be 7 numeric characters or less in length")
	@ExcelCellName("ASACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 7 numeric characters or less in length")
	@ExcelCellName("ASSITE")
	private String siteNumber;
	
	@SerializedName(value="siteName")
	@JsonProperty("Site Name")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]+", message = SITE_NAME_NEEDED_CHARS)
	@Size(max = 30, message = SITE_NAME_MAX_LENGTH)
	@ExcelCellName("ASSNAM")
	private String siteName;
	
	@SerializedName(value="addressNumber")
	@JsonProperty("Address #")
	@Pattern(regexp = "^[a-zA-Z0-9-\\/\\s#:]+$", message = "Address Number can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 15, message = "Address number cannot be longer than 15 chars")
	@NotBlank(message = "Address Number is required")
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
	@NotBlank(message = "Address Name is required")
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
	@ExcelCellName("ASCITY")
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only consist of alpha characters")
	@Size(max = 3, message = "State cannot be longer than 3 chars")
	@ExcelCellName("ASSTPR")
	private String state;
	
	@SerializedName(value="zipCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^\\d{5}([\\-]?\\d{4})?$", message = "Postal code format invalid")
	@Size(max = 10, min = 5, message = "Postal code cannot be shorter than 5 or more than 10 characters")
	@ExcelCellName("ASZPPC")
	private String postalCode;
	
	@SerializedName(value="areaCode")
	@JsonProperty("Area Code")
	@Pattern(regexp = "^\\d{3}?$", message = "Area code can only be 3 numeric characters")
	@ExcelCellName("ASARCD")
	private String areaCode;
	
	@SerializedName(value="contactPhone")
	@JsonProperty("Contact Phone")
	@Pattern(regexp = "^\\d{7}?$", message = "Contact phone format invalid")
	@Size(max = 7, min = 7, message = "Contact phone must be seven numeric characters")
	@ExcelCellName("ASPHNE")
	private String contactPhone;
	
	@SerializedName(value="phoneExtension")
	@JsonProperty("Phone Extension")
	@Pattern(regexp = "^\\d{0,3}$", message = "Phone extension can only be up to 3 numeric characters")
	@ExcelCellName("ASPEXT")
	private String phoneExtension;
	
	@SerializedName(value="siteFaxNumber")
	@JsonProperty("Fax Number")
	@Pattern(regexp = "^(\\d{10}?)|\\s|$", message = "Fax number can only be 10 numeric characters")
	@ExcelCellName("ATTXT1")
	private String faxNumber;

	@SerializedName(value="startDate")
	@JsonProperty("Start Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", message = "Start date invalid")
	@NotBlank(message = "Start Date is required")
	@ExcelCellName("ASSTDT")
	private String startDate;

	@SerializedName(value="siteCSA")
	@JsonProperty("CSA Number")
	@Pattern(regexp = "^[AaSsEeCcMmRrNnPp ][a-zA-Z0-9-]*$", message = "Contract# must be start with A,S,E,C,M,R,N or P")
	@Size(max = 10, message = "CSA Number cannot be longer than 10 chars")
	@ExcelCellName("ASCTRT")
	private String csaNumber;

	@SerializedName(value="contractStatus")
	@JsonProperty("Contract Status")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = CONTSTATUS_ALPHANUM_REQ )
	@Size(max = 2, message = CONTSTATUS_MAX_NUM_LENGTH )
	@NotBlank(message = CONTSTATUS_REQ)
	@ExcelCellName("ASCTST")
	private String contractStatus;

	@SerializedName(value="csaTerm")
	@JsonProperty("Length of Contract")
	@Pattern(regexp = "^\\d{0,3}$", message = LENGCONT_MAX_NUM_LENGTH )
	@NotBlank(message = LENGCONT_REQ)
	@ExcelCellName("ASTERM")
	private String lengthOfContract;
	
	@SerializedName(value="reviewDate")
	@JsonProperty("Review Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", message = CONTREVDATE_MAX_NUM_LENGTH)
	@ExcelCellName("ASRVDT")
	private String contractReviewDate;
	
	@SerializedName(value="contractPriceIndex")
	@JsonProperty("Contract Price Index")
	@Pattern(regexp = "^[YNny]$", message = CPI_UNIQUE)
	@NotBlank(message = CPI_REQ )
	@ExcelCellName("ASCPI")
	private String cpiFlag;

	@SerializedName(value="purchaseOrderNumber")
	@JsonProperty("Purchase Order #")
	@Pattern(regexp = "^[a-zA-Z0-9.-]{0,15}$", message = PONUM_ALPHA_NUM)
	@Size(max = 15, message = PONUM_MAX_NUM_LENGTH)
	@ExcelCellName("ASPO")
	private String poNumber;
	
	@SerializedName(value="serviceContact")
	@JsonProperty("Service Contact")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]*", message = SERV_CONT_ALPHA_NUM)
	@Size(max = 25, message =  SERV_CONT_MAX_LENGTH)
	@ExcelCellName("ASCNAM")
	private String serviceContact;


	@SerializedName(value="serviceContactTitle")
	@JsonProperty("Service Contact Title")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]*$", message = SERV_CONT_TITLE_ALPHA_NUM)
	@Size(max =25, message =  SERV_CONT_TITLE_MAX_LENG)
	@ExcelCellName("ASCTTL")
	private String serviceContactTitle;

	@SerializedName(value="authorizedName")
	@JsonProperty("Authorized Name")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]+", message = AUTH_NAME_ALPHA_NUM)
	@Size(max = 25, message = AUTH_NAME_MAX_LENGTH)
	@ExcelCellName("ASANAM")
	private String authorizedName;

	@SerializedName(value="authorizedTitle")
	@JsonProperty("Authorized Title")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-&\\/:.]*$", message = AUTH_TITLE_ALPHA_NUM )
	@Size(max = 25, message = AUTH_TITLE_MAX_LENGTH )
	@ExcelCellName("ASATTL")
	private String authorizedTitle;																										

	@SerializedName(value="salesRepNumber")
	@JsonProperty("Sales Rep #")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Sales Rep cannot be blank, can only be alphanumeric and no more than 10 characters in length")
	@Size(max = 10, message = "Sales Rep cannot be longer than 10 chars")
	@ExcelCellName("ASSLNO")
	private String salesRepNumber;

	@SerializedName(value="territory")
	@JsonProperty("Territory")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Territory can only consist of alpha numeric characters")
	@Size(max = 3, message = "Territory cannot be longer than 3 chars")
	@ExcelCellName("ASTERR")
	private String territory;

	@SerializedName(value="siteCode")
	@JsonProperty("Site Code")
	@Size(max = 6, message = NAICS_MAX_LENGTH)
	@NotBlank(message = NAICS_REQ)
	@ExcelCellName("ATSIC")
	private String naics;

	@SerializedName(value="taxExemptionNumber")
	@JsonProperty("Tax Exemption #")
	@Pattern(regexp = "^[yY ]*$", message = "Tax Exempt can only be blank or 'Y'")
	@ExcelCellName("ASTXEX")
	private String taxExcempt;
	
	@ExcludeGson
	@Valid
	private List<AobContainerInformation> aobContainers;

	@JsonIgnore
	private AobAccountInformation aobAccountInformation;

	public AobAccountInformation getAobAccountInformation() {
		return aobAccountInformation;
	}

	public void setAobAccountInformation(AobAccountInformation aobAccountInformation) {
		this.aobAccountInformation = aobAccountInformation;
	}	

	@ExcelCell(34)
	@SerializedName(value="SITEERROR")
	private String uploadError = "";
	
	public List<AobContainerInformation> getAobContainers() {
		return aobContainers == null ? Collections.emptyList() : aobContainers;
	}
	public void setAobContainers(List<AobContainerInformation> aobContainers) {
		this.aobContainers = aobContainers;
	}
	public String getCompanyNumber() {
		return companyNumber;
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
	public String getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	public String getPreDirectional() {
		return preDirectional;
	}
	public void setPreDirectional(String preDirectional) {
		this.preDirectional = preDirectional;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public String getPostDirectional() {
		return postDirectional;
	}
	public void setPostDirectional(String postDirectional) {
		this.postDirectional = postDirectional;
	}
	public String getSuiteNumber() {
		return suiteNumber;
	}
	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getPhoneExtension() {
		return phoneExtension;
	}
	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCsaNumber() {
		return csaNumber;
	}
	public void setCsaNumber(String csaNumber) {
		this.csaNumber = csaNumber;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getLengthOfContract() {
		return lengthOfContract;
	}
	public void setLengthOfContract(String lengthOfContract) {
		this.lengthOfContract = lengthOfContract;
	}
	public String getContractReviewDate() {
		return contractReviewDate;
	}
	public void setContractReviewDate(String contractReviewDate) {
		this.contractReviewDate = contractReviewDate;
	}
	public String getCpiFlag() {
		return cpiFlag;
	}
	public void setCpiFlag(String cpiFlag) {
		this.cpiFlag = cpiFlag;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getServiceContact() {
		return serviceContact;
	}
	public void setServiceContact(String serviceContact) {
		this.serviceContact = serviceContact;
	}
	public String getServiceContactTitle() {
		return serviceContactTitle;
	}
	public void setServiceContactTitle(String serviceContactTitle) {
		this.serviceContactTitle = serviceContactTitle;
	}
	public String getAuthorizedName() {
		return authorizedName;
	}
	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}
	public String getAuthorizedTitle() {
		return authorizedTitle;
	}
	public void setAuthorizedTitle(String authorizedTitle) {
		this.authorizedTitle = authorizedTitle;
	}
	public String getSalesRepNumber() {
		return salesRepNumber;
	}
	public void setSalesRepNumber(String salesRepNumber) {
		this.salesRepNumber = salesRepNumber;
	}
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}
	public String getNaics() {
		return naics;
	}
	public void setNaics(String naics) {
		this.naics = naics;
	}
	public String getTaxExcempt() {
		return taxExcempt;
	}
	public void setTaxExcempt(String taxExcempt) {
		this.taxExcempt = taxExcempt;
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
	
	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}

	@Override
	public String getKeyName() {
		return "Site Number";
	}
	
	@Override
    public String toString() {
        return "AobSiteInformation{" +
                "companyNumber='" + companyNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", siteNumber='" + siteNumber + '\'' +
                ", siteName='" + siteName + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", preDirectional='" + preDirectional + '\'' +
                ", addressName='" + addressName + '\'' +
                ", streetType='" + streetType + '\'' +
                ", postDirectional='" + postDirectional + '\'' +
                ", suiteNumber='" + suiteNumber + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", phoneExtension='" + phoneExtension + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", startDate='" + startDate + '\'' +
                ", csaNumber='" + csaNumber + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", lengthOfContract='" + lengthOfContract + '\'' +
                ", contractReviewDate='" + contractReviewDate + '\'' +
                ", cpiFlag='" + cpiFlag + '\'' +
                ", poNumber='" + poNumber + '\'' +
                ", serviceContact='" + serviceContact + '\'' +
                ", serviceContactTitle='" + serviceContactTitle + '\'' +
                ", authorizedName='" + authorizedName + '\'' +
                ", authorizedTitle='" + authorizedTitle + '\'' +
                ", salesRepNumber='" + salesRepNumber + '\'' +
                ", territory='" + territory + '\'' +
                ", naics='" + naics + '\'' +
                ", taxExcempt='" + taxExcempt + '\'' +
                ", aobContainers=" + aobContainers +
                ", aobAccountInformation=" + aobAccountInformation +
                ", uploadError='" + uploadError + '\'' +
                '}';
    }
}
	
	
	

	
	
	

	