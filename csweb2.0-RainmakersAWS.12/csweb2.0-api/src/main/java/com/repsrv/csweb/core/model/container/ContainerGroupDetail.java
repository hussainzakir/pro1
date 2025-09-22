package com.repsrv.csweb.core.model.container;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.repsrv.csweb.core.model.json.transformer.ContainerAccountTypeTransformer;
import com.repsrv.csweb.core.util.AS400DateUtil;
import com.repsrv.csweb.core.util.ContainerAccountType;

import flexjson.JSON;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerGroupDetail {

	@JsonUnwrapped	
	private Container container;
	
	@JSON( transformer = ContainerAccountTypeTransformer.class )
	private ContainerAccountType accountType;
	
	@JsonProperty("Municipal/Franchise_Contr")
	private String muniFranchiseContract;
	
	@JsonProperty("Original_Start_Date")
	private String originalStartDate;
	
	@JsonProperty("Contract_Group_Number")
	private String contractGroupNumber;
	
	@JsonProperty("Last_Service_Date")
	private String lastServiceChangeDate;

	@JsonProperty("Franchise_Flag")
	private String franchiseFlag;

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public ContainerAccountType getAccountType() {
		return accountType;
	}

	@JsonProperty("Account_Type")
	public void setAccountType(String accountType) {	
		this.accountType = ContainerAccountType.stringToEnum(accountType);
	}

	public String getMuniFranchiseContract() {
		return muniFranchiseContract;
	}

	public void setMuniFranchiseContract(String muniFranchiseContract) {
		this.muniFranchiseContract = muniFranchiseContract;
	}

	public String getOriginalStartDate() {
		return originalStartDate;
	}

	public void setOriginalStartDate(String originalStartDate) {
		this.originalStartDate = AS400DateUtil.format8DigitDate(originalStartDate);
	}

	public String getContractGroupNumber() {
		return contractGroupNumber;
	}

	public void setContractGroupNumber(String contractGroupNumber) {
		this.contractGroupNumber = contractGroupNumber;
	}

	public String getLastServiceChangeDate() {
		return lastServiceChangeDate;
	}

	public void setLastServiceChangeDate(String lastServiceChangeDate) {
		this.lastServiceChangeDate = AS400DateUtil.format8DigitDate(lastServiceChangeDate);
	}
	
	public String getFranchiseFlag() {
		return franchiseFlag;
	}

	public void setFranchiseFlag(String franchiseFlag) {
		this.franchiseFlag = franchiseFlag;
	}
}
