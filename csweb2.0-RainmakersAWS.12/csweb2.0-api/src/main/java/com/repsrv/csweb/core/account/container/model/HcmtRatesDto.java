package com.repsrv.csweb.core.account.container.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HcmtRatesDto {


    private String company;
    private String account;
    private String site;
    private String container;
    private String vendorNumber;
    private String vendorType;
    private String chargeCode;
    private String chargeCodeDesc;
    private String haulerCost;
    private String frfAllowed;
    private String unitOfMeasure;
    private String effectiveDate;
    private String closeDate;

    public String getCompany() {
        return company;
    }

    public String getAccount() {
        return account;
    }

    public String getSite() {
        return site;
    }

    public String getContainer() {
        return container;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public String getVendorType() {
        return vendorType;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public String getChargeCodeDesc() {
        return chargeCodeDesc;
    }

    public String getHaulerCost() {
        return haulerCost;
    }

    public String getFrfAllowed() {
        return frfAllowed;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public void setChargeCodeDesc(String chargeCodeDesc) {
        this.chargeCodeDesc = chargeCodeDesc;
    }

    public void setHaulerCost(String haulerCost) {
        this.haulerCost = haulerCost;
    }

    public void setFrfAllowed(String frfAllowed) {
        this.frfAllowed = frfAllowed;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setEffectiveDate(String effectiveDate) {
        String dateFormatted = effectiveDate;
		if (StringUtils.isNotBlank(effectiveDate) && effectiveDate.trim().length() == 10)
			dateFormatted = StringUtils.substring(effectiveDate, 5, 7) + "/" + StringUtils.substring(effectiveDate, 8, 10) + "/"
					+ StringUtils.substring(effectiveDate, 0, 4);
        this.effectiveDate = dateFormatted;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }
}
