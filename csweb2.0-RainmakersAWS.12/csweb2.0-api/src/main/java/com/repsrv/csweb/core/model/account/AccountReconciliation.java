package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountReconciliation {

	@JsonProperty("ObligationTransactionType")
	private String transactionType;
	
	@JsonProperty("ObligationID")
	private String id;
	
	@JsonProperty("ObligationDate")
	private String date;
	
	@JsonProperty("ObligationHistory")
	private String history;
	
	@JsonProperty("ObligationTotalAmount")
	private double totalAmount = 0;
	
	@JsonProperty("ObligationOpenAmount")
	private double openAmount = 0;

	@JsonProperty("AppliedDate")
	private String appliedDate;
	
	@JsonProperty("CheckNumber")
	private String checkNumber;
	
	@JsonProperty("CashApplied")
	private double cashApplied = 0;
	
	@JsonProperty("AnticipationAmount")
	private double anticipationAmount = 0;
	
	@JsonProperty("DiscountAmount")
	private double discountAmount = 0;
	
	@JsonProperty("TolleranceAmount")
	private double tolleranceAmount = 0;
	
	@JsonProperty("WriteOffAmount")
	private double writeOffAmount = 0;	
	
	@JsonProperty("ChargeBackAmount")
	private double chargeBackAmount = 0;
	
	@JsonProperty("DebtCreditMemoApplied")
	private double debtCreditMemoApplied = 0;
	
	@JsonProperty("BankAccount")
	private long bankAccount;
	
	public AccountReconciliation() {}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(double openAmount) {
		this.openAmount = openAmount;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public double getCashApplied() {
		return cashApplied;
	}

	public void setCashApplied(double cashApplied) {
		this.cashApplied = cashApplied;
	}

	public double getAnticipationAmount() {
		return anticipationAmount;
	}

	public void setAnticipationAmount(double anticipationAmount) {
		this.anticipationAmount = anticipationAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getTolleranceAmount() {
		return tolleranceAmount;
	}

	public void setTolleranceAmount(double tolleranceAmount) {
		this.tolleranceAmount = tolleranceAmount;
	}

	public double getWriteOffAmount() {
		return writeOffAmount;
	}

	public void setWriteOffAmount(double writeOffAmount) {
		this.writeOffAmount = writeOffAmount;
	}

	public double getChargeBackAmount() {
		return chargeBackAmount;
	}

	public void setChargeBackAmount(double chargeBackAmount) {
		this.chargeBackAmount = chargeBackAmount;
	}

	public double getDebtCreditMemoApplied() {
		return debtCreditMemoApplied;
	}

	public void setDebtCreditMemoApplied(double debtCreditMemoApplied) {
		this.debtCreditMemoApplied = debtCreditMemoApplied;
	}

	public long getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(long bankAccount) {
		this.bankAccount = bankAccount;
	}

	
}
