package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountSiteContactEdit {
	
	@JsonProperty("companyNumber")
	private String company;
	
	@JsonProperty("accountNumber")
	private String account;	
	
	@JsonProperty("siteNumber")
	private String site;
	
	private String emailBus1;
	
	private String emailHome1;
	
	private String emailBus2;
	
	private String emailHome2;

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

	public String getEmailBus1() {
		return emailBus1;
	}

	public void setEmailBus1(String emailBus1) {
		this.emailBus1 = emailBus1;
	}

	public String getEmailHome1() {
		return emailHome1;
	}

	public void setEmailHome1(String emailHome1) {
		this.emailHome1 = emailHome1;
	}

	public String getEmailBus2() {
		return emailBus2;
	}

	public void setEmailBus2(String emailBus2) {
		this.emailBus2 = emailBus2;
	}

	public String getEmailHome2() {
		return emailHome2;
	}

	public void setEmailHome2(String emailHome2) {
		this.emailHome2 = emailHome2;
	}

}

