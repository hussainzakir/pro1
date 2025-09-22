package com.repsrv.csweb.core.model.account;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.util.AS400DateUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountObligation {

	@JsonProperty("ObligationHistory")
	private String history;
	
	@JsonProperty("ObligationDate")
	private String date;
	
	@JsonProperty("ObligationTransactionType")
	private String transactionType;
	
	@JsonProperty("ObligationID")
	private String id;
	
	@JsonProperty("ObligationDueDate")
	private String dueDate;
	
	@JsonProperty("ObligationCloseDate")
	private String closeDate;
	
	@JsonProperty("ObligationOpenAmount")
	private double openAmount;
	
	@JsonProperty("ObligationTotalAmount")
	private double totalAmount;
	
	@JsonProperty("ObligationPONumber")
	private String poNumber;
	
	@JsonIgnore
	private List<AccountInvoiceDetail> invoiceDetails;
	
	private String company;
	
	private String account;
	
	public AccountObligation() {}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public double getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(double openAmount) {
		this.openAmount = openAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public List<AccountInvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<AccountInvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
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
	
	
}
