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
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.imports.validators.resicontract.ValidSheetRowData;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Account #","Site #","Container Grp","Effective Date","Charge Code"})
@ValidSheetRowData(sheetId = SheetId.RATE)
@ExcelSheet(ExcellSheetsConstants.RATE_SHEET_NAME)
public class RateInformation extends Row {

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
	@NotBlank(message = "Container Group is required")
	@Digits(integer = 2, fraction = 0, message = "Container Group must be 7 numeric characters or less in length")
	@ExcelCell(3)
	private String containerGroup;
	
	@SerializedName(value="rateEffectiveDate")
	@JsonProperty("Effective Date")
	@Pattern(regexp="^\\d{8}", message = "Effective date invalid - format must be YYYYMMDD")
	@NotBlank(message = "Effectice Date is required")
	@ExcelCell(4)
	private String effectiveDate;
	
	@SerializedName(value="chargeCode")
	@JsonProperty("Charge Code")
	@Pattern(regexp = "^(\\w{3,5})$", message = "Charge code must be between 1 and 3 characters in length")
	@NotBlank(message = "Charge Code is required")
	@ExcelCell(5)
	private String chargeCode;
	
	@ExcelCell(6)
	@SerializedName(value="RATEERROR")
	private String uploadError;

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

	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
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
