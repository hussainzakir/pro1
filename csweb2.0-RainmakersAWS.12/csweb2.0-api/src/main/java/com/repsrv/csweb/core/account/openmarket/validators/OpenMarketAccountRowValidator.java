package com.repsrv.csweb.core.account.openmarket.validators;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.repsrv.csweb.core.model.account.openmarket.OpenMarketAccountInformation;

public class OpenMarketAccountRowValidator {
    private static final String PB_BOX_REG_EX = "^(?:.*?\\b(?:PO BOX|P\\.O BOX|BOX|APO)\\b.*)$";

	public static List<String> validateRow(OpenMarketAccountInformation row) {

        List<String> errors = new ArrayList<>();
		

		String ad2 = row.getAddressLine2();	
		String ad1 = row.getAddressLine1();
		if(StringUtils.isNotBlank(ad1) && ad2.toUpperCase().matches(PB_BOX_REG_EX)) {
			errors.add("Address line 1 must be empty when using PO BOX");
		}

		if(StringUtils.isEmpty(ad1) && !ad2.toUpperCase().matches(PB_BOX_REG_EX)) {
			errors.add("Address line 2 must be a PO BOX address when Address Line 1 is empty");
		}		

		String chargeLateFeeFlag = row.getChargeLateFeeFlag();
		String latePayFeePolicy = row.getLatePayFeePolicy();
		if("Y".equalsIgnoreCase(chargeLateFeeFlag) && isBlank(latePayFeePolicy)) {
			errors.add("Late Pay Fee Policy is required when Charge Late Fee Flag is Y");
		} else if("N".equalsIgnoreCase(chargeLateFeeFlag) && !isBlank(latePayFeePolicy)) {
			errors.add("Late Pay Fee Policy must tbe empty when Charge Late Fee Flag is N");
		}
		
		String invAutoManFlag = row.getInvoiceAutoManualFlag();
		String invoiceDest = row.getInvoiceDestination();
		if("M".equalsIgnoreCase(invAutoManFlag) && StringUtils.isBlank(invoiceDest)) {
			errors.add("Invoice Destination cannot be empty when Invoice Auto/Man is M");
		} else if("A".equalsIgnoreCase(invAutoManFlag) && !StringUtils.isBlank(invoiceDest)) {
			errors.add("Invoice Destination must be empty when Invoice Auto/Man is A");
		}

		String chargeInvFeeFlag = row.getChargeInvoiceFeeFlag();
		String frferfFlag = row.getFrfAndErfOnAdmin();
		if("N".equalsIgnoreCase(chargeInvFeeFlag) && !"N".equalsIgnoreCase(frferfFlag)) {
			errors.add("FRF & ERF flag must be N when Charge Invoice Fee is N");
		}

		return errors;
    }
}
