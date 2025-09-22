package com.repsrv.csweb.core.model.account.onboarding;

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
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.onboarding.validators.AobValidSheetRowData;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Cust. Account", "Site", "Container Group","Rate Effective Date","Charge Code","Charge Type", "Charge Method",
"Charge Rate","Waste Material Type", "Unit Of Measure"})
@AobValidSheetRowData(sheetId = SheetId.RATE)
@ExcelSheet(ExcellSheetsConstants.RATE_SHEET_NAME)

public class AobRateInformation extends Row {
	
	public static final String COMP_REQ = "Company Number is required";
	public static final String COMP_MAX_NUM_LENGTH = "Company number must be 3 numeric characters or less in length";
	public static final String ACC_REQ = "Account Number is required";
	public static final String ACC_MAX_NUM_LENGTH =  "Account number must be 7 numeric characters or less in length";
	public static final String SITE_REQ = "Site Number is required";
	public static final String SITE_MAX_NUM_LENGTH = "Site number must be 7 numeric characters or less in length";
	public static final String CONT_REQ = "Container Group is required";
	public static final String CONT_MAX_NUM_LENGTH = "Container Group must be 7 numeric characters or less in length";
	public static final String EFF_DATE_REQ = "Effectice Date is required";
	public static final String EFF_DATE_FORMAT = "Effective date invalid - format must be YYYYMMDD";
	public static final String CHARGE_CODE_REQ = "Charge Code is required";
	public static final String CHARGE_CODE_MAX_LENGTH = "Charge code must be between 1 and 3 characters in length";
	
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = COMP_REQ)
	@Pattern(regexp = "^\\d{0,3}$", message = COMP_MAX_NUM_LENGTH)
	@ExcelCellName("CCCOMP")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@NotBlank(message = ACC_REQ )
	@Pattern(regexp = "^\\d{1,7}$", message = ACC_MAX_NUM_LENGTH)
	@ExcelCellName("CCACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = SITE_REQ)
	@Digits(integer = 5, fraction = 0, message = SITE_MAX_NUM_LENGTH )
	@ExcelCellName("CCSITE")
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Group")
	@NotBlank(message = CONT_REQ)
	@Digits(integer = 2, fraction = 0, message = CONT_MAX_NUM_LENGTH)
	@ExcelCellName("CCCTGR")
	private String containerGroup;
	
	@SerializedName(value="rateEffectiveDate")
	@JsonProperty("Rate Effective Date")
	@Pattern(regexp="^\\d{8}", message = EFF_DATE_FORMAT)
	@NotBlank(message = EFF_DATE_REQ)
	@ExcelCellName("CCEFDT")
	private String effectiveDate;
	
	@SerializedName(value="chargeCode")
	@JsonProperty("Charge Code")
	@Pattern(regexp = "^(\\w{3,5})$", message = CHARGE_CODE_MAX_LENGTH)
	@NotBlank(message = CHARGE_CODE_REQ)
	@ExcelCellName("CCCHCD")
	private String chargeCode;

	@SerializedName(value="chargeType")
	@JsonProperty("Charge Type")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Charge Type can only consist of alpha characters")
	@Size(max = 1, message = "Charge Type cannot be longer than 1 char")
	@NotBlank(message = "Charge Type is required")
	@ExcelCellName("CCCHTY")
	private String chargeType;
	
	@SerializedName(value="chargeMethod")
	@JsonProperty("Charge Method")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Charge Method can only consist of alpha characters")
	@Size(max = 1, message = "Charge Method cannot be longer than 1 char")
	@NotBlank(message = "Charge Method is required")
	@ExcelCellName("CCCGMT")
	private String chargeMethod;
	
	@SerializedName(value="wasteMaterialType")
	@JsonProperty("Waste Material Type")
	@Pattern(regexp = "^[a-zA-Z&]*$", message = "Waste Material Type can only consist of alpha characters")
	@Size(max = 3, message = "Waste Material Type cannot be longer than 3 characters")
	@ExcelCellName("CMWMAT")
	private String wasteMaterialType;
	
	@SerializedName(value="unitOfMeasure")
	@JsonProperty("Unit Of Measure")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Unit Of Measure can only consist of alpha characters")
	@Size(max = 2, message = "Unit of Measure cannot be longer than 2 characters")
	@ExcelCellName("CMUOM")
	private String unitOfMeasure;

	@SerializedName(value="chargeRate")
	@JsonProperty("Charge Rate")
	@Digits(fraction = 2, integer = 9, message = "Charge Amount must be a decimal 9,2")
	@NotBlank(message = "Charge Amount is required")
	@ExcelCellName("CCCHRT")
	private String chargeAmount;

	@JsonIgnore
	private AobContainerInformation aobContainerInformation;
	
	@JsonIgnore
	private AobSiteInformation aobSiteInformation;

	@JsonIgnore
	private AobAccountInformation aobAccountInformation;
	
	@ExcelCell(11)
	@SerializedName(value="RATEERROR")
	private String uploadError;

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
		this.accountNumber = accountNumber.trim();
	}

	public String getSiteNumber() {
		return siteNumber;
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

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getWasteMaterialType() {
		return wasteMaterialType;
	}

	public void setWasteMaterialType(String wasteMaterialType) {
		this.wasteMaterialType = wasteMaterialType;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}

	public AobContainerInformation getAobContainerInformation() {
		return aobContainerInformation;
	}

	public void setAobContainerInformation(AobContainerInformation aobContainerInformation) {
		this.aobContainerInformation = aobContainerInformation;
	}

	public AobSiteInformation getAobSiteInformation() {
		return aobSiteInformation;
	}

	public void setAobSiteinformation(AobSiteInformation aobSiteInformation) {
		this.aobSiteInformation = aobSiteInformation;
	}	

	public AobAccountInformation getAobAccountInformation() {
		return aobAccountInformation;
	}

	public void setAobAccountInformation(AobAccountInformation aobAccountInformation) {
		this.aobAccountInformation = aobAccountInformation;
	}	

	@JsonIgnore
	public String getParentKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroup;
	}
	
	@Override
	public String toString() {
		return "Rate [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + ", siteNumber="
				+ siteNumber + ", containerGroup=" + containerGroup + ", effectiveDate=" + effectiveDate
				+ ", chargeCode=" + chargeCode + "]";
	}

	@JsonIgnore
	@Override
	public String getKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroup + "-" ;
	}
	
	@JsonIgnore
	@Override
	public String getTabName() {
		return ExcellSheetsConstants.RATE_SHEET_NAME;
	}
	
	@Override
	public String getKeyName() {
		return "Company+Account+Site+Container";
	}
}
