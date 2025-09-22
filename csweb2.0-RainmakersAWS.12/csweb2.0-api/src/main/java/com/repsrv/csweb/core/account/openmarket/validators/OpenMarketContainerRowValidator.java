package com.repsrv.csweb.core.account.openmarket.validators;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketContainerInformation;

public class OpenMarketContainerRowValidator extends BaseValidator{
    
	public static List<String> validateRow(OpenMarketContainerInformation row) {
		List<String> errors = new ArrayList<>();
	
		String billToDate = row.getBilledToDate();
		String nextFullChgDt = row.getNextFullchargeDate();
		if ((isNotBlank(billToDate) && !isAllChars("0", billToDate)) 
				&& (isBlank(nextFullChgDt) || isAllChars("0", nextFullChgDt))) {
			errors.add("Next Full Charge date is required if Bill Date is not empty");
		} else if (isBlank(billToDate) || isAllChars("0", billToDate)) {
			row.setNextFullchargeDate(row.getBilledToDate());
		}
	
		String contEffDate = row.getContainerStartDate();
		String siteEffDate = row.getOpenMarketSiteInformation().getStartDate();
		if (contEffDate != null && siteEffDate != null) {
			try {
				if (StringUtils.isNumeric(contEffDate) && StringUtils.isNumeric(siteEffDate)) {
					Integer contEffDte = Integer.parseInt(contEffDate);
					Integer siteEffDte = Integer.parseInt(siteEffDate);
					if (contEffDte < siteEffDte) {
						errors.add("Container Start Date must be greater than or equal to Site Start Date");
					}
				} else {
					errors.add("Container Start Date or Site Start Date contains non-numeric characters");
				}
			} catch (NumberFormatException nfe) {
				errors.add("Invalid Container Start Date: " + contEffDate + " or invalid Site Start Date: " + siteEffDate);
			}
		}
	
		return errors;
	}
}
