package com.repsrv.csweb.core.account.aob3.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.aob3.Aob3ContainerInfo;
import com.repsrv.csweb.core.model.account.aob3.Aob3RateInfo;

import static org.apache.commons.lang.StringUtils.isBlank;

public class Aob3ContainerRowValidator extends BaseValidator{
	
	public static final String CONT_GRP_BLANK = "Contract Group Number cannot be blank when Muni Fran Group Number is not blank";
	public static final String MUNI_FRAN_BLANK = "Muni Fran Group Number cannot be blank when Contract Group Number is not blank";
	public static final String HOA_CODE_REQ = "HOA Assoc. Code is required when Conatiner ID Code is H";
	public static final String ONCALL_FLAG_YES = "Minimum Number of lifts should be more than zero and less than 999 when On Call Flag is Y";
	public static final String ONCALL_FLAG_NO = "Total number lifts should be greater than zero when On Call Flag is N";
	public static final String ONCALL_FLAG_NO_MIN = "Minimun number of lifts must be zero when On Call Flag is N";
	public static final String WEIGHT_LMT_NEEDED_INFO = "Container Weight Limit is populated, rate Charge Code should be OVR and Rate Charge Amount should be greater than zero";
	public static final String DELIV_PO_NUM_REQ = "Delivery PO Number is required when create Delivery UR is Y and container PO Required is Y";
	public static final String UR_EFFECT_DATE_REQ = "UR Effective Date is required when Create Delivery UR is Y";
	public static final String UR_PLAN_DATE_GREATER_PERM = "Create Delivery UR is 'Y' UR Plan Date must be equal or greater than Schedule Perm Service Date";
	public static final String UR_PLAN_DATE_GREATER_CONT = "Create Delivery UR is 'Y' UR Plan Date must be equal or greater than Container Start Date";
	public static final String UR_PLAN_DATE_REQ = "UR Plan Date is Required when Create Delivery UR is 'Y'";
	public static final String SCHE_PERM_SERV_REQ = "Schedule Perm Service must be N when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_DATE_BLANK = "Schedule Perm Service Date must be blank when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_DATE_GREATER = "Create Delivery UR is 'Y' Schedule Perm Service Date must be equal or greater than Container Start Date";
	public static final String SCHE_PERM_SERV_PLAN_DATE_BLANK = "Schedule Perm Service Plan Date must be blank when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_PLAN_DATE_GREATER = "Create Delivery UR is 'Y' Schedule Perm Service Plan Date must be equal or greater than Container Start Date";
	public static final String CREATE_DEL_UR_N = "If Create Delivery UR is 'N' then UR Plan Date and UR Effective Date must be blank";
	public static final String CONT_SITE_DATE_GREATER = "Containter Start Date must be greater than or equal to Site Start Date";
	public static final String UOM_REQ = "UOM is required when Container Type is RO, IR, SC, or TL";
	public static final String MINTOTAL_LIFTS = "Minimun and total number of lifts can not be blank and accept only numeric values";

	public static final Logger logger = Logger.getLogger(Aob3ContainerRowValidator.class.getName());
	
	public static List<String> validateRow(Aob3ContainerInfo row) {
		List<String> errors = new ArrayList<>();	
		String containerCompanyNum = row.getCompanyNumber();
		String muniFranNumber = row.getMuniFranContractNumber();
		String contractGroupNumber = row.getContractGroupNumber();
		if(!isBlank(muniFranNumber) && isBlank(contractGroupNumber)) {
			errors.add(CONT_GRP_BLANK);
		} else if(!isBlank(contractGroupNumber) && isBlank(muniFranNumber)) {
			errors.add(MUNI_FRAN_BLANK);
		}
		
		String contIdCode = row.getContainerIdCode();
		String hoaAssCode = row.getHoaAssociationCode();
		if("H".equalsIgnoreCase(contIdCode) && isBlank(hoaAssCode)) {
			errors.add(HOA_CODE_REQ);
		}
		
		String onCallFlag = row.getOnCallFlag();
		String minNumberLifts = row.getMinimumNumberLifts();
		String totalLifts = row.getTotalLifts();

		if (isValidNumber(totalLifts) && isValidNumber(minNumberLifts))  {		
			if(onCallFlag != null) {
				Integer minLifts = Integer.parseInt(minNumberLifts);
				if(("Y".equalsIgnoreCase(onCallFlag) && (minLifts < 1 || minLifts > 999))) {
					errors.add(ONCALL_FLAG_YES);
				}
				Integer totLifts = Integer.parseInt(totalLifts);
				if(("N".equalsIgnoreCase(onCallFlag) && (totLifts < 1))) {
					errors.add(ONCALL_FLAG_NO);
				}
				if(("N".equalsIgnoreCase(onCallFlag) && (minLifts != 0))) {
					errors.add(ONCALL_FLAG_NO_MIN);
				}
			}
		} else {
			errors.add(MINTOTAL_LIFTS);
		}
		String contEffDate = row.getContainerStartDate();
		String siteEffDate = row.getAob3SiteInformation() != null ? row.getAob3SiteInformation().getStartDate() : null;
		if( contEffDate != null && siteEffDate != null) {
			try {
				Integer contEffDte = Integer.parseInt(contEffDate);
				Integer siteEffDte = Integer.parseInt(siteEffDate);
				if(contEffDte < siteEffDte) {
					errors.add(CONT_SITE_DATE_GREATER);	
				} 
			} catch(NumberFormatException nfe) {
				errors.add("Invalid Container Start Date: " + contEffDate + " or invalid Site Start Date: " + siteEffDate);
			}
		}
		// route fields vals

		String createDelUr = row.getCreateDeliveryUR();
		
		String poReq = row.getPoRequired();
		String deliveryPONum = row.getDeliveryPONumber();
		if("Y".equalsIgnoreCase(createDelUr) && "Y".equalsIgnoreCase(poReq) && isBlank(deliveryPONum)) {
			errors.add(DELIV_PO_NUM_REQ);
		}	
		

		String urEffecDate = row.getUrEffectiveDate();
		String conStartDate = row.getContainerStartDate();	
		if("Y".equalsIgnoreCase(createDelUr) && isBlank(urEffecDate)) {
			errors.add(UR_EFFECT_DATE_REQ);
		}

		String urPlanDte = row.getUrPlanDate();
		String schedulePermServDate = row.getSchedulePermServiceDate();		
		if (urPlanDte != null && !urPlanDte.isEmpty() && schedulePermServDate != null && !schedulePermServDate.isEmpty()) {
			try {
				Integer urPlnDte = Integer.parseInt(urPlanDte);
				Integer schPermSrvDte = Integer.parseInt(schedulePermServDate);
				if("Y".equalsIgnoreCase(createDelUr) && urPlnDte < schPermSrvDte) {
					errors.add(UR_PLAN_DATE_GREATER_PERM);	
				} 
			} catch(NumberFormatException nfe) {
				errors.add("Invalid UR Plan Date: " + urPlanDte + " or invalid Schedule Perm Service Date: " + schedulePermServDate);
			}
			try {
				Integer urPlnDte = Integer.parseInt(urPlanDte);
				Integer containerStartDte = Integer.parseInt(conStartDate);
				if("Y".equalsIgnoreCase(createDelUr) && urPlnDte < containerStartDte) {
					errors.add(UR_PLAN_DATE_GREATER_CONT);
				} 
			} catch(NumberFormatException nfe) {
				errors.add("Invalid UR Plan Date: " + urPlanDte + " or invalid Container Start Date: " + conStartDate);
			}
		}
		
		if ("Y".equalsIgnoreCase(createDelUr) && isBlank(urPlanDte)) {
			errors.add(UR_PLAN_DATE_REQ);
		}

		if ("N".equalsIgnoreCase(createDelUr) && !isBlank(urPlanDte) || "N".equalsIgnoreCase(createDelUr) && !isBlank(urEffecDate)) {
			errors.add(CREATE_DEL_UR_N);
		}
			
		if("N".equalsIgnoreCase(createDelUr)) {
			row.setDeliveryNote1("");
		}
			
		String schedulePermServ = row.getSchedulePermService();
		if("625".equalsIgnoreCase(containerCompanyNum) && !"N".equalsIgnoreCase(schedulePermServ)) {
			errors.add(SCHE_PERM_SERV_REQ);
		} 
		
		if("625".equalsIgnoreCase(containerCompanyNum) && !isBlank(schedulePermServDate)) {
			errors.add(SCHE_PERM_SERV_DATE_BLANK);
		} 
		
		if (row.getSchedulePermServiceDate() != null && !row.getSchedulePermServiceDate().isEmpty()
           && row.getContainerStartDate() != null && !row.getContainerStartDate().isEmpty()) {
        try {
          Integer schPermSrvDte = Integer.parseInt(schedulePermServDate);
          Integer containerStartDte = Integer.parseInt(conStartDate);
          if ("Y".equalsIgnoreCase(createDelUr) && schPermSrvDte < containerStartDte) {
            errors.add(SCHE_PERM_SERV_DATE_GREATER);
        }
            } catch (NumberFormatException nfe) {
             errors.add("Invalid Schedule Perm Service Date: " + row.getSchedulePermServiceDate() +
                   " or invalid Container Start Date: " + row.getContainerStartDate());
           }
		} else {
			row.setSchedulePermServiceDate("");
	}	
		
		String schedulePermServPlanDate = row.getSchedulePermServicePlanDate();
		if (schedulePermServPlanDate == null){
			schedulePermServPlanDate = "";
		}
		if("625".equalsIgnoreCase(containerCompanyNum) && !isBlank(schedulePermServPlanDate)) {
			errors.add(SCHE_PERM_SERV_PLAN_DATE_BLANK);
		} 
		
		if (row.getSchedulePermServiceDate() != null && !row.getSchedulePermServiceDate().isEmpty()
           && row.getContainerStartDate() != null && !row.getContainerStartDate().isEmpty()) {
			try {
				Integer schPermServPlanDte = Integer.parseInt(schedulePermServPlanDate);
				Integer containerStartDte = Integer.parseInt(conStartDate);
			    if("Y".equalsIgnoreCase(createDelUr) && schPermServPlanDte < containerStartDte) {
			    errors.add(SCHE_PERM_SERV_PLAN_DATE_GREATER);
			    }
			} catch(NumberFormatException nfe) {
				errors.add("Invalid Schedule Perm Service Plan Date: " + row.getSchedulePermServicePlanDate() + " or invalid Container Start Date: " + row.getContainerStartDate());
			}
		} else {
			row.setSchedulePermServiceDate("");	
		}
		
		String uom = row.getUnitOfMeasure();
		String conType = row.getContainerType();
		if ((uom == null || uom.trim().isEmpty()) && ("RO".equalsIgnoreCase(conType) || "IR".equalsIgnoreCase(conType) || "SC".equalsIgnoreCase(conType) || "TL".equalsIgnoreCase(conType))) {
    		errors.add(UOM_REQ);
		}

	weightLimitValidator(row, errors);

		return errors;
	}

	private static void weightLimitValidator(Aob3ContainerInfo row, List<String> errors) {
	    if (row != null && row.getAob3Rates() != null && !row.getAob3Rates().isEmpty()) {
	        List<Aob3RateInfo> rates = row.getAob3Rates();
	        Map<String, List<Aob3RateInfo>> ratesGroups = rates.stream().collect(Collectors.groupingBy(rate -> {
	            String accountNumber = rate.getAccountNumber() != null ? rate.getAccountNumber() : "";
	            String siteNumber = rate.getSiteNumber() != null ? rate.getSiteNumber() : "";
	            String containerGroup = rate.getContainerGroup() != null ? rate.getContainerGroup() : "";
	            return accountNumber + '.' + siteNumber + '.' + containerGroup;
	        }));
	        ratesGroups.entrySet().stream().map(entrySet -> entrySet.getValue()).forEach(rateGroup -> {
	            if (row.getWeightLimit() != null && !row.getWeightLimit().isEmpty()) {
	                List<String> ovrList = new ArrayList<>();
	                List<String> amountList = new ArrayList<>();
	                for (int i = 0; i < rateGroup.size(); i++) {
	                    Aob3RateInfo rateInfo = rateGroup.get(i); 
	                    if (rateInfo != null) {
	                        String rateChargeCode = rateInfo.getChargeCode();
	                        String rateChargeAmount = rateInfo.getChargeAmount();

	                        if (rateChargeCode != null) {
	                            ovrList.add(rateChargeCode);
	                        }
	                        if (rateChargeAmount != null) {
	                            amountList.add(rateChargeAmount);
	                        }
	                    }
	                }

	        boolean ovrNotFound = ovrList.stream()
	                .map(String::toUpperCase) // Convert all list values to uppercase
	                .anyMatch(s -> s.equals("OVR")); // Check if any value matches "OVR"
	        if (!ovrNotFound) {
	            errors.add(WEIGHT_LMT_NEEDED_INFO);
	        }

	        for (String value : amountList) {
	            try {
	                double num = Double.parseDouble(value); // Convert string to double
	                if (num < 1.0) {
	                    errors.add(WEIGHT_LMT_NEEDED_INFO);
	                    break;
	                }
	            } catch (NumberFormatException e) {
	                errors.add("Invalid number format: " + value);
	            }
	        }
	    }
	});
	}
	}
	public static boolean isValidNumber(String str) {
        // Regular expression to check if the string contains only digits
        return str != null && str.matches("\\d+");
    }
}