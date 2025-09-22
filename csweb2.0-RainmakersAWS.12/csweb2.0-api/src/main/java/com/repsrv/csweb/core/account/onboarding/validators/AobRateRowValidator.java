package com.repsrv.csweb.core.account.onboarding.validators;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.onboarding.AobRateInformation;

public class AobRateRowValidator extends BaseValidator{
	public static final String CHRG_ERR_DSP_OVR = "Charge Type and Charge Method Should be S and W if Charge Code is 'DSP' or 'OVR'";
	public static final String CHRG_ERR_NOT_DSP_OVR = "Charge Method Should be W if Charge Code is not 'DSP' or 'OVR'";
	public static final String WST_MTRL_UNT_MSR_ERR = "Both Waste Material and Unit of Measure must either be empty or contain some value";
	public static final String COMP_MISMATCH = "AOB Rate Company Number is not matching with Base Container Company Number";
	public static final String ACCT_MISMATCH = "AOB Rate Account Number is not matching with Base Container Account Number";
	public static final String SITE_SEQ_MISMATCH = "AOB Rate Site Sequence Number is not matching with Base Container Site Sequence Number";
	public static final String CONT_GRP_MISMATCH = "AOB Rate Container Group Number is not matching with Base Container Container Group Number";
	public static final String RATE_CONT_DATE_GREATER = "Rate Effective Date must be greater than or equal to Container Start Date";
	public static final String RATE_SITE_DATE_GREATER = "Rate Effective Date must be greater than or equal to Site Start Date";
	public static final String WST_ALLWD_FOR_DSP_CODE = "Waste Material Type can only be added when Charge Code is 'DSP'";
	public static final Logger logger = Logger.getLogger(AobRateRowValidator.class.getName());

	public static List<String> validateRow(AobRateInformation row) {
		List<String> errors = new ArrayList<>();
		
		if(row.getCompanyNumber() != null && row.getAobContainerInformation() != null && 
		   !row.getCompanyNumber().equals(row.getAobContainerInformation().getCompanyNumber())) {
			errors.add(COMP_MISMATCH);
		}
		
		if(row.getAccountNumber() != null && row.getAobContainerInformation() != null && 
		   !row.getAccountNumber().equals(row.getAobContainerInformation().getAccountNumber())) {
			errors.add(ACCT_MISMATCH);
		}
		
		if(row.getSiteNumber() != null && row.getAobContainerInformation() != null && 
		   !row.getSiteNumber().equals(row.getAobContainerInformation().getSiteNumber())) {
			errors.add(SITE_SEQ_MISMATCH);
		}
		
		if(row.getContainerGroup() != null && row.getAobContainerInformation() != null && 
		   !row.getContainerGroup().equals(row.getAobContainerInformation().getContainerGroupNumber())) {
			errors.add(CONT_GRP_MISMATCH);
		}
		if(row.getChargeCode() != null && (row.getChargeCode().equals("DSP") || row.getChargeCode().equals("OVR"))) {
			if(row.getChargeType() != null && row.getChargeMethod() != null && 
			   !(row.getChargeType().equals("S") && row.getChargeMethod().equals("W"))) {
				errors.add(CHRG_ERR_DSP_OVR);
			}
		}		
		
		if (row.getChargeCode() != null && row.getChargeCode().equals("DSP")) {
			if (row.getWasteMaterialType() != null && row.getWasteMaterialType().equals("")) {
				if (row.getUnitOfMeasure() != null && !row.getUnitOfMeasure().equals("")) {
					errors.add(WST_MTRL_UNT_MSR_ERR);
				}
			}

			if (row.getWasteMaterialType() != null && !row.getWasteMaterialType().equals("")) {
				if (row.getUnitOfMeasure() == null || row.getUnitOfMeasure().equals("")) {
					errors.add(WST_MTRL_UNT_MSR_ERR);
				}
			}
		}

		if (row.getChargeCode() != null && !row.getChargeCode().equals("DSP")) {
            if (row.getWasteMaterialType() != null && !row.getWasteMaterialType().equals("")) {
    			errors.add(WST_ALLWD_FOR_DSP_CODE);
            }
        }
		
		String rateEffDate = row.getEffectiveDate();
		String contEffDate = row.getAobContainerInformation() != null ? row.getAobContainerInformation().getContainerStartDate() : null;
		String siteEffDate = row.getAobSiteInformation() != null ? row.getAobSiteInformation().getStartDate() : null;
		if(row.getEffectiveDate() != null) {
			try {
				Integer rateEffDte = Integer.parseInt(rateEffDate);
				if(contEffDate != null) {
					Integer contEffDte = Integer.parseInt(contEffDate);
					if(rateEffDte < contEffDte) {
						errors.add(RATE_CONT_DATE_GREATER);	
					}
				}
			} catch(NumberFormatException nfe) {
				errors.add("Invalid Rate Effective Date: " + row.getEffectiveDate() + " or invalid Container Effective Date: " + contEffDate);
			}
			try {
				Integer rateEffDte = Integer.parseInt(rateEffDate);
				if(siteEffDate != null) {
					Integer siteEffDte = Integer.parseInt(siteEffDate);
					if(rateEffDte < siteEffDte) {
						errors.add(RATE_SITE_DATE_GREATER);
					}
				}
			} catch(NumberFormatException nfe) {
				errors.add("Invalid Rate Effective Date: " + row.getEffectiveDate() + " or invalid Site Start Date: " + siteEffDate);
			}
		}
			
		formatValues(row);
		return errors;
	}
	
	private static void formatValues(AobRateInformation row) {
		if(row.getCompanyNumber() != null) {
			row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		}
		if(row.getAccountNumber() != null) {
			row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		}
		if(row.getSiteNumber() != null) {
			row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		}
	}

}
