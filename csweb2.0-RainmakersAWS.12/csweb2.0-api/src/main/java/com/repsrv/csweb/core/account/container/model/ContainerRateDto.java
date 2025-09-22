package com.repsrv.csweb.core.account.container.model;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;
import com.repsrv.csweb.core.util.AS400DateUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerRateDto {

	@JsonProperty("companyId")
	private String companyId;
	
	@JsonProperty("accountId")
	private String accountId;
	
	@JsonProperty("siteId")
	private String siteId;
	
	@JsonProperty("contGrp")
	private String containerGroup;
	
	@JsonProperty("haulCharge")
	private String haulCharge;
	
	@JsonProperty("disposalRate")
	private String disposalRate;
	
	@JsonProperty("disposalUOM")
	private String disposalUOM;
	
	@JsonProperty("recurrRate")
	private String recurringRate;
	
	@JsonProperty("effectiveDate")
	private String effectiveDate;
	
	@JsonProperty("chargeCode")
	private String chargeCode;
	
	@JsonProperty("chargeType")
	private String chargeType;
	
	@JsonProperty("chargeMethod")
	private String chargeMethod;
	
	@JsonProperty("taxApplicationCode")
	private String taxApplicationCode;
	
	@JsonProperty("chargeDescription")
	private String chargeDescription;
	
	@JsonProperty("unitOfMeasure")
	private String unitOfMeasure;
	
	@JsonProperty("chargeRate")
	private String chargeRate;
	
	@JsonProperty("wasteMaterialType")
	private String wasteMaterialType;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getContainerGroup() {
		return containerGroup;
	}

	public void setContainerGroup(String containerGroup) {
		this.containerGroup = containerGroup;
	}

	public String getHaulCharge() {
		return haulCharge;
	}

	public void setHaulCharge(String haulCharge) {
		this.haulCharge = haulCharge;
	}

	public String getDisposalRate() {
		return disposalRate;
	}

	public void setDisposalRate(String disposalRate) {
		this.disposalRate = disposalRate;
	}

	public String getDisposalUOM() {
		return disposalUOM;
	}

	public void setDisposalUOM(String disposalUOM) {
		this.disposalUOM = disposalUOM;
	}

	public String getRecurringRate() {
		return recurringRate;
	}

	public void setRecurringRate(String recurringRate) {
		this.recurringRate = recurringRate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = AS400DateUtil.format8DigitDate(effectiveDate);
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

	public String getTaxApplicationCode() {
		return taxApplicationCode;
	}

	public void setTaxApplicationCode(String taxApplicationCode) {
		this.taxApplicationCode = taxApplicationCode;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}
	
	public ContainerGroupRate toGroupRate() {
		ContainerGroupRate grate = new ContainerGroupRate();
		BeanUtils.copyProperties(this, grate);
		
		return grate;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(String chargeRate) {
		this.chargeRate = chargeRate;
	}

	public String getWasteMaterialType() {
		return wasteMaterialType;
	}

	public void setWasteMaterialType(String wasteMaterialType) {
		this.wasteMaterialType = wasteMaterialType;
	}
}
