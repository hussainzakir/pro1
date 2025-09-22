package com.repsrv.csweb.core.account.onboarding.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.repsrv.csweb.core.model.account.onboarding.AobAccountInformation;

public class AobAccountRowValidator {
    
    public static final String ADDL1_REQ = "Address line 1 required";
    public static final String INV_EXC_CODE_EMPTY = "Invoice Excempt Code cannot be empty when Charge Invoice Fee Flag is N";
    public static final String LATE_FEE_EXC_CODE_EMPTY = "Late Fee Exempt Code cannot be empty when Charge Late Fee Flag is N";
    public static final String SERV_INT_EXC_CODE_REQ = "Service Int Excempt Code cannot be empty when Charge Svc Interrupt Fee Flag is N";
    public static final String LATE_PAY_FEE_POL_EMPTY = "Late Pay Fee Policy must be blank when Charge Late Fee Flag is N";
    public static final String FUEL_FEE_EXC_CODE_REQ = "Fuel Fee Excempt Code is required when Charge Fuel Fee flag is N";
    public static final String ENV_FEE_EXC_CODE_REQ = "Env Fee Exempt Code is required when Charge Env Fee flag is N";
    public static final String FRF_ERF_FLAG_REQ = "FRF & ERF flag must be N when Charge Invoice Fee is N";
    public static final String INV_DEST_REQ = "Invoice Destination cannot be empty when Invoice Auto/Man is M";
    public static final String LATE_PAY_FEE_POL_REQ = "Late Pay Fee Policy is required when Charge Late Fee Flag is Y";
    public static final String SERV_INT_EXC_CODE_EMPTY = "Service Int Exempt Code must be empty when Charge Svc Interrupt Fee Flag is Y";
	private static final String ACCT_GRP_CODE = "Account Group Code must be 2 when Cbs Location Id and Cbs Number are populated";
	private static final String ACCT_OPEN_DATE = "Account Open Date can not be older than 90 days from today.";

	public static List<String> validateRow(AobAccountInformation row) {
		List<String> errors = new ArrayList<>();
		
		String ad2 = row.getAddressLine2();
		String ad1 = row.getAddressLine1();
		if (StringUtils.isNotBlank(ad2)) {
    		String upperAd2 = ad2.toUpperCase();
    		if (upperAd2.startsWith("PO BOX") || upperAd2.startsWith("P.O BOX")) {
        		row.setAddressLine1("");
    		} else if (StringUtils.isBlank(ad1)) {
        		errors.add(ADDL1_REQ);
    		}
		}
	
		String cbsLocId = row.getCbsLocationId();
		String cbsNumb = row.getNationalAccountNumber();
		String acctGrpCode = row.getAccountGroupCode();
		if(!StringUtils.isAnyBlank(cbsLocId, cbsNumb)) {
			if(acctGrpCode != null && !StringUtils.equals(acctGrpCode.trim(), "2")){
				errors.add(ACCT_GRP_CODE);
			}
		}		
		
		String invAutoManFlag = row.getInvoiceAutoManualFlag();
		String invoiceDest = row.getInvoiceDestination();
		if("M".equalsIgnoreCase(invAutoManFlag) && StringUtils.isBlank(invoiceDest)) {
			errors.add(INV_DEST_REQ);
		// } else if("A".equalsIgnoreCase(invAutoManFlag)) {
		// 	row.setInvoiceDestination("");
		}

		String accountStartDate = row.getAccountOpenDate();
		isValidDate(accountStartDate, errors);

        return errors;
    }

	 public static void isValidDate(String dateStr, List<String> errors) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return; 
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false); // Prevents invalid dates like 20211332

        try {
            // Parse the input date string into a Date object
            Date inputDate = sdf.parse(dateStr);
            
            // Get the current date
            Date currentDate = new Date();
            
            // Create a Calendar instance and set it to 90 days ago
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_YEAR, -90);
            Date ninetyDaysAgo = calendar.getTime();

            // Check if the input date is older than 90 days
            if (inputDate.before(ninetyDaysAgo)) {
				errors.add(ACCT_OPEN_DATE);
			}
        } catch (ParseException e) {
            // If parsing fails, the date is invalid
            errors.add("Invalid Account Open Date format: " + dateStr);
        }
	}
}
