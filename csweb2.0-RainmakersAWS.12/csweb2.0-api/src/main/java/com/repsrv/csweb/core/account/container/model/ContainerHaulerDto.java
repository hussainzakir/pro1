package com.repsrv.csweb.core.account.container.model;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.container.ContainerGroupHauler;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;



@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerHaulerDto {


	@JsonProperty("CompanyID")
	private String companyId;
	
	@JsonProperty("AccountID")
	private String accountId;
	
	@JsonProperty("Site")
	private String site;
	
	@JsonProperty("ContainerGp")
	private String containerGroup;
	
	@JsonProperty("Vendor_number")
	private String vendorNumber;
	
	@JsonProperty("Hauler_name")
	private String haulerName;
	
	@JsonProperty("Address_type_1")
	private String addressType1;
	
	@JsonProperty("Address_line_1")
	private String addressLine1;
	
	@JsonProperty("Address_line_2")
	private String addressLine2;
	
	@JsonProperty("Address_line_3")
	private String addressLine3;
	
	@JsonProperty("City")
	private String city;
	
	@JsonProperty("State")
	private String state;
	
	@JsonProperty("Postal_code")
	private String postalCode;
	
	@JsonProperty("Contact_first_name")
	private String contactFirstName;
	
	@JsonProperty("Contact_last_name")
	private String contactLastName;
	
	@JsonProperty("Contact_Description")
	private String contactDescription;
	
	@JsonProperty("Primary_telephone_number")
	private String primaryTelephoneNumber;
	
	@JsonProperty("Primary_extension_number")
	private String primaryExtensionNumber;
	
	@JsonProperty("Primary_FAX_telephone_num")
	private String primaryFaxNumber;
	
	@JsonProperty("Vendor_contact_email_addr")
	private String vendorContactEmail;
	
	@JsonProperty("Address_type_2")
	private String addressType2;
	
	public ContainerHaulerDto () {}

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
	
	
	public String getVendorNumber() {
		return vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	
	public String getHaulerName() {
		return haulerName;
	}

	public void setHaulerName(String haulerName) {
		this.haulerName = haulerName;
	}

	public String getAddressType1() {
		return addressType1;
	}

	public void setAddressType1(String addressType1) {
		this.addressType1 = addressType1;
	}

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

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactDescription() {
		return contactDescription;
	}

	public void setContactDescription(String contactDescription) {
		this.contactDescription = contactDescription;
	}

	public String getPrimaryTelephoneNumber() {
		return primaryTelephoneNumber;
	}

	public void setPrimaryTelephoneNumber(String primaryTelephoneNumber) {
		this.primaryTelephoneNumber = primaryTelephoneNumber;
	}

	public String getPrimaryExtensionNumber() {
		return primaryExtensionNumber;
	}

	public void setPrimaryExtensionNumber(String primaryExtensionNumber) {
		this.primaryExtensionNumber = primaryExtensionNumber;
	}

	public String getPrimaryFaxNumber() {
		return primaryFaxNumber;
	}

	public void setPrimaryFaxNumber(String primaryFaxNumber) {
		this.primaryFaxNumber = primaryFaxNumber;
	}

	public String getVendorContactEmail() {
		return vendorContactEmail;
	}

	public void setVendorContactEmail(String vendorContactEmail) {
		this.vendorContactEmail = vendorContactEmail;
	}

	public String getAddressType2() {
		return addressType2;
	}

	public void setAddressType2(String addressType2) {
		this.addressType2 = addressType2;
	}
	
	public ContainerGroupHauler toGroupHauler() {
		ContainerGroupHauler ghauler = new ContainerGroupHauler();
		BeanUtils.copyProperties(this, ghauler);
		
		return ghauler;
	}

}
