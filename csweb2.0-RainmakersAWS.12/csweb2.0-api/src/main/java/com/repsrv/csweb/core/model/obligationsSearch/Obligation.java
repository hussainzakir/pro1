package com.repsrv.csweb.core.model.obligationsSearch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Obligation {
	@JsonProperty("Company")
	private String company;
	@JsonProperty("Account")
	private String account;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("ObligationId")
	private String obligationId;
	@JsonProperty("ObligationDate")
	private String obligationDate;
	@JsonProperty("OpenAmount")
	private String openAmount;
	@JsonProperty("TotalAmount")
	private String totalAmount;
	@JsonProperty("TransType")
	private String transType;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Region")
	private String region;
	@JsonProperty("AccountingPeriod")
	private String accountingPeriod;

	@JsonProperty("Site")
	private String site;

	public Obligation() {}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getObligationId() {
		return obligationId;
	}

	public void setObligationId(String obligationId) {
		this.obligationId = obligationId;
	}

	public String getObligationDate() {
		return obligationDate;
	}

	public void setObligationDate(String obligationDate) {
		this.obligationDate = obligationDate;
	}

	public String getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(String openAmount) {
		this.openAmount = openAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

}
