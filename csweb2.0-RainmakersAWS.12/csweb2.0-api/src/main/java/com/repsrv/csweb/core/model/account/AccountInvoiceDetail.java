package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.util.AS400DateUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountInvoiceDetail {

	@JsonProperty("Company")
	private String company;
	
	@JsonProperty("Account")
	private String account;
	
	@JsonProperty("Site")
	private String site;
	
	@JsonProperty("ContainerGroup")
	private int containerGroup;

	@JsonProperty("Contract")
	private String contract;
	
	@JsonProperty("ContractGroup")
	private int contractGroup;
	
	@JsonProperty("InvoiceDate")
	private String invoiceDate;
	
	@JsonProperty("FromDate")
	private String fromDate;
	
	@JsonProperty("ToDate")
	private String toDate;
	
	@JsonProperty("ChargeCode")
	private String chargeCode;
	
	@JsonProperty("ChargeDescription")
	private String chargeDescription;
	
	@JsonProperty("Charge")
	private double charge;
	
	@JsonProperty("ContainerType")
	private String containerType;
	
	@JsonProperty("ContainerSize")
	private double containerSize;
	
	@JsonProperty("ContainerQuantity")
	private double containerQuantity;
	
	@JsonProperty("Receipt")
	private String receipt;	
	
	@JsonProperty("ObligationReference")
	private String obligationReference;	
	
	@JsonProperty("ObligationDate")
	private String obligationDate;
	
	@JsonProperty("ObligationTotal")
	private double obligationTotal;
	
	@JsonProperty("ObligationOpen")
	private double obligationOpen;

	public AccountInvoiceDetail() {}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getContainerGroup() {
		return containerGroup;
	}

	public void setContainerGroup(int containerGroup) {
		this.containerGroup = containerGroup;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public int getContractGroup() {
		return contractGroup;
	}

	public void setContractGroup(int contractGroup) {
		this.contractGroup = contractGroup;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = AS400DateUtil.formatDigitDate(invoiceDate);
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = AS400DateUtil.formatDigitDate(fromDate);
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = AS400DateUtil.formatDigitDate(toDate);
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public double getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(double containerSize) {
		this.containerSize = containerSize;
	}

	public double getContainerQuantity() {
		return containerQuantity;
	}

	public void setContainerQuantity(double containerQuantity) {
		this.containerQuantity = containerQuantity;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getObligationReference() {
		return obligationReference;
	}

	public void setObligationReference(String obligationReference) {
		this.obligationReference = obligationReference;
	}

	public String getObligationDate() {
		return obligationDate;
	}

	public void setObligationDate(String obligationDate) {
		this.obligationDate = obligationDate;
	}

	public double getObligationTotal() {
		return obligationTotal;
	}

	public void setObligationTotal(double obligationTotal) {
		this.obligationTotal = obligationTotal;
	}

	public double getObligationOpen() {
		return obligationOpen;
	}

	public void setObligationOpen(double obligationOpen) {
		this.obligationOpen = obligationOpen;
	}


	
}