package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountSiteContact {
	
	@JsonProperty("companyNumber")
	private String company;
	
	@JsonProperty("accountNumber")
	private String account;
	
	@JsonProperty("siteNumber")
	private String site;
	
	private String businessEmail1;
	
	private String homeEmail1;
	
	private String businessEmail2;
	
	private String homeEmail2;
	
	private String phoneNumber;
	
	private String phoneType;
	
	public AccountSiteContact() {}
	
	public String getBusinessEmail1() {
		return businessEmail1;
	}
	public void setBusinessEmail1(String businessEmail1) {
		this.businessEmail1 = businessEmail1;
	}
	public String getHomeEmail1() {
		return homeEmail1;
	}
	public void setHomeEmail1(String homeEmail1) {
		this.homeEmail1 = homeEmail1;
	}
	public String getBusinessEmail2() {
		return businessEmail2;
	}
	public void setBusinessEmail2(String businessEmail2) {
		this.businessEmail2 = businessEmail2;
	}
	public String getHomeEmail2() {
		return homeEmail2;
	}
	public void setHomeEmail2(String homeEmail2) {
		this.homeEmail2 = homeEmail2;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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

}
