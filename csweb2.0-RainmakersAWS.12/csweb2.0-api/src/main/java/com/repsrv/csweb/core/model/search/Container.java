package com.repsrv.csweb.core.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.transformer.ContainerAccountTypeTransformer;
import com.repsrv.csweb.core.util.ContainerAccountType;

import flexjson.JSON;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {

	private String containerId;
	
	@JSON( transformer = ContainerAccountTypeTransformer.class )
	private ContainerAccountType accountType;
	
	private String containerType;
	
	private double containerSize;
	
	private String compactor;
	
	private int qtyOrder;
	
	private String onCall;
	
	private int closeDate;
	
	private String containerStatus;
	
	private String specialHandling;
	
	private String specialHandlingDescription;
	
	private String customerOwned;
	
	@JsonProperty("PORequired")
	private String poRequired;
	
	private String poReqdDescription;
	
	private String onCallDescription;
	
	private int billingFrequency;
	
	private String revenueDistribution;
	
	private String sharedContainerID;
	
	private int contractGroup;
	
	private String contractNumber;
	
	private int orginalCreateDate;
	
	private int startDate;
	
	public Container() {}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public ContainerAccountType getAccountType() {
		return accountType;
	}

	@JsonProperty("accountType")
	public void setAccountType(String accountType) {
		this.accountType = ContainerAccountType.stringToEnum(accountType);
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public double getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(double containerSize) {
		this.containerSize = containerSize;
	}

	public String getCompactor() {
		return compactor;
	}

	public void setCompactor(String compactor) {
		this.compactor = compactor;
	}

	public int getQtyOrder() {
		return qtyOrder;
	}

	public void setQtyOrder(int qtyOrder) {
		this.qtyOrder = qtyOrder;
	}

	public String getOnCall() {
		return onCall;
	}

	public void setOnCall(String onCall) {
		this.onCall = onCall;
	}

	public int getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(int closeDate) {
		this.closeDate = closeDate;
	}

	public String getContainerStatus() {
		return containerStatus;
	}

	public void setContainerStatus(String containerStatus) {
		this.containerStatus = containerStatus;
	}

	public String getSpecialHandling() {
		return specialHandling;
	}

	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}

	public String getSpecialHandlingDescription() {
		return specialHandlingDescription;
	}

	public void setSpecialHandlingDescription(String specialHandlingDescription) {
		this.specialHandlingDescription = specialHandlingDescription;
	}

	public String getCustomerOwned() {
		return customerOwned;
	}

	public void setCustomerOwned(String customerOwned) {
		this.customerOwned = customerOwned;
	}

	public String getPoRequired() {
		return poRequired;
	}

	public void setPoRequired(String poRequired) {
		this.poRequired = poRequired;
	}

	public String getPoReqdDescription() {
		return poReqdDescription;
	}

	public void setPoReqdDescription(String poReqdDescription) {
		this.poReqdDescription = poReqdDescription;
	}

	public String getOnCallDescription() {
		return onCallDescription;
	}

	public void setOnCallDescription(String onCallDescription) {
		this.onCallDescription = onCallDescription;
	}

	public int getBillingFrequency() {
		return billingFrequency;
	}

	public void setBillingFrequency(int billingFrequency) {
		this.billingFrequency = billingFrequency;
	}

	public String getRevenueDistribution() {
		return revenueDistribution;
	}

	public void setRevenueDistribution(String revenueDistribution) {
		this.revenueDistribution = revenueDistribution;
	}

	public String getSharedContainerID() {
		return sharedContainerID;
	}

	public void setSharedContainerID(String sharedContainerID) {
		this.sharedContainerID = sharedContainerID;
	}

	public int getContractGroup() {
		return contractGroup;
	}

	public void setContractGroup(int contractGroup) {
		this.contractGroup = contractGroup;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public int getOrginalCreateDate() {
		return orginalCreateDate;
	}

	public void setOrginalCreateDate(int orginalCreateDate) {
		this.orginalCreateDate = orginalCreateDate;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	
	
}
