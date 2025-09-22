package com.repsrv.csweb.core.model.account.openmarket;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
@JsonPropertyOrder({"Company #", "Cust. Account", "Site", "Container Group", "Start Date", "Account Type",
	"Rev Distribution Code", "Container Type", "Container Size", "Container Id Code", "Association Code", "Container Owned", "Container Qty Order",
	"Total Lifts", "Period Length", "Stop Code", "Rate Code", "District Code", "Recurring Months Adv Bill", "Recur Charge Freq", "Billed To Date", 
	"Next Full Charge Date", "Note 1", "Note 2", "Note 3", "Note 4", "Note 5", "Transaction Code", "Reason Code", "Competitor Code"})

@OpenMarketValidSheetRowData(sheetId = SheetId.CONTAINER)
@ExcelSheet("Container Information")

@Getter
@Setter

public class OpenMarketContainerInformation extends Row {
	
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
	@Digits(integer = 5, fraction = 0, message = "Site number must be 5 numeric characters or less in length")
	@ExcelCellName("ACSITE")
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Group")
	@Digits(integer = 2, fraction = 0, message = "Container group number must be 2 numeric characters or less in length")
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
	
	@SerializedName(value="contractGroupNumber")
	@JsonProperty("Contract Group #")
	@Size(max = 2, message = "Contract group number must be 2 numeric characters or less in length")
	@ExcelCellName("ACCGRP")
	private String contractGroupNumber;
	
	@SerializedName(value="accountType")
	@JsonProperty("Account Type")
	@Pattern(regexp = "^[PpSsTt]$", message = "Account Type must be P,S or T")
	@NotBlank(message = "Account Type is required")
	@ExcelCellName("ACTYPE")
	private String accountType;
	
	@SerializedName(value="containerIdCode")
	@JsonProperty("Container Id Code")
	@Pattern(regexp = "^[OFH]$", message = "Container Id Code must be O, F, H")
	@NotBlank(message = "Container ID Code is required")
	@ExcelCellName("ACFG04")
	private String containerIdCode;
	
	@SerializedName(value="associationCode")
	@JsonProperty("Association Code")
	@Pattern(regexp = "^[0-9]*$", message = "HOA Association Code must contain only numeric characters or be blank")
	@Size(max = 18, message = "HOA Association Code must be 18 numeric characters or less in length")
	@ExcelCellName("ASCASCD")
	private String hoaAssociationCode;
	
	@SerializedName(value="stopCode")
	@JsonProperty("Stop Code")
	@Pattern(regexp = "^[a-zA-Z0-9]$", message = "Stop code can only be one alpha character in length")
	@NotBlank(message = "Stop Code is required")
	@ExcelCellName("ACSTCD")
	private String stopCode;

	@SerializedName(value="rateType")
	@JsonProperty("Rate Code")
	@Pattern(regexp = "^[a-zA-Z0-9]$", message = "Rate code can only be one alpha character in length")
	@NotBlank(message = "Rate Code is required")
	@ExcelCellName("ACRATT")
	private String rateCode;

	@SerializedName(value="residentialDistrict")
	@JsonProperty("District Code")
	@Pattern(regexp = "^\\d{0,2}$", message = "District Code must be up to 2 numeric characters or blank")
	@ExcelCellName("ACDIST")
	private String districtCode;

	@SerializedName(value="recurMnthsAdvBill")
	@JsonProperty("Recurring Months Adv Bill")
	@Pattern(regexp = "^\\d{0,1}$", message = "Recurring months adv. bill must be 1 numeric characters")
	@NotBlank(message = "Recur Mnths Adv Bill is required")
	@ExcelCellName("ACAVRC")
	private String recurMonthsAdvBill;

	@SerializedName(value="recurChargeFrequency")
	@JsonProperty("Recur Charge Freq")
	@Digits(integer = 2, fraction = 0, message = "Recur Charge Frequency must be 2 numeric characters or less in length")
	@NotBlank(message = "Recur Charge Freq is required")
	@ExcelCellName("ACRCFQ")
	private String recurChargeFrequency;

	@SerializedName(value="billedToDate")
	@JsonProperty("Billed To Date")
	@Pattern(regexp = "^(\\d{8}|0{0,8}|\\s*|)$",
			message = "Bill To Date invalid")
	@NotBlank(message = "Bill To Date is required")
	@ExcelCellName("ACBTDT")
	private String billedToDate;

	@SerializedName(value="nextRecurChargeDate")
	@JsonProperty("Next Full Charge Date")
	@Pattern(regexp = "^(\\d{8}|0{0,8}|\\s*|)$", 
			message = "Next Full Charge date invalid")
	@NotBlank(message = "Next Full Charge Date is required")
	@ExcelCellName("ACREDT")
	private String nextFullchargeDate;
	
	@SerializedName(value="revenueDistributionCode")
	@JsonProperty("Rev Distribution Code")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,2}$", message = "Rev Dist Code can only be two alpha characters in length or less")
	@NotBlank(message = "Rev Dist Code is required")
	@ExcelCellName("ACREVD")
	private String revDistCode;
	
	@SerializedName(value="containerType")
	@JsonProperty("Container Type")
	@Pattern(regexp = "^[a-zA-Z]{0,2}$", message = "Container Type can only be two alpha characters in length or less")
	@NotBlank(message = "Container Type is required")
	@ExcelCellName("ACCTTP")
	private String containerType;
	
	@SerializedName(value="containerSize")
	@JsonProperty("Container Size")
	@Digits(fraction = 2, integer = 5, message = "Container size must be a decimal 5,2")
	@NotBlank(message = "Container Size is required")
	@ExcelCellName("ACCTSZ")
	private String containerSize;
	
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
	
	@SerializedName(value="totalLifts")
	@JsonProperty("Total Lifts")
	@Pattern(regexp = "^\\d{0,3}$", message = "Total Number Of Lifts cannot be larger than 999 or less than 0")
	@NotBlank(message = "Total Lifts is required")
	@ExcelCellName("ACTOTL")
	private String totalLifts;
	
	@SerializedName(value="periodLength")
	@JsonProperty("Period Length")
	@Pattern(regexp = "^(\\d{1,2})$", message = "Period Length must be a number between 0 and 99")
	@NotBlank(message = "Period Length is required")
	@ExcelCellName("ACPLTH")
	private String periodLength;
	
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
	
	@SerializedName(value="leadSource")
	@JsonProperty("Lead Source")
	@Pattern(regexp = "^[a-zA-Z-]$", message = "Lead Source must be alphanumeric, and cannot exceed 1 character in length")
	@NotBlank(message = "Lead Source is required")
	@ExcelCellName("SDKSF")
	private String leadSource;

	@Valid
	@ExcludeGson
	private List<OpenMarketRateInformation> openMarketRateInformation;

	@Valid
	@ExcludeGson
	private List<OpenMarketRouteInformation> openMarketRoutes;

	public List<OpenMarketRateInformation> getOpenMarketRateInformation() {
		return openMarketRateInformation == null ? Collections.emptyList() : openMarketRateInformation;
	}

	public List<OpenMarketRouteInformation> getOpenMarketRoutes() {
		return openMarketRoutes == null ? Collections.emptyList() : openMarketRoutes;
	}

	@JsonIgnore
	private OpenMarketSiteInformation openMarketSiteInformation;

	public void setCompanyNumber(String companyNumber) {
		if (companyNumber.length() == 2){
			this.companyNumber = "0"+companyNumber.trim();
		} else if (companyNumber.length() == 1) {
			this.companyNumber = "00"+companyNumber.trim();
		}
		this.companyNumber = companyNumber.trim();
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
		return "CONTAINER INFO [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + "]";
	}

}