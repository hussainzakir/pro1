package com.repsrv.csweb.core.model.account.aob3;

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
import com.repsrv.csweb.core.gson.ExcludeGson;
import com.repsrv.csweb.core.model.account.imports.Row;

import lombok.Getter;
import lombok.Setter;

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
@ExcelSheet("Container Information")
@Valid

@Getter
@Setter

public class Aob3ContainerInfo extends Row{
    
    
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
	@Pattern(regexp = "^[a-zA-Z0-9]{1,2}$", message = "Rev Dist Code can only be two alpha characters in length or less")
	@NotBlank(message = "Rev Dist Code is required")
	@ExcelCellName("ACREVD")
	private String revDistCode;
	
	@SerializedName(value="containerType")
	@JsonProperty("Container Type")
	@Size(min = 1, max = 2)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Container Type can only be two alpha characters in length or less")
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
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Special Handling can only consist of alpha numeric characters")
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
	
	@JsonIgnore
	private Aob3SiteInfo aob3SiteInformation;

	@JsonIgnore
	private Aob3AccountInfo aob3AccountInformation;

	@Valid
	@ExcludeGson
	private List<Aob3RateInfo> aob3Rates;

	public List<Aob3RateInfo> getAob3Rates() {
		return aob3Rates == null ? Collections.emptyList() : aob3Rates;
	}
	
	public void setAob3Rates(List<Aob3RateInfo> aob3Rates) {
		this.aob3Rates = aob3Rates;
	}    

	@JsonIgnore
	public String getParentKey() {
		return this.companyNumber + "-" + this.accountNumber + "-" + this.siteNumber;
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
}
