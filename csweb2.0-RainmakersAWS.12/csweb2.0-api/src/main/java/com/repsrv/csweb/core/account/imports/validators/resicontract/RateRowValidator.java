package com.repsrv.csweb.core.account.imports.validators.resicontract;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;

import com.repsrv.csweb.core.model.account.imports.RateInformation;

public class RateRowValidator {

	public static List<String> validateRow(RateInformation row) {
		List<String> errors = new ArrayList<>();
		
		//formatValues(row);
		return errors;
	}
	
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 */
	private static void formatValues(RateInformation row) {
		row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		//row.setContainerGroup(leftPad(row.getContainerGroup(), 2, "0"));
		
	}


}
