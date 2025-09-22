package com.repsrv.csweb.core.account.onboarding.validators;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.onboarding.AobSiteInformation;

public class AobSiteRowValidator extends BaseValidator {
	
	public static final String SITE_ACCOUNT_DATE_GREATER = "Site Start Date must be greater than or equal to Account Open Date";
	private static Logger logger = Logger.getLogger(AobSiteRowValidator.class.getName());

	public static List<String> validateRow(AobSiteInformation row) {
		List<String> errors = new ArrayList<>();

//site start date against account open date validation

			String siteEffDate = row.getStartDate();
			String accountEffDate = row.getAobAccountInformation() != null ? row.getAobAccountInformation().getAccountOpenDate() : null;
			logger.info("Account Effective Date: " + accountEffDate);
			logger.info("Site Effective Date: " + siteEffDate);
			if( siteEffDate != null && accountEffDate != null) {
				try {
					Integer siteEffDte = Integer.parseInt(siteEffDate);
					Integer accountEffDte = Integer.parseInt(accountEffDate);
					if(siteEffDte < accountEffDte) {
						errors.add(SITE_ACCOUNT_DATE_GREATER);	
					} 
				} catch(NumberFormatException nfe) {
					errors.add("Invalid Site Start Date: " + siteEffDate + " or invalid Account Open Date: " + accountEffDate);
				}
			}

		formatAOBValues(row);
		return errors;
}
 
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 */
	private static void formatAOBValues(AobSiteInformation row) {
		if(row.getCompanyNumber() != null) {
			row.setCompanyNumber(leftPad(row.getCompanyNumber(), 3, "0"));
		}
		if(row.getAccountNumber() != null) {
			row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		}
		if(row.getSiteNumber() != null) {
			row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		}
	}
}

