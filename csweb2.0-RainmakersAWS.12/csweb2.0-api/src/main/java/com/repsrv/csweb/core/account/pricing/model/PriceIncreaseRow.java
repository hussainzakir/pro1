package com.repsrv.csweb.core.account.pricing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvCustomBindByName;
import com.repsrv.csweb.core.util.CsvStringTrimConverter;

public class PriceIncreaseRow {

	@JsonProperty("Index")
	@CsvCustomBindByName(column = "INDEX", converter = CsvStringTrimConverter.class)
	private String index;
	
	@JsonProperty("Company")
	@CsvCustomBindByName(column = "Company", converter = CsvStringTrimConverter.class)
	private String company;
	
	@JsonProperty("Account")
	@CsvCustomBindByName(column = "Acct", converter = CsvStringTrimConverter.class)
	private String account;
	
	@JsonProperty("Site")
	@CsvCustomBindByName(column = "Site", converter = CsvStringTrimConverter.class)
	private String site;
	
	@JsonProperty("containerGroup")
	@CsvCustomBindByName(column = "CGrp", converter = CsvStringTrimConverter.class)
	private String containerGroup;
	
	@CsvCustomBindByName(column = "CType", converter = CsvStringTrimConverter.class)
	private String containerType;
	
	@CsvCustomBindByName(column = "Lifts", converter = CsvStringTrimConverter.class)
	private String totalLifts;
	
	@CsvCustomBindByName(column = "QTY", converter = CsvStringTrimConverter.class)
	private String containerQty;
	
	@CsvCustomBindByName(column = "Size", converter = CsvStringTrimConverter.class)
	private String containerSize;
	
	@CsvCustomBindByName(column = "Charge Code", converter = CsvStringTrimConverter.class)
	private String chargeCode;
	
	@CsvCustomBindByName(column = "Type", converter = CsvStringTrimConverter.class)
	private String chargeType;
	
	@CsvCustomBindByName(column = "Method", converter = CsvStringTrimConverter.class)
	private String chargeMethod;
	
	@CsvCustomBindByName(column = "Charge Rate", converter = CsvStringTrimConverter.class)
	private String currentChargeRate;
	
	@CsvCustomBindByName(column = "New Rate", converter = CsvStringTrimConverter.class)
	private String newChargeRate;
	
	@CsvCustomBindByName(column = "Effective Date", converter = CsvStringTrimConverter.class)
	private String effectiveDate;
	
	@JsonIgnore
	@CsvCustomBindByName(column = "LAST_UPDATE_USER", converter = CsvStringTrimConverter.class)
	private String lastUpdateUser;
	
	@JsonIgnore
	@CsvCustomBindByName(column = "Error Messages", converter = CsvStringTrimConverter.class)
	private String errorMessages;
	
	public PriceIncreaseRow() {}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getContainerGroup() {
		return containerGroup;
	}

	public void setContainerGroup(String containerGroup) {
		this.containerGroup = containerGroup;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getTotalLifts() {
		return totalLifts;
	}

	public void setTotalLifts(String totalLifts) {
		this.totalLifts = totalLifts;
	}

	public String getContainerQty() {
		return containerQty;
	}

	public void setContainerQty(String containerQty) {
		this.containerQty = containerQty;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getCurrentChargeRate() {
		return currentChargeRate;
	}

	public void setCurrentChargeRate(String currentChargeRate) {
		this.currentChargeRate = currentChargeRate;
	}

	public String getNewChargeRate() {
		return newChargeRate;
	}

	public void setNewChargeRate(String newChargeRate) {
		this.newChargeRate = newChargeRate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMEssages) {
		this.errorMessages = errorMEssages;
	}

	@Override
	public String toString() {
		return "PriceIncreaseRow [index=" + index + ", company=" + company + ", account=" + account + ", site=" + site
				+ ", containerGroup=" + containerGroup + ", containerType=" + containerType + ", totalLifts="
				+ totalLifts + ", containerQty=" + containerQty + ", containerSize=" + containerSize + ", chargeCode="
				+ chargeCode + ", chargeType=" + chargeType + ", chargeMethod=" + chargeMethod + ", currentChargeRate="
				+ currentChargeRate + ", newChargeRate=" + newChargeRate + ", effectiveDate=" + effectiveDate
				+ ", lastUpdateUser=" + lastUpdateUser + ", errorMEssages=" + errorMessages + "]";
	}
	


}


