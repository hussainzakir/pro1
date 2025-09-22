package com.repsrv.csweb.core.model.account.openmarket;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
import com.repsrv.csweb.core.account.openmarket.validators.OpenMarketValidSheetRowData;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.ANY, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@JsonPropertyOrder({"Company #","Cust. Account","Site","Container Group","Route Quantity", "Route #", "Stop #", "Week Delay Start Date"})
@OpenMarketValidSheetRowData(sheetId = SheetId.ROUTE)
@ExcelSheet("Route Information")
@Setter
@Getter
@NoArgsConstructor
public class OpenMarketRouteInformation extends Row {

    private static final String TAB_NAME = "Route Information";

    @SerializedName(value="companyNumber")
	@JsonProperty("Company #")
	@NotBlank(message = "Company Number is required")
	@Digits(integer = 3, fraction = 0, message = "Company number must be 3 numeric characters or less in length")
	@ExcelCellName("RDCOMP")
	private String companyNumber;
	
	@SerializedName(value="customerAccount")
	@JsonProperty("Cust. Account")
	@NotBlank(message = "Account Number is required")
	@Pattern(regexp = "^\\d{1,7}$", message = "Account number must be 7 numeric characters or less in length")
	@ExcelCellName("RDACCT")
	private String accountNumber;
	
	@SerializedName(value="site")
	@JsonProperty("Site")
	@NotBlank(message = "Site Number is required")
	@Digits(integer = 5, fraction = 0, message = "Site number must be 5 numeric characters or less in length")
	@ExcelCellName("RDSITE")
	private String siteNumber;
	
	@SerializedName(value="containerGroup")
	@JsonProperty("Container Group")
	@Digits(integer = 2, fraction = 0, message = "Container group number must be 2 numeric characters or less in length")
	@NotBlank(message = "Container Group Number is required")
	@ExcelCellName("RDCTGR")
	private String containerGroupNumber;

    @SerializedName(value="routeNumber")
	@JsonProperty("Route #")
	@Pattern(regexp = "^([1-7][0-9]{0,3})$", message = "Route number must start with 1-7 and connot be larger than 7999")
	@NotBlank(message = "Route Number is required")
	@ExcelCellName("RDROUT")
	private String routeNumber;

	@SerializedName(value="routeQuantity")
	@JsonProperty("Route Quantity")
	@Pattern(regexp = "^[1-9]([0-9]{0,2})$", message = "Route Quantity must start with 1-9 and connot be larger than 999")
	@NotBlank(message = "Route Quanitity is required")
	@ExcelCellName("RDQTY")
	private String routeQuantity;

	@SerializedName(value="firstServiceDate")
	@JsonProperty("Week Delay Start Date")
	@Pattern(message = "First Service Date invalid",
		regexp = "^(\\d{8})?$")
	@ExcelCellName("DYDT01")
	private String firstServiceDate;

	@SerializedName(value="stopSequence")
	@JsonProperty("Stop #")
	@Pattern(regexp = "^([1-9][0-9]{0,10})$", message = "Stop Sequence Number can only be 11 characters max")
	@NotBlank(message = "Sequence  Number is required")
	@ExcelCellName("RDSEQ#")
	private String stopNumber;

    
	@JsonIgnore
	private OpenMarketContainerInformation openMarketContainerInformationontainer;

	public String getFirstServiceDate() {
		return StringUtils.isBlank(firstServiceDate) ?  null :  firstServiceDate;
	}
	public void setFirstServiceDate(String firstServiceDate) {
		this.firstServiceDate = StringUtils.isBlank(firstServiceDate) ? null : firstServiceDate;
	}

    @Override
	public String toString() {
		return "Route [companyNumber=" + companyNumber + ", accountNumber=" + accountNumber + ", siteNumber="
				+ siteNumber + ", containerGroup=" + containerGroupNumber + ", routeNumber=" + routeNumber
				 + ", firstServiceDate=" + firstServiceDate + ", stopNumber=" + stopNumber + "]";
	}

	@JsonIgnore
	@Override
	public String getKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroupNumber + "-" 
				+ routeNumber;
	}
	
	@JsonIgnore
	@Override
	public String getParentKey() {
		return companyNumber + "-" + accountNumber + "-" 
				+ siteNumber + "-" + containerGroupNumber;
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
