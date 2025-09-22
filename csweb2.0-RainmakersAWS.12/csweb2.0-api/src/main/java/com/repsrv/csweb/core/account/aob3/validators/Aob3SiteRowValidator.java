package com.repsrv.csweb.core.account.aob3.validators;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.aob3.Aob3SiteInfo;

public class Aob3SiteRowValidator extends BaseValidator {
	
	public static final String SITE_ACCOUNT_DATE_GREATER = "Site Start Date must be greater than or equal to Account Open Date";
		private static final Logger logger = LoggerFactory.getLogger(Aob3SiteRowValidator.class.getName());

	public static List<String> validateRow(Aob3SiteInfo row) {
		List<String> errors = new ArrayList<>();

//site start date against account open date validation

			String siteEffDate = row.getStartDate();
			String accountEffDate = row.getAob3AccountInfo() != null ? row.getAob3AccountInfo().getAccountOpenDate() : null;
			logger.debug("Account Effective Date: " + accountEffDate);
			logger.debug("Site Effective Date: " + siteEffDate);
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
	private static void formatAOBValues(Aob3SiteInfo row) {
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
