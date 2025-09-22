package com.repsrv.csweb.core.account.servicerecordings.model;

import org.apache.commons.lang3.StringUtils;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

public class MpuDownRequest extends StoredProcCallResult {

	private String downFlag;
	private String company;
	private String serviceCode;
	
	public MpuDownRequest(String company, String serviceCode) {
		this.company = company;
		this.serviceCode = StringUtils.substring(serviceCode, 0, 2);
	}

	public String getDownFlag() {
		return downFlag;
	}

	public void setDownFlag(String downFlag) {
		this.downFlag = downFlag;
	}

	public String getCompany() {
		return company;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}

	public boolean isDownFlagSet() {
		if(StringUtils.isBlank(this.getDownFlag()))
			return false;
		else
			return "Y".equalsIgnoreCase(this.getDownFlag().trim());
	}
	
}
