package com.repsrv.csweb.core.model.account.onboarding;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.Collections;
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
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import com.repsrv.csweb.core.account.onboarding.validators.AobValidSheetRowData;
import com.repsrv.csweb.core.gson.ExcludeGson;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Cust. Account", "Site", "Container Group", "Start Date", "Muni/Fran Contract #", "Contract Group #", "Account Type",
 "Container Id Code", "Association Code", "Recurring Months Adv Bill","Recur Charge Freq", "Rev Distribution Code", "Container Type",
  "Container Size", "Compactor Flag", "Container Owned", "Container Qty Order", "On Call Flag", "Min # Of Lifts", "Total Lifts", "Period Length", 
  "Monday Lift Day","Tuesday Lift Day", "Wednesday Lift Day", "Thursday Lift Day", "Friday Lift Day", "Saturday Lift Day", "Sunday Lift Day",
   "Receipt Request Flag", "PO Required","Special Handling", "Disposal Code", "Disposal Price Code", "Unit Of Measure", "Weight Limit", 
   "Remote Monitor Flag", "City Account #", "Container Notes Flag", "Note 1","Note 2","Note 3","Note 4","Note 5", 
   "Transaction Code", "Reason Code", "Competitor Code", "Week Delay Start Date", "Create Delivery UR", "Delivery PO #", "UR Effective Date",
   "UR Plan Date", "Delivery Note", "Schedule Perm Service", "Schedule Perm Effective Date", "Schedule Perm Plan Date"})
@AobValidSheetRowData(sheetId = SheetId.CONTAINER)
@ExcelSheet("Container Information")

public class AobContainerInformation extends Row {
	
	private static final String TAB_NAME = "Container Information";

	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("ACCOMP")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$", message = "Account number must be 7 numeric characters or less in length")
	@ExcelCellName("ACACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 7 numeric characters or less in length")
	@ExcelCellName("ACSITE")
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Group")
	@Digits(integer = 2, fraction = 0, message = "Container group number must be 5 numeric characters or less in length")
	@NotBlank(message = "Container Group Number is required")
	@ExcelCellName("ACCTGR")
	private String containerGroupNumber;
	
	@SerializedName(value="startDate")
	@JsonProperty("Start Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)$", 
			message = "Container start date invalid - format must be YYYYMMDD")
	@NotBlank(message = "Container Start Date is required")
	@ExcelCellName("ACSTDT")
	private String containerStartDate;
	
	@SerializedName(value="muniFranContract")
	@JsonProperty("Muni/Fran Contract #")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Muni/Fran contract number can only consist of alpha numeric characters")
	@Size(max = 10, message = "Muni/Fran contract number cannot be longer than 10 chars")
	@ExcelCellName("ACMFCT")
	private String muniFranContractNumber;
	
	@SerializedName(value="contractGroupNumber")
	@JsonProperty("Contract Group #")
	@Size(max = 2, message = "Contract group number must be 2 numeric characters or less in length")
	@ExcelCellName("ACCGRP")
	private String contractGroupNumber;
	
	@SerializedName(value="accountType")
	@JsonProperty("Account Type")
	@Pattern(regexp = "^[PpSsTtCcEe]$", message = "Account Type must be P,S,T,C or E")
	@NotBlank(message = "Account Type is required")
	@ExcelCellName("ACTYPE")
	private String accountType;
	
	@SerializedName(value="containerIdCode")
	@JsonProperty("Container Id Code")
	@Pattern(regexp = "^[OFH]$", message = "Container Id Code must O, F, H or blank")
	@ExcelCellName("ACFG04")
	private String containerIdCode;
	
	@SerializedName(value="associationCode")
	@JsonProperty("Association Code")
	@Size(min = 0, max = 18)
	@Pattern(regexp = "^[0-9]+$|", message = "HOA Association Code must be 18 numeric characters or less in length")
	@ExcelCellName("ASCASCD")
	private String hoaAssociationCode;

	@SerializedName(value="recurMnthsAdvBill")
	@JsonProperty("Recurring Months Adv Bill")
	@Pattern(regexp = "^\\d{0,1}$", message = "Recurring months adv. bill must be 1 numeric characters")
	@ExcelCellName("ACAVRC")
	private String recurMonthsAdvBill;

	@SerializedName(value="recurChargeFrequency")
	@JsonProperty("Recur Charge Freq")
	@ExcelCellName("ACRCFQ")
	private String recurChargeFrequency;
	
	@SerializedName(value="revenueDistributionCode")
	@JsonProperty("Rev Distribution Code")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,2}$", message = "Rev Dist Code can only be two characters in length or less")
	@NotBlank(message = "Rev Dist Code is required")
	@ExcelCellName("ACREVD")
	private String revDistCode;
	
	@SerializedName(value="containerType")
	@JsonProperty("Container Type")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,2}$", message = "Container Type can only be two characters in length or less")
	@NotBlank(message = "Container Type is required")
	@ExcelCellName("ACCTTP")
	private String containerType;
	
	@SerializedName(value="containerSize")
	@JsonProperty("Container Size")
	@Digits(fraction = 2, integer = 5, message = "Container size must be a decimal 5,2")
	@NotBlank(message = "Container Size is required")
	@ExcelCellName("ACCTSZ")
	private String containerSize;

	@SerializedName(value="compactorFlag")
	@JsonProperty("Compactor Flag")
	@Pattern(regexp = "^[Yy]?$", message = "Compactor Flag must be Y")
	@ExcelCellName("ACCPAC")
	private String compactor;
	
	@SerializedName(value="containerOwned")
	@JsonProperty("Container Owned")
	@Pattern(regexp = "^[YNny]$", message = "Customer Owned must be Y or N")
	@NotBlank(message = "Customer Owned is required")
	@ExcelCellName("ACCOWN")
	private String customerOwned;
	
	@SerializedName(value="containerQtyOrder")
	@JsonProperty("Container Qty Order")
	@Digits(fraction = 0, integer = 3, message = "Container Qty On Order cannot be larger than 999 or less than 0")
	@NotBlank(message = "Container Qty On Order is required")
	@ExcelCellName("ACQTYC")
	private String containerQtyOnOrder;

	@SerializedName(value="onCallFlag")
	@JsonProperty("On Call Flag")
	@Pattern(regexp = "^[YNny]$", message = "On Call Flag must be Y or N")
	@NotBlank(message = "On Call Flag is required")
	@ExcelCellName("ACOCAL")
	private String onCallFlag;
	
	@SerializedName(value="minNumberOfLifts")
	@JsonProperty("Min # Of Lifts")
	@Pattern(regexp = "^[0-9]{1,3}$", message = "Minimum Number Of Lifts cannot be empty, larger than 999 or less than 0")
	@ExcelCellName("ACMINL")
	private String minimumNumberLifts;
	
	@SerializedName(value="totalLifts")
	@JsonProperty("Total Lifts")
	@Pattern(regexp = "^[0-9]{1,3}$", message = "Total Number Of Lifts must be numeric value no more that 999 and no less that 0")
	@ExcelCellName("ACTOTL")
	private String totalLifts;
	
	@SerializedName(value="periodLength")
	@JsonProperty("Period Length")
	@Pattern(regexp = "^\\d{0,3}$", message = "Period Length must be numeric, cannot be larger than 999 or less than 0")
	@ExcelCellName("ACPLTH")
	private String periodLength;
	
	@SerializedName(value="mondayLiftDay")
	@JsonProperty("Monday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Monday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACMOND")
	private String mondayNumberOfLifts;
	
	@SerializedName(value="tuesdayLiftDay")
	@JsonProperty("Tuesday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Tuesday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACTUES")
	private String tuesdayNumberOfLifts;
	
	@SerializedName(value="wednesdayLiftDay")
	@JsonProperty("Wednesday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Wednesday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACWEDN")
	private String wednesdayNumberOfLifts;
	
	@SerializedName(value="thursdayLiftDay")
	@JsonProperty("Thursday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Thursday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACTHUR")
	private String thursdayNumberOfLifts;
	
	@SerializedName(value="fridayLiftDay")
	@JsonProperty("Friday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Friday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACFRID")
	private String fridayNumberOfLifts;
	
	@SerializedName(value="saturdayLiftDay")
	@JsonProperty("Saturday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Saturday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACSATU")
	private String saturdayNumberOfLifts;
	
	@SerializedName(value="sundayLiftDay")
	@JsonProperty("Sunday Lift Day")
	@Pattern(regexp = "^\\d{0,3}$", message = "Sunday Number Of Lifts cannot be larger than 999 or less than 0")
	@ExcelCellName("ACSUND")
	private String sundayNumberOfLifts;
	
	@SerializedName(value="receiptRequired")
	@JsonProperty("Receipt Request Flag")
	@Pattern(regexp = "^[YNny]$", message = "Receipt Request Flag must be Y or N")
	@NotBlank(message = "Receipt Request Flag is required")
	@ExcelCellName("ACRCPT")
	private String receiptRequestFlag;
	
	@SerializedName(value="purchaseOrderRequired")
	@JsonProperty("PO Required")
	@Pattern(regexp = "^[YNny]$", message = "PO Required must be Y or N")
	@NotBlank(message = "PO Required is required")
	@ExcelCellName("ACPOFG")
	private String poRequired;
	
	@SerializedName(value="specialHandling")
	@JsonProperty("Special Handling")
	@Pattern(regexp = "^[a-zA-Z0-9&%@#*!¢]+$", message = "Special Handling can only consist of alpha numeric characters")
	@Size(max = 1, message = "Special Handling cannot be longer than 1 char")
	@NotBlank(message = "Special Handling is required")
	@ExcelCellName("ACSHFL")
	private String specialHandling;
	
	@SerializedName(value="disposalCode")
	@JsonProperty("Disposal Code")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Disposal Code can only consist of alpha numeric characters")
	@Size(max = 2, message = "Disposal Code cannot be longer than 2 alpha numeric characters")
	@NotBlank(message = "Disposal Code is required")
	@ExcelCellName("ACLFCD")
	private String disposalCode;
	
	@SerializedName(value="disposalPriceCode")
	@JsonProperty("Disposal Price Code")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Disposal Price Code can only consist of alpha numeric characters")
	@Size(max = 2, message = "Disposal Price Code cannot be longer than 2 alpha numeric characters")
	@NotBlank(message = "Disposal Price Code is required")
	@ExcelCellName("ACLFPC")
	private String disposalPriceCode;
	
	@SerializedName(value="unitOfMeasure")
	@JsonProperty("Unit Of Measure")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Unit Of Measure can only consist of alpha numeric characters")
	@Size(max = 2, message = "Unit Of Measure cannot be longer than 2 alpha numeric characters")
	@ExcelCellName("ACOLDN")
	private String unitOfMeasure;
	
	@SerializedName(value="weightLimit")
	@JsonProperty("Weight Limit")
	// @Digits(fraction = 2, integer = 5, message = "Weight Limit must be a decimal 5,2")
	// @NotBlank(message = "Weight Limit is required")
	@ExcelCellName("ACAVGW")
	private String weightLimit;
	
	@SerializedName(value="remoteMonitorFlag")
	@JsonProperty("Remote Monitor Flag")
	@Pattern(regexp = "^[YNny]$", message = "Remote Monitor Flag must be Y or N")
	@NotBlank(message = "Remote Monitor Flag is required")
	@ExcelCellName("ACFG03")
	private String remoteMonitorFlag;
	
	@SerializedName(value="cityAccountNumber")
	@JsonProperty("City Account #")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "City Account can only consist of alpha numeric characters")
	@Size(max = 20, message = "City Account cannot be longer than 20 alpha numeric characters")
	@ExcelCellName("ACCITY")
	private String cityAccount;

	@SerializedName(value="containerNotesFlag")
	@JsonProperty("Container Notes Flag")
	@Pattern(regexp = "^Y|N|n|y|\\s|$", message = "Container Notes Flag must be Y, N or empty")
	@ExcelCellName("ACCNFL")
	private String containerNotesFlag;
	
	@SerializedName(value="containerNote1")
	@JsonProperty("Note 1")
	@Size(max = 30, message = "Notes 1 cannot be larger than 30 characters")
	@ExcelCellName("CNCNT1")
	private String note1;
	
	@SerializedName(value="containerNote2")
	@JsonProperty("Note 2")
	@Size(max = 30, message = "Notes 2 cannot be larger than 30 characters")
	@ExcelCellName("CNCNT2")
	private String note2;
	
	@SerializedName(value="containerNote3")
	@JsonProperty("Note 3")
	@Size(max = 30, message = "Notes 3 cannot be larger than 30 characters")
	@ExcelCellName("CNCNT3")
	private String note3;
	
	@SerializedName(value="containerNote4")
	@JsonProperty("Note 4")
	@Size(max = 30, message = "Notes 4 cannot be larger than 30 characters")
	@ExcelCellName("CNCNT4")
	private String note4;
	
	@SerializedName(value="containerNote5")
	@JsonProperty("Note 5")
	@Size(max = 30, message = "Notes 5 cannot be larger than 30 characters")
	@ExcelCellName("CNCNT5")
	private String note5;
	
	@SerializedName(value="transactionCode")
	@JsonProperty("Transaction Code")
	@Digits(fraction = 0, integer = 2, message = "Sales Trans Code cannot be larger than 99 or less than 0")
	@NotBlank(message = "Sales Trans Code is required")
	@ExcelCellName("SDTRCD")
	private String transactionCode;
	
	@SerializedName(value="reasonCode")
	@JsonProperty("Reason Code")
	@Digits(fraction = 0, integer = 2, message = "Sales Trans Reason Code cannot be larger than 99 or less than 0")
	@NotBlank(message = "Sales Trans Reason Code is required")
	@ExcelCellName("SDRECD")
	private String reasonCode;
	
	@SerializedName(value="competitorCode")
	@JsonProperty("Competitor Code")
	@Size(min = 1, max = 3, message = "Competitor code must contain atleast one character and no more than three")
	@Pattern(regexp = "^[a-zA-Z0-9\\/-]{1,3}$", message = "Competitor Code must be alphanumeric, can contain '-' and cannot exceed 3 characters in length")
	@NotBlank(message = "Competitor Code is required")
	@ExcelCellName("SDCPCD")
	private String competitorCode;
	
	@SerializedName(value="weekDelayStartDate")
	@JsonProperty("Week Delay Start Date")
	@Pattern(message = "First Service Date invalid - format must be YYYYMMDD",
		regexp = "^(|([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29))*$")
	@ExcelCellName("DYDT01")
	private String firstServiceDate;

	@SerializedName(value="createDeliveryUr")
	@JsonProperty("Create Delivery UR")
	@Pattern(regexp = "^[YNny]$", message = "Create Delete UR must be Y or N")
	@NotBlank(message = "Create Delete UR is required")
	@ExcelCellName("URTRTY1")
	private String createDeliveryUR;
	
	@SerializedName(value="deliveryPoNumber")
	@JsonProperty("Delivery PO #")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Delivery PO Number can only contain letters and numbers")
	@Size(max = 15, message = "Delivery PO Number max length is 15")
	@ExcelCellName("UOPONO")
	private String deliveryPONumber;
	
	@SerializedName(value="urEffectiveDate")
	@JsonProperty("UR Effective Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)*$", 
			message = "UR Effective date invalid - format must be YYYYMMDD")
	@ExcelCellName("UREDAT1")
	private String urEffectiveDate;
	
	@SerializedName(value="urPlanDate")
	@JsonProperty("UR Plan Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)*$", 
			message = "UR Plan date invalid - format must be YYYYMMDD")
	@ExcelCellName("URPDAT1")
	private String urPlanDate;
	
	@SerializedName(value="deliveryNote")
	@JsonProperty("Delivery Note")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Delivery Note 1 can only contain letters and numbers")
	@Size(max = 60, message = "Delivery Note max length is 60")
	@ExcelCellName("UNNOTE1")
	private String deliveryNote1;
	
	@SerializedName(value="schedulePermService")
	@JsonProperty("Schedule Perm Service")
	@Pattern(regexp = "^[YNny]$", message = "Schedule Perm Service must be Y or N")
	@NotBlank(message = "Schedule Perm Service is required")
	@ExcelCellName("SCHEDU")
	private String schedulePermService;
	
	@SerializedName(value="schedulePermEffectiveDate")
	@JsonProperty("Schedule Perm Effective Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)*$", 
			message = "Schedule Perm Service date invalid - format must be YYYYMMDD")
	@ExcelCellName("UREDAT")
	private String schedulePermServiceDate;
	
	@SerializedName(value="schedulePermPlanDate")
	@JsonProperty("Schedule Perm Plan Date")
	@Pattern(regexp = "^([0-9]{4}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048])00)[-]?02[-]?29)*$", 
			message = "Schedule Perm Service Plan date invalid - format must be YYYYMMDD")
	@ExcelCellName("URPDAT")
	private String schedulePermServicePlanDate;
	
	@Valid
	@ExcludeGson
	private List<AobRateInformation> aobRates;

	public List<AobRateInformation> getAobRates() {
		return aobRates == null ? Collections.emptyList() : aobRates;
	}
	
	public void setAobRates(List<AobRateInformation> aobRates) {
		this.aobRates = aobRates;
	}

	@JsonIgnore
	private AobSiteInformation aobSiteInformation;

	public AobSiteInformation getAobSiteInformation() {
		return aobSiteInformation;
	}

	public void setAobSiteInformation(AobSiteInformation aobSiteInformation) {
		this.aobSiteInformation = aobSiteInformation;
	}
	
	@JsonIgnore
	private AobAccountInformation aobAccountInformation;

	public AobAccountInformation getAobAccountInformation() {
		return aobAccountInformation;
	}

	public void setAobAccountInformation(AobAccountInformation aobAccountInformation) {
		this.aobAccountInformation = aobAccountInformation;
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

	public String getContainerGroupNumber() {
		return containerGroupNumber;
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

	public String getRecurMonthsAdvBill() {
		return recurMonthsAdvBill;
	}

	public void setRecurMonthsAdvBill(String recurMonthsAdvBill) {
		this.recurMonthsAdvBill = recurMonthsAdvBill;
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

	public String getCompactor() {
		return compactor;
	}

	public void setCompactor(String compactor) {
		this.compactor = compactor;
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

	public String getOnCallFlag() {
		return onCallFlag;
	}

	public void setOnCallFlag(String onCallFlag) {
		this.onCallFlag = onCallFlag;
	}

	public String getMinimumNumberLifts() {
		return minimumNumberLifts;
	}

	public void setMinimumNumberLifts(String minimumNumberLifts) {
		this.minimumNumberLifts = minimumNumberLifts;
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

	public String getMondayNumberOfLifts() {
		return mondayNumberOfLifts;
	}

	public void setMondayNumberOfLifts(String mondayNumberOfLifts) {
		this.mondayNumberOfLifts = mondayNumberOfLifts;
	}

	public String getTuesdayNumberOfLifts() {
		return tuesdayNumberOfLifts;
	}

	public void setTuesdayNumberOfLifts(String tuesdayNumberOfLifts) {
		this.tuesdayNumberOfLifts = tuesdayNumberOfLifts;
	}

	public String getWednesdayNumberOfLifts() {
		return wednesdayNumberOfLifts;
	}

	public void setWednesdayNumberOfLifts(String wednesdayNumberOfLifts) {
		this.wednesdayNumberOfLifts = wednesdayNumberOfLifts;
	}

	public String getThursdayNumberOfLifts() {
		return thursdayNumberOfLifts;
	}

	public void setThursdayNumberOfLifts(String thursdayNumberOfLifts) {
		this.thursdayNumberOfLifts = thursdayNumberOfLifts;
	}

	public String getFridayNumberOfLifts() {
		return fridayNumberOfLifts;
	}

	public void setFridayNumberOfLifts(String fridayNumberOfLifts) {
		this.fridayNumberOfLifts = fridayNumberOfLifts;
	}

	public String getSaturdayNumberOfLifts() {
		return saturdayNumberOfLifts;
	}

	public void setSaturdayNumberOfLifts(String saturdayNumberOfLifts) {
		this.saturdayNumberOfLifts = saturdayNumberOfLifts;
	}

	public String getSundayNumberOfLifts() {
		return sundayNumberOfLifts;
	}

	public void setSundayNumberOfLifts(String sundayNumberOfLifts) {
		this.sundayNumberOfLifts = sundayNumberOfLifts;
	}

	public String getReceiptRequestFlag() {
		return receiptRequestFlag;
	}

	public void setReceiptRequestFlag(String receiptRequestFlag) {
		this.receiptRequestFlag = receiptRequestFlag;
	}

	public String getPoRequired() {
		return poRequired;
	}

	public void setPoRequired(String poRequired) {
		this.poRequired = poRequired;
	}

	public String getSpecialHandling() {
		return specialHandling;
	}

	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}

	public String getDisposalCode() {
		return disposalCode;
	}

	public void setDisposalCode(String disposalCode) {
		this.disposalCode = disposalCode;
	}

	public String getDisposalPriceCode() {
		return disposalPriceCode;
	}

	public void setDisposalPriceCode(String disposalPriceCode) {
		this.disposalPriceCode = disposalPriceCode;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(String weightLimit) {
		this.weightLimit = weightLimit;
	}

	public String getRemoteMonitorFlag() {
		return remoteMonitorFlag;
	}

	public void setRemoteMonitorFlag(String remoteMonitorFlag) {
		this.remoteMonitorFlag = remoteMonitorFlag;
	}

	public String getCityAccount() {
		return cityAccount;
	}

	public void setCityAccount(String cityAccount) {
		this.cityAccount = cityAccount;
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

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
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
	
			public String getFirstServiceDate() {
		return StringUtils.isBlank(firstServiceDate) ?  null :  firstServiceDate;
	}
	public void setFirstServiceDate(String firstServiceDate) {
		this.firstServiceDate = StringUtils.isBlank(firstServiceDate) ? null : firstServiceDate;
	}
	public String getCreateDeliveryUR() {
		return createDeliveryUR;
	}
	public void setCreateDeliveryUR(String createDeliveryUR) {
		this.createDeliveryUR = createDeliveryUR;
	}
	
	public String getDeliveryPONumber() {
		return deliveryPONumber;
	}
	public void setDeliveryPONumber(String deliveryPONumber) {
		this.deliveryPONumber = deliveryPONumber;
	}
	public String getUrEffectiveDate() {
		return urEffectiveDate;
	}
	public void setUrEffectiveDate(String urEffectiveDate) {
		this.urEffectiveDate = urEffectiveDate;
	}
	public String getUrPlanDate() {
		return urPlanDate;
	}
	public void setUrPlanDate(String urPlanDate) {
		this.urPlanDate = urPlanDate;
	}
	public String getDeliveryNote1() {
		return deliveryNote1;
	}
	public void setDeliveryNote1(String deliveryNote1) {
		this.deliveryNote1 = deliveryNote1;
	}
	
	public String getSchedulePermService() {
		return schedulePermService;
	}
	public void setSchedulePermService(String schedulePermService) {
		this.schedulePermService = schedulePermService;
	}
	public String getSchedulePermServiceDate() {
		return schedulePermServiceDate;
	}
	public void setSchedulePermServiceDate(String schedulePermServiceDate) {
		this.schedulePermServiceDate = schedulePermServiceDate;
	}
	public String getSchedulePermServicePlanDate() {
		return schedulePermServicePlanDate;
	}
	public void setSchedulePermServicePlanDate(String schedulePermServicePlanDate) {
		this.schedulePermServicePlanDate = schedulePermServicePlanDate;
	}


	@JsonIgnore
	@Override
	public String getKey() {
		return this.companyNumber 
				+ "-" + this.accountNumber 
				+ "-" + this.siteNumber 
				+ "-" + this.containerGroupNumber;
	}
	
	@JsonIgnore
	@Override
	public String getTabName() {
		return TAB_NAME;
	}

	@Override
	public String getKeyName() {
		return "Container Group";
	}

	@Override
	public String toString() {
		return "CONTAINER INFO [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + 
				 ", deliverNOTE1= "+ deliveryNote1 + ", siteNumber="+ siteNumber + ", schedulePeRM=" + schedulePermService + ", schedulePermServiceDate= " + schedulePermServiceDate + ", schedulePermServicePlaNDAte= " + schedulePermServicePlanDate +    "]";
	}

	public String getRecurChargeFrequency() {
		return recurChargeFrequency;
	}

	public void setRecurChargeFrequency(String recurChargeFrequency) {
		this.recurChargeFrequency = recurChargeFrequency;
	}
}
