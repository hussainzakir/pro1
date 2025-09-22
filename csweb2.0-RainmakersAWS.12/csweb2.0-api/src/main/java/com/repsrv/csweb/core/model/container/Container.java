package com.repsrv.csweb.core.model.container;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Container {

	@JsonUnwrapped	
	private ContainerServiceInfo serviceInfo;
	
	@JsonUnwrapped	
	private ContainerRateInfo rateInfo;
	
	@JsonProperty("Container_Type")
	private String containerType;
	
	@JsonProperty("Container_Size")
	private double containerSize;
	
	@JsonProperty("Compactor_Y/N")
	private String compactor;
	
	@JsonProperty("Container_Owned_Y/N")
	private String customerOwned;
	
	@JsonProperty("On-Call_Flag")
	private String onCall;
	
	@JsonProperty("Container_Qty_Order")
	private int quantityOrdered;

	@JsonProperty("Container_Qty_On_Site")
	private int quantityOnSite;
	
	public ContainerServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ContainerServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public ContainerRateInfo getRateInfo() {
		return rateInfo;
	}

	public void setRateInfo(ContainerRateInfo rateInfo) {
		this.rateInfo = rateInfo;
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
		if("C".equalsIgnoreCase(compactor)) {
			this.compactor = "Y";
		}else{
			this.compactor = "N";
		}
	}

	public String getCustomerOwned() {
		return customerOwned;
	}

	public void setCustomerOwned(String customerOwned) {
		this.customerOwned = customerOwned;
	}

	public String getOnCall() {
		return onCall;
	}

	public void setOnCall(String onCall) {
		this.onCall = onCall;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getQuantityOnSite() {
		return quantityOnSite;
	}

	public void setQuantityOnSite(int quantityOnSite) {
		this.quantityOnSite = quantityOnSite;
	}
	
}
