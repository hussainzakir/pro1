package com.repsrv.csweb.core.account.imports.validators.resicontract;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.imports.ContainerInformation;
import com.repsrv.csweb.core.model.account.imports.RouteInformation;

public class ContainerRowValidator extends BaseValidator{

	public static List<String> validateRow(ContainerInformation row) {
		List<String> errors = new ArrayList<>();
		
		String contIdCode = row.getContainerIdCode();
		String hoaAssCode = row.getHoaAssociationCode();
		if("H".equalsIgnoreCase(contIdCode) && isBlank(hoaAssCode)) {
			errors.add("HOA Assoc. Code is required when Conatiner ID Code is H");
		} else if("O".equalsIgnoreCase(contIdCode) || "F".equalsIgnoreCase(contIdCode)) {
			row.setHoaAssociationCode("");
		}
		
		String recMonthBill = row.getRecurMonthsAdvBill();
		if(!"0".equals(recMonthBill) && !"1".equals(recMonthBill)) {
			errors.add("Recur Months Adv. Bill can only be 0 or 1");
		}
		
		String billToDate = row.getBilledToDate();
		String nextFullChgDt = row.getNextFullchargeDate();
		if((isNotBlank(billToDate) && !isAllChars("0", billToDate)) 
				&& (isBlank(nextFullChgDt) || isAllChars("0", nextFullChgDt))) {
			errors.add("Next Full Charge date is required if Bill Date is not empty");
		} else if(isBlank(billToDate) || isAllChars("0", billToDate)) {
			row.setNextFullchargeDate(row.getBilledToDate());
		}

		
		if("Y".equalsIgnoreCase(row.getContainerNotesFlag())
				&& StringUtils.isAllBlank(row.getNote1(), row.getNote2(), row.getNote3(), row.getNote4(), row.getNote5())) {
			errors.add("Container Notes flag is 'Y' but no notes found in note fields 1-5");
		} else if("N".equalsIgnoreCase(row.getContainerNotesFlag())
				&& !StringUtils.isAllBlank(row.getNote1(), row.getNote2(), row.getNote3(), row.getNote4(), row.getNote5())) {
			errors.add("Container Notes flag is 'N' but notes found in one or more note fields 1-5");
		}
			
		
		//formatValues(row);
		return errors;
	}
	
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 */
	private static void formatValues(ContainerInformation row) {
		row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		
		row.setContainerNotesFlag(row.hasNotes() ? "Y" : "");
		row.setSalesTransCode(leftPad(row.getSalesTransCode(), 2, "0"));
		row.setSalesTransReasonCode(leftPad(row.getSalesTransReasonCode(), 2, "0"));
		
	}
	
}
