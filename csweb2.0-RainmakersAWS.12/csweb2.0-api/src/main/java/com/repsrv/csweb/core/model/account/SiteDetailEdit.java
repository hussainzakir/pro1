package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteDetailEdit {
	
	@JsonProperty("companyNumber")
	private String company;
	
	@JsonProperty("accountNumber")
	private String account;
	
	@JsonProperty("siteNumber")
	private String site;
	
	private String siteName;
	
	@JsonProperty("siteAddrNumber")
	private String siteAddressNumber;
	
	@JsonProperty("siteAddrName")
	private String siteAddressName;
	
	@JsonProperty("siteSuite#")
	private String siteSuiteNumber;
	
	private String streetType;
	
	@JsonProperty("streetPreDir")
	private String streetPreDirection;
	
	private String siteAddress;
	
	private String siteAddress2;
	
	@JsonProperty("streetDir")
	private String streetDirection;
	
	private String siteCity;
	
	private String siteState;
	
	private String sitePostalCode;

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

	public String getSiteAddressNumber() {
		return siteAddressNumber;
	}

	public void setSiteAddressNumber(String siteAddressNumber) {
		this.siteAddressNumber = siteAddressNumber;
	}

	public String getSiteAddressName() {
		return siteAddressName;
	}

	public void setSiteAddressName(String siteAddressName) {
		this.siteAddressName = siteAddressName;
	}

	public String getSiteSuiteNumber() {
		return siteSuiteNumber;
	}

	public void setSiteSuiteNumber(String siteSuiteNumber) {
		this.siteSuiteNumber = siteSuiteNumber;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreetPreDirection() {
		return streetPreDirection;
	}

	public void setStreetPreDirection(String streetPreDirection) {
		this.streetPreDirection = streetPreDirection;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getSiteAddress2() {
		return siteAddress2;
	}

	public void setSiteAddress2(String siteAddress2) {
		this.siteAddress2 = siteAddress2;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getSiteCity() {
		return siteCity;
	}

	public void setSiteCity(String siteCity) {
		this.siteCity = siteCity;
	}

	public String getSiteState() {
		return siteState;
	}

	public void setSiteState(String siteState) {
		this.siteState = siteState;
	}

	public String getSitePostalCode() {
		return sitePostalCode;
	}

	public void setSitePostalCode(String sitePostalCode) {
		this.sitePostalCode = sitePostalCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
