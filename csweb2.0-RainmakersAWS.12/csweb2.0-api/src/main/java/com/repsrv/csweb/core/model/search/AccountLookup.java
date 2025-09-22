package com.repsrv.csweb.core.model.search;
/************************************************************************
 * Property of Allied Waste Industries, Inc
 * (c) Copyright Allied Waste Industries, Inc. 2007.  All rights reserved.
 *
 *************************************************************************/

import java.io.Serializable;

/**
 * Defines an object to store Account/Site search results.
 * The instance object is bound to a screen component.
 * 
 * @version 1.0
 */
public class AccountLookup implements Serializable {

	private static final long serialVersionUID = 1L;
	private String servicePO;
	private String accountNumber;
	private String account;
	private String accountAddressNumber;
	private String accountAddressName;
	private String siteNumber;
	private String site;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String siteZipCode;
	private String accountCity;
	private String accountState;
	private String accountZipCode;
	private String region;
	private String company;
	private String longCompany;
	private String siteClosedDate;
	private String siteStatusDescription;
	private String nationalAccount;
	private String locationId;

	public String getSiteZipCode() {
		return siteZipCode;
	}
	public void setSiteZipCode(String siteZipCode) {
		this.siteZipCode = siteZipCode;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAccountZipCode() {
		return accountZipCode;
	}
	public void setAccountZipCode(String accountZipCode) {
		this.accountZipCode = accountZipCode;
	}
	public String getAccountCity() {
		return accountCity;
	}
	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}
	public String getAccountState() {
		return accountState;
	}
	public void setAccountState(String accountState) {
		this.accountState = accountState;
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
	public String getSiteStatusDescription(){
		return siteStatusDescription;
	}
	public void setSiteStatusDescription(String statusDescription){
		this.siteStatusDescription = (statusDescription.trim().equals("0")?"":"CLOSED");
	}
	public String getSiteClosedDate() {
		return siteClosedDate;
	}
	public void setSiteClosedDate(String closedDate) {
		this.siteClosedDate = closedDate;
	}
	public String getAccountAddressNumber() {
		return accountAddressNumber;
	}
	public void setAccountAddressNumber(String accountAddressNumber) {
		this.accountAddressNumber = accountAddressNumber;
	}	
	public String getAccountAddressName() {
		return accountAddressName;
	}	
	public void setAccountAddressName(String accountAddressName) {
		this.accountAddressName = accountAddressName;
	}
	public String getSiteAddress() {
		return siteAddress;
	}
	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getServicePO() {
		return servicePO;
	}
	public void setServicePO(String servicePO) {
		this.servicePO = servicePO;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}
	public String getLongCompany() {
		return longCompany;
	}
	public void setLongCompany(String longCompany) {
		this.longCompany = longCompany;
	}
	public String getNationalAccount() {
		return nationalAccount;
	}
	public void setNationalAccount(String nationalAccount) {
		this.nationalAccount = nationalAccount;
	}

}
