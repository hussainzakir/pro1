package com.repsrv.csweb.core.account.imports.validators.resicontract;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.SiteInformation;

public class AccountRowValidator {

	public static List<String> validateRow(AccountInformation row) {
		List<String> errors = new ArrayList<>();
		
		String ad2 = row.getAddressLine2();
		String ad1 = row.getAddressLine1();
		if (StringUtils.isNotBlank(ad2)) {
			String upperAd2 = ad2.toUpperCase();
			if (upperAd2.startsWith("PO BOX") || upperAd2.startsWith("P.O BOX")) {
				row.setAddressLine1("");
			} else if (StringUtils.isBlank(ad1)) {
				errors.add("Address line 1 required");
			}
		}
		
		String invExcemptCode = row.getInvoiceFeeExemptCode();
		String chargeInvFeeFlag = row.getChargeInvoiceFeeFlag();
		if("N".equalsIgnoreCase(chargeInvFeeFlag.trim()) && isBlank(invExcemptCode)) {
			errors.add("Invoice Excempt Code cannot be empty when Charge Invoice Fee Flag is N");
		} else if("Y".equalsIgnoreCase(chargeInvFeeFlag.trim())) {
			row.setInvoiceFeeExemptCode("");
		}
		
		String svcIntExemptCode = row.getServiceIntExemptCode();
		String chargeSvcIntFeeFlag = row.getChargeServiceInterruptFeeFlag();
		if("N".equalsIgnoreCase(chargeSvcIntFeeFlag) && isBlank(svcIntExemptCode)) {
			errors.add("Service Int Exempt Code cannot be empty when Charge Svc Interrupt Fee Flag is N");
		} else if("Y".equalsIgnoreCase(chargeSvcIntFeeFlag) && !isBlank(svcIntExemptCode)) {
			errors.add("Service Int Exempt Code must be empty when Charge Svc Interrupt Fee Flag is Y");
		}
		
		
		String chargeLateFeeFlag = row.getChargeLateFeeFlag();
		String lateFeeExemptCode = row.getLateFeeExemptCode();
		if("N".equalsIgnoreCase(chargeLateFeeFlag) && isBlank(lateFeeExemptCode)) {
			errors.add("Late Fee Exempt Code cannot be empty when Charge Late Fee Flag is N");
		} else if("Y".equalsIgnoreCase(chargeLateFeeFlag)) {
			row.setLateFeeExemptCode("");
		}
		
		String latePayFeePolicy = row.getLatePayFeePolicy();
		if("Y".equalsIgnoreCase(chargeLateFeeFlag) && isBlank(latePayFeePolicy)) {
			errors.add("Late Pay Fee Policy is required when Charge Late Fee Flag is Y");
		} else if("N".equalsIgnoreCase(chargeLateFeeFlag) && !isBlank(latePayFeePolicy)) {
			errors.add("Late Pay Fee Policy must tbe blank when Charge Late Fee Flag is N");
		}
		
		String chargeFuelFeeFlag = row.getChargeFuelFeeFlag();
		String fuelFeeExcemptCode = row.getFuelFeeExemptCode();
		if("N".equalsIgnoreCase(chargeFuelFeeFlag) && StringUtils.isBlank(fuelFeeExcemptCode)) {
			errors.add("Fuel Fee Excempt Code is required when Charge Fuel Fee flag is N");
		} else if("Y".equalsIgnoreCase(chargeFuelFeeFlag)) {
			row.setFuelFeeExemptCode("");
		}
		
		String chgEnvFee = row.getChargeEnvironmentalFee();
		String envFeeExemptCode = row.getEnvironmentFeeExemptCode();
		if("N".equalsIgnoreCase(chgEnvFee) && StringUtils.isBlank(envFeeExemptCode)) {
			errors.add("Env Fee Exempt Code is required when Charge Env Fee flag is N");
		} else if("Y".equalsIgnoreCase(chgEnvFee)) {
			row.setEnvironmentFeeExemptCode("");
		}
		
		
		//chargeInvFeeFlag = IFIFEE
		String frferfFlag = row.getFrfAndErfOnAdmin();
		if("N".equalsIgnoreCase(chargeInvFeeFlag) && !"N".equalsIgnoreCase(frferfFlag)) {
			errors.add("FRF & ERF flag must be N when Charge Invoice Fee is N");
		}
		
		String invAutoManFlag = row.getInvoiceAutoManualFlag();
		String invoiceDest = row.getInvoiceDestination();
		if("M".equalsIgnoreCase(invAutoManFlag) && StringUtils.isBlank(invoiceDest)) {
			errors.add("Invoice Destination cannot be empty when Invoice Auto/Man is M");
		} else if("A".equalsIgnoreCase(invAutoManFlag)) {
			row.setInvoiceDestination("");
		}
		
		siteNumberValidator(row, errors);
		return errors;
	}

	private static void siteNumberValidator(AccountInformation row, List<String> errors) {
		List <SiteInformation> sitios = row.getSites(); 
		if(sitios != null) {
			Map<String, List<SiteInformation>> siteGroups = sitios.stream().collect(Collectors.groupingBy(SiteInformation::getAccountNumber));
			siteGroups.entrySet().stream().map(entrySet -> entrySet.getValue()).forEach(siteGroup -> {
				int size = siteGroup.size();
				if (size == 1){
					if (!siteGroup.get(0).getSiteNumber().equals("1")){
						errors.add("Associated Site must have an id of 1");
					}
				} else {
					int max = siteGroup.stream().map(SiteInformation::getSiteNumber).map(Integer::valueOf).max(Integer::compare).get();
					for (int i = 1; i < max; i++){
						Integer index = Integer.valueOf(i);
						boolean validateSiteNumber = siteGroup.stream().map(SiteInformation::getSiteNumber).map(Integer::valueOf).anyMatch(siteNumber -> siteNumber.equals(index));
						if (!validateSiteNumber) {
							errors.add("Associated Sites must be sequential starting at 1");
						break;
						}

					}
				}
			});
		}
	}
	
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static void formatValues(AccountInformation row) throws IllegalArgumentException, IllegalAccessException {
		row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		
		
	    
		
	}
	
}
