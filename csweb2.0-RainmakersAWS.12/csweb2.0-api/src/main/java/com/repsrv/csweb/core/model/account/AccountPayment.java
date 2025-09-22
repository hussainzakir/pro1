package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountPayment {

	@JsonProperty("CashReceivedDate")
	private String cashReceivedDate;
	
	@JsonProperty("CheckNumber")
	private int checkNumber;
	
	@JsonProperty("ReceiptAmount")
	private double receiptAmount;
	
	@JsonProperty("UnappliedAmount")
	private double unappliedAmount;
	
	@JsonProperty("LockboxNumber")
	private int lockboxNumber;
	
	@JsonProperty("ObligationID")
	private String obligationId;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("ReceiptReferenceNumber")
	private String receiptReferenceNumber;
	
	@JsonProperty("BankAccount")
	private long bankAccountNumber;

	@JsonProperty("BatchNumber")
	private long batchNumber;

	public AccountPayment() {}
	
	public String getCashReceivedDate() {
		return cashReceivedDate;
	}

	public void setCashReceivedDate(String cashReceivedDate) {
		this.cashReceivedDate = cashReceivedDate;
	}

	public int getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public double getUnappliedAmount() {
		return unappliedAmount;
	}

	public void setUnappliedAmount(double unappliedAmount) {
		this.unappliedAmount = unappliedAmount;
	}

	public int getLockboxNumber() {
		return lockboxNumber;
	}

	public void setLockboxNumber(int lockboxNumber) {
		this.lockboxNumber = lockboxNumber;
	}

	public String getObligationId() {
		return obligationId;
	}

	public void setObligationId(String obligationId) {
		this.obligationId = obligationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceiptReferenceNumber() {
		return receiptReferenceNumber;
	}

	public void setReceiptReferenceNumber(String receiptReferenceNumber) {
		this.receiptReferenceNumber = receiptReferenceNumber;
	}

	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public long getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(long batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	
}
