package com.repsrv.csweb.core.model.search;

public class AccountSearchRequest {
	
	private String division;
	private String locationId;
	private String acctNum;
	private String site;
	private String accountSiteName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	private String phone;
	//TO DO: Verify variables purchaseOrderNum, accountEmail, openObligationBalance.
	//Remain the names when added to search SP
	// private String purchaseOrderNum;
	// private String accountEmail;
	// private String openObligationBalance;
	private String includeClosedSites;
	private String nationalAccountsOnly;
	private String orderBy;
	private String userId;
	private String returnJson;
	private int rows;
	private String changeRequired;
	private String sqlCode;
	private String sqlState;

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAccountSiteName() {
		return accountSiteName;
	}

	public void setAccountSiteName(String accountSiteName) {
		this.accountSiteName = accountSiteName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIncludeClosedSites() {
		return includeClosedSites;
	}

	public void setIncludeClosedSites(String includeClosedSites) {
		this.includeClosedSites = includeClosedSites;
	}

	public String getNationalAccountsOnly() {
		return nationalAccountsOnly;
	}

	public void setNationalAccountsOnly(String nationalAccountsOnly) {
		this.nationalAccountsOnly = nationalAccountsOnly;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(String returnJson) {
		this.returnJson = returnJson;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getChangeRequired() {
		return changeRequired;
	}

	public void setChangeRequired(String changeRequired) {
		this.changeRequired = changeRequired;
	}

	public String getSqlCode() {
		return sqlCode;
	}

	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	public String getSqlState() {
		return sqlState;
	}

	public void setSqlState(String sqlState) {
		this.sqlState = sqlState;
	}
	
}
