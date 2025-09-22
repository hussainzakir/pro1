package com.repsrv.csweb.core.model.account.imports;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.imports.validators.RepEntity;
import com.repsrv.csweb.core.account.imports.validators.resicontract.ValidSheetRowData;
import com.repsrv.csweb.core.gson.ExcludeGson;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Account #","Site #","Site Name","Address #"
	,"Pre Directional","Address Name","Street Type","Post Directional","Suite #"
	,"Add Line 2","City","State","Postal Code","Area Code","Contact Phone","Phone Ext."
	,"Fax #","Service Contact","Authorized Name","Territory","Sales Rep #","CSA #"
	,"Start Date","Close Date","SIC Code","Sic Code Sec 1","Tax Exempt"})
@ValidSheetRowData(sheetId = SheetId.SITE)
@ExcelSheet("Site Information")
@Valid
public class SiteInformation extends Row{
	
	private static final String TAB_NAME = "Site Information";
	
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCell(0)
	//@RepEntity(entity = EntityType.COMPANY)
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Account #")
	@Pattern(regexp = "^\\d{0,7}$", message = "Account number must be 7 numeric characters or less in length")
	@ExcelCell(1)
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site #")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 7 numeric characters or less in length")
	@ExcelCell(2)
	private String siteNumber;
	
	@SerializedName(value="siteName")
	@JsonProperty("Site Name")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s:#\\&]+$", message = "Site name can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 30, message = "Site name cannot be longer than 30 chars")
	@ExcelCell(3)
	private String siteName;
	
	@SerializedName(value="addressNumber")
	@JsonProperty("Address #")
	@Pattern(regexp = "^[a-zA-Z0-9-\\/\\s#:]+$", message = "Address Number can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 15, message = "Address number cannot be longer than 15 chars")
	@NotBlank(message = "Address Number is required")
	@ExcelCell(4)
	private String addressNumber;
	
	@SerializedName(value="preDirectional")
	@JsonProperty("Pre Directional")
	@Size(max = 2, message = "Pre Directional cannot be longer than 2 chars")
	@ExcelCell(5)
	private String preDirectional;
	
	@SerializedName(value="addressName")
	@JsonProperty("Address Name")
	@Pattern(regexp = "^[a-zA-Z0-9-\\/\\s]+$", message = "Address name can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 30, message = "Address name cannot be longer than 30 chars")
	@NotBlank(message = "Address Name is required")
	@ExcelCell(6)
	private String addressName;
	
	@SerializedName(value="streetType")
	@JsonProperty("Street Type")
	@Pattern(regexp = "^((\\w+([\\s-_]\\w+)*+)|\\s|)$", message = "Street type can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 4, message = "Street type cannot be longer than 4 chars")
	@ExcelCell(7)
	private String streetType;
	
	@SerializedName(value="streetDirection")
	@JsonProperty("Post Directional")
	@Size(max = 3, message = "Post Directional cannot be longer than 3 chars")
	@ExcelCell(8)
	private String postDirectional;
	
	@SerializedName(value="suite")
	@JsonProperty("Suite #")
	@Pattern(regexp = "^([a-zA-Z0-9-#\\s]+)|\\s|$", message = "Suite Number can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 5, message = "Suite Number cannot be longer than 5 chars")
	@ExcelCell(9)
	private String suiteNumber;
	
	@SerializedName(value="addressLine2")
	@JsonProperty("Add Line 2")
	@Pattern(regexp = "^([a-zA-Z0-9-]+)|\\s|$", message = "Address Line 2 can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 30, message = "Address Line 2 cannot be longer than 30 chars")
	@ExcelCell(10)
	private String addressLine2;
	
	@SerializedName(value="city")
	@JsonProperty("City")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "City can only consist of alpha numeric, space, hyphen, colon and pound characters")
	@Size(max = 20, message = "City cannot be longer than 20 chars")
	@ExcelCell(11)
	private String city;
	
	@SerializedName(value="state")
	@JsonProperty("State")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "State can only consist of alpha characters")
	@Size(max = 3, message = "State cannot be longer than 3 chars")
	@ExcelCell(12)
	private String state;
	
	@SerializedName(value="zipCode")
	@JsonProperty("Postal Code")
	@Pattern(regexp = "^\\d{5}([\\-]?\\d{4})?$", message = "Postal code format invalid")
	@Size(max = 10, min = 5, message = "Postal code cannot be shorter than 5 or more than 10 characters")
	@ExcelCell(13)
	private String postalCode;
	
	@SerializedName(value="areaCode")
	@JsonProperty("Area Code")
	@Pattern(regexp = "^\\d{3}?$", message = "Area code can only be 3 numeric characters")
	@ExcelCell(14)
	private String areaCode;
	
	@SerializedName(value="contactPhone")
	@JsonProperty("Contact Phone")
	@Pattern(regexp = "^\\d{7}?$", message = "Contact phone format invalid")
	@Size(max = 7, min = 7, message = "Contact phone must be seven numeric characters")
	@ExcelCell(15)
	private String contactPhone;
	
	@SerializedName(value="phoneExtension")
	@JsonProperty("Phone Ext.")
	@Pattern(regexp = "^((\\d{3})|\\s|)$", message = "Phone extension can only be 3 numeric characters")
	@ExcelCell(16)
	private String phoneExtension;
	
	@SerializedName(value="siteFaxNumber")
	@JsonProperty("Fax #")
	@Pattern(regexp = "^((\\d{10})|\\s|)$", message = "Fax number can only be 10 numeric characters")
	@ExcelCell(17)
	private String faxNumber;
	
	@SerializedName(value="serviceContract")
	@JsonProperty("Service Contract")
	@Pattern(regexp = "^[a-zA-Z0-9.\\s]+$", message = "Service Contact can only consist of alpha numeric, space, hyphen and period characters")
	@Size(max = 25, message = "Service Contact cannot be longer than 25 chars")
	@ExcelCell(18)
	private String serviceContact;
	
	@SerializedName(value="authorizedName")
	@JsonProperty("Authorized Name")
	@Pattern(regexp = "^[a-zA-Z0-9.-]{0,25}$", message = "Authorized Name can only consist of alpha numeric, space, hyphen and period characters")
	@Size(max = 25, message = "Authorized Name cannot be longer than 25 chars")
	@ExcelCell(19)
	private String authorizedName;
	
	@SerializedName(value="territory")
	@JsonProperty("Territory")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Territory can only consist of alpha numeric characters")
	@Size(max = 3, message = "Territory cannot be longer than 3 chars")
	@ExcelCell(20)
	private String territory;
	
	@SerializedName(value="salesRepNumber")
	@JsonProperty("Sales Rep #")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Sales Rep cannot be blank, can only be alphanumeric and no more than 10 characters in length")
	@Size(max = 10, message = "Sales Rep cannot be longer than 10 chars")
	@ExcelCell(21)
	private String salesRepNumber;
	
	@SerializedName(value="siteCSA")
	@JsonProperty("CSA #")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "CSA Number can only consist of alpha numeric characters")
	@Size(max = 10, message = "CSA Number cannot be longer than 10 chars")
	@ExcelCell(22)
	private String csaNumber;
	
	@SerializedName(value="contractStatus")
	@JsonProperty("Start Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", message = "Start date invalid")
	@NotBlank(message = "Start Date is required")
	@ExcelCell(23)
	private String startDate;
	
	@SerializedName(value="closeDate")
	@JsonProperty("Close Date")
	@Pattern(regexp = "^((\\d{8})|\\s|)$", 
			message = "Close date invalid")
	@ExcelCell(24)
	private String closeDate;
	
	@SerializedName(value="siteCode")
	@JsonProperty("SIC Code")
	@Pattern(regexp = "^\\d{4}?$", message = "Sic Code can only be 4 numeric characters")
	@NotBlank(message = "Site Code is required")
	@ExcelCell(25)
	private String sicCode;
	
	@SerializedName(value="siteCodeSec1")
	@JsonProperty("Sic Code Sec 1")
	@Pattern(regexp = "^\\d{2}?$", message = "Sic Code Sec-1 can only be 4 numeric characters")
	@NotBlank(message = "Sic Code Sec1 is required")
	@ExcelCell(26)
	private String sicCodeSec1;
	
	@SerializedName(value="taxExemptionNumber")
	@JsonProperty("Tax Exempt")
	@Pattern(regexp = "^(?:Y|\\s*|)$", message = "Tax Exempt can only be blank or 'Y'")
	@ExcelCell(27)
	private String taxExcempt;

	@SerializedName(value="longitude")
	@JsonProperty("Longitude")
	@NotBlank(message = "Longitud is required")
	@Pattern(regexp = "^[-.0-9]{1,11}$", message = "Longitude is required, must be 11 numeric characters or less in length and no spaces")
	@Digits(integer = 3, fraction = 9, message = "Longitude must have this format: '23.343112'")
	@ExcelCell(28)
	private String longitude;
	
	@SerializedName(value="latitude")
	@JsonProperty("Latitude")
	@NotBlank(message = "Latitude is required")
	@Pattern(regexp = "^[-.0-9]{1,11}$", message = "Latitude is required, must be 11 numeric characters or less in length and no spaces")
	@Digits(integer = 3, fraction = 9, message = "Latitude must have this format: '23.343112'")
	@ExcelCell(29)
	private String latitude;
	
	@SerializedName(value="grid")
	@JsonProperty("Grid")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,10}$", message = "Grid is required and can only be 10 alphanumeric characters max")
	@NotBlank(message = "Grid is required")
	@ExcelCell(30)
	private String grid;

	@ExcludeGson
	@Valid
	private List<ContainerInformation> containers;
	
	@ExcelCell(31)
	@SerializedName(value="SITEERROR")
	private String uploadError = "";

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

	public String getSiteNumber() {
		return StringUtils.stripStart(siteNumber,"0");
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

	public String getContaxtPhone() {
		return contactPhone;
	}

	public void setContaxtPhone(String contaxtPhone) {
		this.contactPhone = contaxtPhone;
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

	public String getServiceContact() {
		return serviceContact;
	}

	public void setServiceContact(String serviceContact) {
		this.serviceContact = serviceContact;
	}

	public String getAuthorizedName() {
		return authorizedName;
	}

	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getSalesRepNumber() {
		return salesRepNumber;
	}

	public void setSalesRepNumber(String salesRepNumber) {
		this.salesRepNumber = salesRepNumber;
	}

	public String getCsaNumber() {
		return csaNumber;
	}

	public void setCsaNumber(String csaNumber) {
		this.csaNumber = csaNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getSicCodeSec1() {
		return sicCodeSec1;
	}

	public void setSicCodeSec1(String sicCodeSec1) {
		this.sicCodeSec1 = sicCodeSec1;
	}

	public String getTaxExcempt() {
		return taxExcempt;
	}

	public void setTaxExcempt(String taxExcempt) {
		this.taxExcempt = taxExcempt;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public List<ContainerInformation> getContainers() {
		return containers == null ? Collections.emptyList() : containers;
	}

	public void setContainers(List<ContainerInformation> containers) {
		this.containers = containers;
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
		return "SiteInformation [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + ", siteNumber="
				+ siteNumber + ", siteName=" + siteName + ", addressNumber=" + addressNumber + ", preDirectional="
				+ preDirectional + ", addressName=" + addressName + ", streetType=" + streetType + ", postDirectional="
				+ postDirectional + ", suiteNumber=" + suiteNumber + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", postalCode=" + postalCode + ", areaCode=" + areaCode + ", contaxtPhone="
				+ contactPhone + ", phoneExtension=" + phoneExtension + ", faxNumber=" + faxNumber + ", serviceContact="
				+ serviceContact + ", authorizedName=" + authorizedName + ", territory=" + territory
				+ ", salesRepNumber=" + salesRepNumber + ", csaNumber=" + csaNumber + ", startDate=" + startDate
				+ ", closeDate=" + closeDate + ", sicCode=" + sicCode + ", sicCodeSec1=" + sicCodeSec1 + ", taxExcempt="
				+ taxExcempt + "]";
	}
	
	
}
