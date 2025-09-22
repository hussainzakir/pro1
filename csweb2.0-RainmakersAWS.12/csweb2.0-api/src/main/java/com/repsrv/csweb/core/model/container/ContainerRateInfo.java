package com.repsrv.csweb.core.model.container;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.util.AS400DateUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerRateInfo {

	@JsonProperty("Receipt_Required_Flag")
	private String receiptRequired;
	
	@JsonProperty("Purchase_Order_Required")
	private String poRequired;
	
	@JsonProperty("Billed_To_Date")
	private String billToDate;
	
	@JsonProperty("Revenue_Distribution_Code")
	private String revDistributionCode;
	
	@JsonProperty("Recurring_Charge_Frequency")
	private String recurringChargeFreq;
	
	@JsonProperty("Recurring_Charge_Frequenc_Desc")
	private String recurringChargeFreqDesc;
	
	@JsonProperty("Recur_Mnths_Adv_Bill")
	private String monthsInAdvRecurring;
	
	@JsonProperty("Remote_Monitor")
	private String remoteMonitor;
	
	@JsonProperty("Disposal_Rate_Restriction")
	private String disposalRateRestriction;
	
	@JsonProperty("Operational_Rate_Restriction")
	private String operationalRateRestriction;
	
	@JsonProperty("Container_Close/End_Date")
	private String closeDate;
	
	@JsonProperty("Container_Qty_On_Site")
	private int quantityOnSite;
	
	@JsonProperty("Receipt_Required_Flag_Desc")
	private String receiptRequiredFlagDesc;
	
	@JsonProperty("Revenue_Distribution_Code_Desc")
	private String revenueDistributionCodeDesc;
	
	@JsonProperty("Recurring_Charge_Frequency_Desc")
	private String recurringChargeFrequencyDesc;

	public String getReceiptRequired() {
		return receiptRequired;
	}

	public void setReceiptRequired(String receiptRequired) {
		this.receiptRequired = receiptRequired;
	}

	public String getPoRequired() {
		return poRequired;
	}

	public void setPoRequired(String poRequired) {
		this.poRequired = poRequired;
	}

	public String getBillToDate() {
		return billToDate;
	}

	public void setBillToDate(String billToDate) {
		this.billToDate = AS400DateUtil.format8DigitDate(billToDate);
	}

	public String getRevDistributionCode() {
		return revDistributionCode;
	}

	public void setRevDistributionCode(String revDistributionCode) {
		this.revDistributionCode = revDistributionCode;
	}

	public String getRecurringChargeFreq() {
		return recurringChargeFreq;
	}

	public void setRecurringChargeFreq(String recurringChargeFreq) {
		this.recurringChargeFreq = recurringChargeFreq;
	}

	public String getRecurringChargeFreqDesc() {
		return recurringChargeFreqDesc;
	}

	public void setRecurringChargeFreqDesc(String recurringChargeFreqDesc) {
		this.recurringChargeFreqDesc = recurringChargeFreqDesc;
	}

	public String getMonthsInAdvRecurring() {
		return monthsInAdvRecurring;
	}

	public void setMonthsInAdvRecurring(String monthsInAdvRecurring) {
		this.monthsInAdvRecurring = monthsInAdvRecurring;
	}

	public String getRemoteMonitor() {
		return remoteMonitor;
	}

	public void setRemoteMonitor(String remoteMonitor) {
		this.remoteMonitor = remoteMonitor;
	}

	public String getDisposalRateRestriction() {
		return disposalRateRestriction;
	}

	public void setDisposalRateRestriction(String disposalRateRestriction) {
		this.disposalRateRestriction = disposalRateRestriction;
	}

	public String getOperationalRateRestriction() {
		return operationalRateRestriction;
	}

	public void setOperationalRateRestriction(String operationalRateRestriction) {
		this.operationalRateRestriction = operationalRateRestriction;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = AS400DateUtil.format8DigitDate(closeDate);
	}
	
	public String getReceiptRequiredFlagDesc() {
		return receiptRequiredFlagDesc;
	}

	public void setReceiptRequiredFlagDesc(String receiptRequiredFlagDesc) {
		this.receiptRequiredFlagDesc = receiptRequiredFlagDesc;
	}

	public String getRevenueDistributionCodeDesc() {
		return revenueDistributionCodeDesc;
	}

	public void setRevenueDistributionCodeDesc(String revenueDistributionCodeDesc) {
		this.revenueDistributionCodeDesc = revenueDistributionCodeDesc;
	}

	public String getRecurringChargeFrequencyDesc() {
		return recurringChargeFrequencyDesc;
	}

	public void setRecurringChargeFrequencyDesc(String recurringChargeFrequencyDesc) {
		this.recurringChargeFrequencyDesc = recurringChargeFrequencyDesc;
	}
	
}
