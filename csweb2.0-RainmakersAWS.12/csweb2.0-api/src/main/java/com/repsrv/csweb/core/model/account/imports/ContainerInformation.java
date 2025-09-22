package com.repsrv.csweb.core.model.account.imports;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import javax.validation.Valid;
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
import com.repsrv.csweb.core.gson.ExcludeGson;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Account #","Site #","Container Group"
	,"Cont. Start Date","Muni Fran Contract #","Contract Group #","Account Type"
	,"Container Id Code","HOA Assoc Code","Stop Code","Rate Code","District Code"
	,"Recur. Mnths Adv Bill","Recur Chg Freq.","Billed To Date","Next Full Chg Date"
	,"Rev Dist Code","Container Type","Container Size","Customer Owned","Qty On Order"
	,"Total Lifts","Period Length","Container Notes Flag","Note 1","Note 2","Note 3","Note 4","Note 5","Sales Trans Code"
	,"Sales Trans Rsn Code","Competitor Code"})
@ValidSheetRowData(sheetId = SheetId.CONTAINER)
@ExcelSheet("Container Information")
public class ContainerInformation extends Row {
	
	private static final String TAB_NAME = "Container Information";

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
	@JsonProperty("Container Group")
	@Digits(integer = 2, fraction = 0, message = "Container group number must be 5 numeric characters or less in length")
	@NotBlank(message = "Container Group Number is required")
	//@RepEntity(entity = EntityType.CONTAINER)
	@ExcelCell(3)
	private String containerGroupNumber;
	
	@SerializedName(value="startDate")
	@JsonProperty("Cont. Start Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
			message = "Container start date invalid - format must be YYYYMMDD")
	@NotBlank(message = "Container Start Date is required")
	@ExcelCell(4)
	private String containerStartDate;
	
	@SerializedName(value="muniFranContract")
	@JsonProperty("Muni Fran Contract #")
	@Pattern(regexp = "^(?!\\s*$).+", message = "Muni/Fran Contract Number is required")
	@Size(max = 10, message = "Muni/Fran contract number cannot be longer than 10 chars")
	@ExcelCell(5)
	private String muniFranContractNumber;
	
	@SerializedName(value="contractGroupNumber")
	@JsonProperty("Contract Group #")
	@Digits(integer = 2, fraction = 0, message = "Contract group number must be 2 numeric characters or less in length")
	@NotBlank(message = "Contract Group Number is required")
	@ExcelCell(6)
	private String contractGroupNumber;
	
	@SerializedName(value="accountType")
	@JsonProperty("Account Type")
	@Pattern(regexp = "^[PS]$", message = "Account Type must be P or S")
	@NotBlank(message = "Account Type is required")
	@ExcelCell(7)
	private String accountType;
	
	@SerializedName(value="containerIdCode")
	@JsonProperty("Container Id Code")
	@Pattern(regexp = "^[OFH]$", message = "Container Id Code must O, F, H or blank")
	@ExcelCell(8)
	private String containerIdCode;
	
	@SerializedName(value="associationCode")
	@JsonProperty("HOA Assoc Code")
	@Size(min = 0, max = 18)
	@Pattern(regexp = "^[0-9]+$|", message = "HOA Association Code must be 18 numeric characters or less in length")
	@ExcelCell(9)
	private String hoaAssociationCode;
	
	@SerializedName(value="stopCode")
	@JsonProperty("Stop Code")
	@Pattern(regexp = "^[a-zA-Z0-9]$", message = "Stop code can only be one alpha character in length")
	@NotBlank(message = "Stop Code is required")
	@ExcelCell(10)
	private String stopCode;
	
	@SerializedName(value="rateType")
	@JsonProperty("Rate Code")
	@Pattern(regexp = "^[a-zA-Z0-9]$", message = "Rate code can only be one alpha character in length")
	@NotBlank(message = "Rate Code is required")
	@ExcelCell(11)
	private String rateCode;
	
	@SerializedName(value="residentialDistrict")
	@JsonProperty("District Code")
	@Digits(integer = 2, fraction = 0, message = "District Code number must be 2 numeric characters or less in length")
	@NotBlank(message = "District Code is required")
	@ExcelCell(12)
	private String districtCode;
	
	@SerializedName(value="recurMnthsAdvBill")
	@JsonProperty("Recur. Mnths Adv Bill")
	@Digits(integer = 1, fraction = 0, message = "Recurring months adv. bill must be 1 numeric characters")
	@ExcelCell(13)
	private String recurMonthsAdvBill;
	
	@SerializedName(value="recurringChargeFrequency")
	@JsonProperty("Recur Chg Freq.")
	@Digits(integer = 3, fraction = 0, message = "Recurring Charge Freq. must be 3 numeric characters or less in length")
	@ExcelCell(14)
	private String recurChargeFreq;
	
	@SerializedName(value="billedToDate")
	@JsonProperty("Billed To Date")
	@Pattern(regexp = "^(\\d{8}|0{0,8}|\\s*|)$",
			message = "Bill To Date invalid")
	@ExcelCell(15)
	private String billedToDate;
	
	@SerializedName(value="nextRecurChargeDate")
	@JsonProperty("Next Full Chg Date")
	@Pattern(regexp = "^(\\d{8}|0{0,8}|\\s*|)$", 
			message = "Next Full Charge date invalid")
	@ExcelCell(16)
	private String nextFullchargeDate;
	
	@SerializedName(value="revenueDistributionCode")
	@JsonProperty("Rev Dist Code")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,2}$", message = "Rev Dist Code can only be two alpha characters in length or less")
	@NotBlank(message = "Rev Dist Code is required")
	@ExcelCell(17)
	private String revDistCode;
	
	@SerializedName(value="containerType")
	@JsonProperty("Container Type")
	@Pattern(regexp = "^[a-zA-Z]{1,2}$", message = "Container Type can't be blank and can only be two alpha characters in length or less")	@NotBlank(message = "Container Type is required")
	@ExcelCell(18)
	private String containerType;
	
	@SerializedName(value="containerSize")
	@JsonProperty("Container Size")
	@Digits(fraction = 2, integer = 5, message = "Container size must be a decimal 5,2")
	@NotBlank(message = "Container Size is required")
	@ExcelCell(19)
	private String containerSize;
	
	@SerializedName(value="containerOwned")
	@JsonProperty("Customer Owned")
	@Pattern(regexp = "^[YNny]$", message = "Customer Owned must be Y or N")
	@NotBlank(message = "Customer Owned is required")
	@ExcelCell(20)
	private String customerOwned;
	
	@SerializedName(value="containerQtyOrder")
	@JsonProperty("Qty On Order")
	@Digits(fraction = 0, integer = 3, message = "Container Qty On Order cannot be larger than 999 or less than 0")
	@NotBlank(message = "Container Qty On Order is required")
	@ExcelCell(21)
	private String containerQtyOnOrder;
	
	@SerializedName(value="totalLifts")
	@JsonProperty("Total Lifts")
	@Digits(fraction = 0, integer = 3, message = "Total Lifts cannot be larger than 999 or less than 0")
	@NotBlank(message = "Total Lifts is required")
	@ExcelCell(22)
	private String totalLifts;
	
	@SerializedName(value="periodLength")
	@JsonProperty("Period Length")
	@Digits(fraction = 0, integer = 2, message = "Period Length cannot be larger than 99 or less than 0")
	@NotBlank(message = "Period Length is required")
	@ExcelCell(23)
	private String periodLength;
	
	@SerializedName(value="containerNotesFlag")
	@JsonProperty("Container Notes Flag")
	@Pattern(regexp = "^(Y|N|n|y|\\s|)$", message = "Container Notes Flag must be Y, N or empty")
	@ExcelCell(24)
	private String containerNotesFlag;
	
	@SerializedName(value="containerNote1")
	@JsonProperty("Note 1")
	@Size(max = 30, message = "Notes 1 cannot be larger than 30 characters")
	@ExcelCell(25)
	private String note1;
	
	@SerializedName(value="containerNote2")
	@JsonProperty("Note 2")
	@Size(max = 30, message = "Notes 2 cannot be larger than 30 characters")
	@ExcelCell(26)
	private String note2;
	
	@SerializedName(value="containerNote3")
	@JsonProperty("Note 3")
	@Size(max = 30, message = "Notes 3 cannot be larger than 30 characters")
	@ExcelCell(27)
	private String note3;
	
	@SerializedName(value="containerNote4")
	@JsonProperty("Note 4")
	@Size(max = 30, message = "Notes 4 cannot be larger than 30 characters")
	@ExcelCell(28)
	private String note4;
	
	@SerializedName(value="containerNote5")
	@JsonProperty("Note 5")
	@Size(max = 30, message = "Notes 5 cannot be larger than 30 characters")
	@ExcelCell(29)
	private String note5;
	
	@SerializedName(value="transactionCode")
	@JsonProperty("Sales Trans Code")
	@Digits(fraction = 0, integer = 2, message = "Sales Trans Code cannot be larger than 99 or less than 0")
	@NotBlank(message = "Sales Trans Code is required")
	@ExcelCell(30)
	private String salesTransCode;
	
	@SerializedName(value="reasonCode")
	@JsonProperty("Sales Trans Rsn Code")
	@Digits(fraction = 0, integer = 2, message = "Sales Trans Reason Code cannot be larger than 99 or less than 0")
	@NotBlank(message = "Sales Trans Reason Code is required")
	@ExcelCell(31)
	private String salesTransReasonCode;
	
	@SerializedName(value="competitorCode")
	@JsonProperty("Competitor Code")
	@Size(min = 1, max = 3, message = "Competitor code must contain atleast one character and no more than three")
	@Pattern(regexp = "^[a-zA-Z0-9\\/-]{1,3}$", message = "Competitor Code must be alphanumeric, can contain '-' and cannot exceed 3 characters in length")
	@NotBlank(message = "Competitor Code is required")
	@ExcelCell(32)
	private String competitorCode;
	
	@Valid
	@ExcludeGson
	private List<RateInformation> rates;
	
	@Valid
	@ExcludeGson
	private List<RouteInformation> routes;
	
	@ExcelCell(33)
	@SerializedName(value="CNTRERROR")
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

	public String getContainerGroupNumber() {
		return StringUtils.stripStart(containerGroupNumber,"0");
	}

	public void setContainerGroupNumber(String containerGroupNumber) {
		this.containerGroupNumber = containerGroupNumber;
	}

	public String getContainerStartDate() {
		return containerStartDate;
	}

	public void setContainerStartDate(String containerStartDate) {
		this.containerStartDate = containerStartDate;
	}

	public String getMuniFranContractNumber() {
		return muniFranContractNumber;
	}

	public void setMuniFranContractNumber(String muniFranContractNumber) {
		this.muniFranContractNumber = muniFranContractNumber;
	}

	public String getContractGroupNumber() {
		return contractGroupNumber;
	}

	public void setContractGroupNumber(String contractGroupNumber) {
		this.contractGroupNumber = contractGroupNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getContainerIdCode() {
		return containerIdCode;
	}

	public void setContainerIdCode(String containerIdCode) {
		this.containerIdCode = containerIdCode;
	}

	public String getHoaAssociationCode() {
		return hoaAssociationCode;
	}

	public void setHoaAssociationCode(String hoaAssociationCode) {
		this.hoaAssociationCode = hoaAssociationCode;
	}

	public String getStopCode() {
		return stopCode;
	}

	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getRecurMonthsAdvBill() {
		return recurMonthsAdvBill;
	}

	public void setRecurMonthsAdvBill(String recurMonthsAdvBill) {
		this.recurMonthsAdvBill = recurMonthsAdvBill;
	}

	public String getRecurChargeFreq() {
		return recurChargeFreq;
	}

	public void setRecurChargeFreq(String recurChargeFreq) {
		this.recurChargeFreq = recurChargeFreq;
	}

	public String getBilledToDate() {
		return billedToDate;
	}

	public void setBilledToDate(String billedToDate) {
		this.billedToDate = billedToDate;
	}

	public String getNextFullchargeDate() {
		return nextFullchargeDate;
	}

	public void setNextFullchargeDate(String nextFullchargeDate) {
		this.nextFullchargeDate = nextFullchargeDate;
	}

	public String getRevDistCode() {
		return revDistCode;
	}

	public void setRevDistCode(String revDistCode) {
		this.revDistCode = revDistCode;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public String getCustomerOwned() {
		return customerOwned;
	}

	public void setCustomerOwned(String customerOwned) {
		this.customerOwned = customerOwned;
	}

	public String getContainerQtyOnOrder() {
		return containerQtyOnOrder;
	}

	public void setContainerQtyOnOrder(String containerQtyOnOrder) {
		this.containerQtyOnOrder = containerQtyOnOrder;
	}

	public String getTotalLifts() {
		return totalLifts;
	}

	public void setTotalLifts(String totalLifts) {
		this.totalLifts = totalLifts;
	}

	public String getPeriodLength() {
		return periodLength;
	}

	public void setPeriodLength(String periodLength) {
		this.periodLength = periodLength;
	}

	public String getContainerNotesFlag() {
		return containerNotesFlag;
	}

	public void setContainerNotesFlag(String containerNotesFlag) {
		this.containerNotesFlag = containerNotesFlag;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}

	public String getSalesTransCode() {
		return salesTransCode;
	}

	public void setSalesTransCode(String salesTransCode) {
		this.salesTransCode = salesTransCode;
	}

	public String getSalesTransReasonCode() {
		return salesTransReasonCode;
	}

	public void setSalesTransReasonCode(String salesTransReasonCode) {
		this.salesTransReasonCode = salesTransReasonCode;
	}

	public String getCompetitorCode() {
		return competitorCode;
	}

	public void setCompetitorCode(String competitorCode) {
		this.competitorCode = competitorCode;
	}
	
	@JsonIgnore
	public String getParentKey() {
		return this.companyNumber + "-" + this.accountNumber + "-" + this.siteNumber;
	}
	
	public List<RouteInformation> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteInformation> routes) {
		this.routes = routes;
	}

	@JsonIgnore
	public boolean hasNotes() {
		return 
				isNotBlank(note1)
				||
				isNotBlank(note2)
				||
				isNotBlank(note3)
				||
				isNotBlank(note4)
				||
				isNotBlank(note5);
	}
	
	@JsonIgnore
	@Override
	public String getKey() {
		return this.companyNumber 
				+ "-" + this.accountNumber 
				+ "-" + this.siteNumber 
				+ "-" + this.containerGroupNumber;
	}

	public List<RateInformation> getRates() {
		return rates;
	}

	public void setRates(List<RateInformation> rates) {
		this.rates = rates;
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
		return "Container Group";
	}

	@Override
	public String toString() {
		return "ContainerInformation [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber
				+ ", siteNumber=" + siteNumber + ", containerGroupNumber=" + containerGroupNumber
				+ ", containerStartDate=" + containerStartDate + ", muniFranContractNumber=" + muniFranContractNumber
				+ ", contractGroupNumber=" + contractGroupNumber + ", accountType=" + accountType + ", containerIdCode="
				+ containerIdCode + ", hoaAssociationCode=" + hoaAssociationCode + ", stopCode=" + stopCode
				+ ", rateCode=" + rateCode + ", districtCode=" + districtCode + ", recurMonthsAdvBill="
				+ recurMonthsAdvBill + ", recurChargeFreq=" + recurChargeFreq + ", billedToDate=" + billedToDate
				+ ", nextFullchargeDate=" + nextFullchargeDate + ", revDistCode=" + revDistCode + ", containerType="
				+ containerType + ", containerSize=" + containerSize + ", customerOwned=" + customerOwned
				+ ", containerQtyOnOrder=" + containerQtyOnOrder + ", totalLifts=" + totalLifts + ", periodLength="
				+ periodLength + ", containerNotesFlag=" + containerNotesFlag + ", note1=" + note1 + ", note2="
				+ note2 + ", note3=" + note3 + ", note4=" + note4 + ", note5=" + note5 + ", salesTransCode="
				+ salesTransCode + ", salesTransReasonCode=" + salesTransReasonCode + ", competitorCode="
				+ competitorCode + "]";
	}
	
	
}
