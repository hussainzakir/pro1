package com.repsrv.csweb.core.model.account.openmarket;

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
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.openmarket.validators.OpenMarketValidSheetRowData;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Cust. Account", "Site", "Container Group","Rate Effective Date","Charge Code","Charge Type", "Charge Method",
"Charge Amount"})
@OpenMarketValidSheetRowData(sheetId = SheetId.RATE)
@ExcelSheet(ExcellSheetsConstants.RATE_SHEET_NAME)

@Getter
@Setter
@NoArgsConstructor
public class OpenMarketRateInformation extends Row {
	public static final String COMP_REQ = "Company Number is required";
	public static final String COMP_MAX_NUM_LENGTH = "Company number must be 3 numeric characters or less in length";
	public static final String ACC_REQ = "Account Number is required";
	public static final String ACC_MAX_NUM_LENGTH =  "Account number must be 7 numeric characters or less in length";
	public static final String SITE_REQ = "Site Number is required";
	public static final String SITE_MAX_NUM_LENGTH = "Site number must be 5 numeric characters or less in length";
	public static final String CONT_REQ = "Container Group is required";
	public static final String CONT_MAX_NUM_LENGTH = "Container Group must be 2 numeric characters or less in length";
	public static final String EFF_DATE_REQ = "Effectice Date is required";
	public static final String EFF_DATE_FORMAT = "Effective date invalid - format must be YYYYMMDD";
	public static final String CHARGE_CODE_REQ = "Charge Code is required";
	public static final String CHARGE_CODE_MAX_LENGTH = "Charge code must be between 1 and 3 characters in length";
	
	@SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = COMP_REQ)
	@Pattern(regexp = "^\\d{0,3}$", message = COMP_MAX_NUM_LENGTH)
	@ExcelCellName("CRCOMP")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@NotBlank(message = ACC_REQ )
	@Pattern(regexp = "^\\d{1,7}$", message = ACC_MAX_NUM_LENGTH)
	@ExcelCellName("CRACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = SITE_REQ)
	@Digits(integer = 5, fraction = 0, message = SITE_MAX_NUM_LENGTH )
	@ExcelCellName("CRSITE")
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Group")
	@NotBlank(message = CONT_REQ)
	@Digits(integer = 2, fraction = 0, message = CONT_MAX_NUM_LENGTH)
	@ExcelCellName("CRCTGR")
	private String containerGroup;
	
	@SerializedName(value="rateEffectiveDate")
	@JsonProperty("Rate Effective Date")
	@Pattern(regexp="^\\d{8}", message = EFF_DATE_FORMAT)
	@NotBlank(message = EFF_DATE_REQ)
	@ExcelCellName("CREFDT")
	private String effectiveDate;
	
	@SerializedName(value="chargeCode")
	@JsonProperty("Charge Code")
	@Pattern(regexp = "^(\\w{3,5})$", message = CHARGE_CODE_MAX_LENGTH)
	@NotBlank(message = CHARGE_CODE_REQ)
	@ExcelCellName("CRCHCD")
	private String chargeCode;

	@SerializedName(value="chargeType")
	@JsonProperty("Charge Type")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Charge Type can only consist of alpha characters")
	@Size(max = 1, message = "Charge Type cannot be longer than 1 char")
	@NotBlank(message = "Charge Type is required")
	@ExcelCellName("CRCHTY")
	private String chargeType;
	
	@SerializedName(value="chargeMethod")
	@JsonProperty("Charge Method")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Charge Method can only consist of alpha characters")
	@Size(max = 1, message = "Charge Method cannot be longer than 1 char")
	@NotBlank(message = "Charge Method is required")
	@ExcelCellName("CRCGMT")
	private String chargeMethod;

	@SerializedName(value="chargeRate")
	@JsonProperty("Charge Amount")
	@Digits(fraction = 2, integer = 9, message = "Charge Amount must be a decimal 9,2")
	@NotBlank(message = "Charge Amount is required")
	@ExcelCellName("CRCHRT")
	private String chargeAmount;

	@JsonIgnore
	private OpenMarketContainerInformation openMarketContainerInformation;
	
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
