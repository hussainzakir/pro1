package com.repsrv.csweb.core.model.aae;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateEdit {
	
    private String rateStagingId;
    
    private String companyNumber;
    
    private String customerAccount;
    
    private String site;
    
    private String containerGroup;
    
    @JsonProperty("sharedContainerID")
    private String sharedContainerId;
    
    private String chargeCode;
    
    private String chargeRate;
    
    private String chargeType;
    
    private String chargeMethod;
    
    private String taxApplicationCode;
    
    private String billingAdjustFlag;
    
    private String rateEffectiveDate;
    
    private String closeDate;
    
    private String lastUpdateUser;
    
    private String lastUpdateDate;
    
    private String wasteMaterialType;
	
	private String unitOfMeasure;

	public String getRateStagingId() {
		return rateStagingId;
	}

	public void setRateStagingId(String rateStagingId) {
		this.rateStagingId = rateStagingId;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
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

	public String getSharedContainerId() {
		return sharedContainerId;
	}

	public void setSharedContainerId(String sharedContainerId) {
		this.sharedContainerId = sharedContainerId;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(String chargeRate) {
		this.chargeRate = chargeRate;
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

	public String getTaxApplicationCode() {
		return taxApplicationCode;
	}

	public void setTaxApplicationCode(String taxApplicationCode) {
		this.taxApplicationCode = taxApplicationCode;
	}

	public String getBillingAdjustFlag() {
		return billingAdjustFlag;
	}

	public void setBillingAdjustFlag(String billingAdjustFlag) {
		this.billingAdjustFlag = billingAdjustFlag;
	}

	public String getRateEffectiveDate() {
		return rateEffectiveDate;
	}

	public void setRateEffectiveDate(String rateEffectiveDate) {
		this.rateEffectiveDate = rateEffectiveDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getWasteMaterialType() {
		return wasteMaterialType;
	}

	public void setWasteMaterialType(String wasteMaterialType) {
		this.wasteMaterialType = wasteMaterialType;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
}
