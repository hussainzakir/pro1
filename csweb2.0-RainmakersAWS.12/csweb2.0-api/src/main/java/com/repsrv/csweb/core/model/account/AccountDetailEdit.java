package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDetailEdit {
	
	@JsonProperty("companyNumber")
	private String company;
	
	@JsonProperty("accountNumber")
	private String account;
	
	private String billingAddressName;
	
	@JsonProperty("address#1")
	private String addressLine1;
	
	@JsonProperty("address#2")
	private String addressLine2;
	
	@JsonProperty("address#3")
	private String addressLine3;
	
	private String city;
	
	private String state;
	
	private String postalCode;
	
	private String telephone;
	
	private String faxNumber;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getBillingAddressName() {
		return billingAddressName;
	}

	public void setBillingAddressName(String billingAddressName) {
		this.billingAddressName = billingAddressName;
	}

}
