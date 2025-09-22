package com.repsrv.csweb.core.account.imports.validators.resicontract;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.repsrv.csweb.core.model.account.imports.RouteInformation;

public class RouteRowValidator {

	public static List<String> validateRow(RouteInformation row) {
		List<String> errors = new ArrayList<>();
		
		if(row.getContainer() != null) {
			try {
				Integer periodLen = Integer.parseInt(row.getContainer().getPeriodLength());
				if(periodLen > 1 && 
						(StringUtils.isBlank(row.getFirstServiceDate()) || row.getFirstServiceDate().equals("0"))){
						errors.add("First Service Date required when Container period length > 1 ");
					}
			} catch(NumberFormatException nfe){
				errors.add("Invalid Period Length in associated container: " + row.getContainer().getPeriodLength());
			}
		}
		
		return errors;
	}
	
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 */
	private static void formatValues(RouteInformation row) {
		row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		//row.setContainerGroup(leftPad(row.getContainerGroup(), 2, "0"));
		
		
	}

}
