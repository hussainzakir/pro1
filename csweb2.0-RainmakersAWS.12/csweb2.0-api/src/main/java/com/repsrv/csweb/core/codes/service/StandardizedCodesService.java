package com.repsrv.csweb.core.codes.service;

import java.util.List;

import com.repsrv.csweb.core.model.codes.StandardizedCode;
import com.repsrv.csweb.core.model.codes.StandardizedCodeType;

public interface StandardizedCodesService {

	public enum CodeStatus{
		INACTIVE("I"),
		ACTIVE("A");
		private String status;
		private CodeStatus(String status) {this.status = status;}
		public String getCode() {return this.status;}
	}
	
	List<StandardizedCode> getStandardizedCodesByType(String company, StandardizedCodeType type, CodeStatus status);
}
