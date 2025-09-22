package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountBalance {
	
	@JsonProperty("OpenInvoiceAmount")
	private double openInvoiceAmount;
	
	@JsonProperty("CashOnAccount")
	private double cashOnAccount;
	
	@JsonProperty("TotalAmountDue")
	private double totalAmountDue;
	
	@JsonProperty("PastDueAmount")
	private double pastDueAmount;
	
	@JsonProperty("CreditMemos")
	private double creditMemos;
	
	@JsonProperty("DebitMemos")
	private double debitMemos;
	
	@JsonProperty("DepositBalance")
	private double depositBalance;
	
	@JsonProperty("CreditLimit")
	private double creditLimit;
	
	@JsonProperty("RiskCode")
	private String riskCode;
	
	public AccountBalance() {}

	public double getOpenInvoiceAmount() {
		return openInvoiceAmount;
	}

	public void setOpenInvoiceAmount(double openInvoiceAmount) {
		this.openInvoiceAmount = openInvoiceAmount;
	}

	public double getCashOnAccount() {
		return cashOnAccount;
	}

	public void setCashOnAccount(double cashOnAccount) {
		this.cashOnAccount = cashOnAccount;
	}

	public double getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	public double getPastDueAmount() {
		return pastDueAmount;
	}

	public void setPastDueAmount(double pastDueAmount) {
		this.pastDueAmount = pastDueAmount;
	}

	public double getCreditMemos() {
		return creditMemos;
	}

	public void setCreditMemos(double creditMemos) {
		this.creditMemos = creditMemos;
	}

	public double getDebitMemos() {
		return debitMemos;
	}

	public void setDebitMemos(double debitMemos) {
		this.debitMemos = debitMemos;
	}

	public double getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(double depositBalance) {
		this.depositBalance = depositBalance;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	
}
