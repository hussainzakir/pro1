package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountCharge {

	@JsonProperty("Site")
	private String site;
	
	@JsonProperty("ContainerGroup")
	private int containerGroup;
	
	@JsonProperty("ChargeDate")
	private String chargeDate;
	
	@JsonProperty("ContainerType")
	private String containerType;
	
	@JsonProperty("ContainerSize")
	private String containerSize;
	
	@JsonProperty("Compactor")
	private String compactor;
	
	@JsonProperty("ChargeCode")
	private String chargeCode;
	
	@JsonProperty("ChargeAmount")
	private double chargeAmount;
	
	@JsonProperty("FromDate")
	private String fromDate;
	
	@JsonProperty("ToDate")
	private String toDate;
	
	@JsonProperty("ServiceCode")
	private String serviceCode;
	
	@JsonProperty("Quantity")
	private double quantity;
	
	@JsonProperty("Receipt")
	private int receipt;
	
	@JsonProperty("LineOfBusines")
	private String lineOfBusiness;
	
	public AccountCharge() {}
	
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

	public String getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
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

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public double getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public int getReceipt() {
		return receipt;
	}

	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	
	
}
