package com.repsrv.csweb.core.model.account.imports;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.google.gson.annotations.SerializedName;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.imports.validators.resicontract.ValidSheetRowData;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Account #","Site #","Container Grp","Route #","Route Quantity","Firts Svc Date","Stop #"})
@ValidSheetRowData(sheetId = SheetId.ROUTE)
@ExcelSheet("Route Information")
public class RouteInformation extends Row {
	
	private static final String TAB_NAME = "Route Information";
	
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	//@RepEntity(entity = EntityType.COMPANY)
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCell(0)
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Account #")
	//@RepEntity(entity = EntityType.ACCOUNT)
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$", message = "Account number must be 7 numeric characters or less in length")
	@ExcelCell(1)
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site #")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 7 numeric characters or less in length")
	//@RepEntity(entity = EntityType.SITE)
	@ExcelCell(2)
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Grp")
	@Digits(integer = 2, fraction = 0, message = "Container group number must be 5 numeric characters or less in length")
	@NotBlank(message = "Container Group is required")
	//@RepEntity(entity = EntityType.CONTAINER)
	@ExcelCell(3)
	private String containerGroup;
	
	@SerializedName(value="routeNumber")
	@JsonProperty("Route #")
	@Pattern(regexp = "^([1-7][0-9]{0,3})$", message = "Route number must start with 1-7 and connot be larger than 7999")
	@NotBlank(message = "Route Number is required")
	@ExcelCell(4)
	private String routeNumber;

	@SerializedName(value="routeQuantity")
	@JsonProperty("Route Quantity")
	@Pattern(regexp = "^[1-9]([0-9]{0,2})$", message = "Route Quantity must start with 1-9 and connot be larger than 999")
	@Digits(integer = 3, fraction = 0, message = "Route Quantity must be 3 numeric characters or less in length")
	@NotBlank(message = "Route Quanitity is required")
	@ExcelCell(5)
	private String routeQuantity;
	
	@SerializedName(value="weekDelayServiceDate")
	@JsonProperty("Firts Svc Date")
	@Pattern(message = "First Service Date invalid - format must be YYYYMMDD",
		regexp = "^(|([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29))$")
	@ExcelCell(6)
	private String firstServiceDate;
	
	@SerializedName(value="stopSequenceNumber")
	@JsonProperty("Stop #")
	@Pattern(regexp = "^([1-9][0-9]{0,10})$", message = "Stop Sequence Number can only be 11 characters max")
	@NotBlank(message = "Sequence  Number is required")
	@ExcelCell(7)
	private String stopNumber;
	
	@ExcelCell(8)
	@SerializedName(value="ROUTEERROR")
	private String uploadError;
	
	@JsonIgnore
	private ContainerInformation container;

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

	public String getContainerGroup() {
		return containerGroup;
	}

	public void setContainerGroup(String containerGroup) {
		this.containerGroup = containerGroup;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getFirstServiceDate() {
		return StringUtils.isBlank(firstServiceDate) ?  null :  firstServiceDate;
	}

	public void setFirstServiceDate(String firstServiceDate) {
		this.firstServiceDate = StringUtils.isBlank(firstServiceDate) ? null : firstServiceDate;
	}

	public String getStopNumber() {
		return stopNumber;
	}

	public void setStopNumber(String stopNumber) {
		this.stopNumber = stopNumber;
	}

	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}

	public ContainerInformation getContainer() {
		return container;
	}

	public void setContainer(ContainerInformation container) {
		this.container = container;
	}

	@Override
	public String toString() {
		return "Route [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + ", siteNumber="
				+ siteNumber + ", containerGroup=" + containerGroup + ", routeNumber=" + routeNumber
				+ ", routeQuantity="+ routeQuantity + ", firstServiceDate=" + firstServiceDate + ", stopNumber=" + stopNumber + "]";
	}

	@JsonIgnore
	@Override
	public String getKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroup + "-" 
				+ routeNumber;
	}
	
	@JsonIgnore
	@Override
	public String getParentKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroup;
	}
	
	@JsonIgnore
	@Override
	public String getTabName() {
		return TAB_NAME;
	}
	
	@Override
	public String getKeyName() {
		return "Company+Account+Site+Container";
	}
	
}
