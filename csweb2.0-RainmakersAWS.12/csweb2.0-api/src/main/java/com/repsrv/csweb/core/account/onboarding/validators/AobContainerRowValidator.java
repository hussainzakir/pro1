package com.repsrv.csweb.core.account.onboarding.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import com.repsrv.csweb.core.account.imports.validators.BaseValidator;
import com.repsrv.csweb.core.model.account.onboarding.AobContainerInformation;


import static org.apache.commons.lang.StringUtils.isBlank;

public class AobContainerRowValidator extends BaseValidator{
	
	public static final String CONT_GRP_BLANK = "Contract Group Number cannot be blank when Muni Fran Group Number is not blank";
	public static final String MUNI_FRAN_BLANK = "Muni Fran Group Number cannot be blank when Contract Group Number is not blank";
	public static final String HOA_CODE_REQ = "HOA Assoc. Code is required when Conatiner ID Code is H";
	public static final String ONCALL_FLAG_YES = "Minimum Number of lifts should be more than zero and less than 999 when On Call Flag is Y";
	public static final String ONCALL_FLAG_NO = "Total number lifts should be greater than zero when On Call Flag is N";
	public static final String ONCALL_FLAG_NO_MIN = "Minimun number of lifts must be zero when On Call Flag is N";
	public static final String DEL_SVC_CODE_NEEDED = "Delivery Service Code must be either DEL or NCL when Create Delivery UR is Y";
	public static final String DEL_SVC_CODE_BLANK = "Delivery Service Code must be blank when Create Delivery UR is N";
	public static final String DELIV_PO_NUM_REQ = "Delivery PO Number is required when create Delivery UR is Y and container PO Required is Y";
	public static final String UR_EFFECT_DATE_REQ = "UR Effective Date is required when Create Delivery UR is Y";
	public static final String PERM_GREATER_UR_PLAN_DATE = "Create Delivery UR is 'Y' Schedule Perm Service Date must be equal or greater than UR Plan Date";
	public static final String UR_PLAN_DATE_GREATER_CONT = "Create Delivery UR is 'Y' UR Plan Date must be equal or greater than Container Start Date";
	public static final String UR_PLAN_DATE_REQ = "UR Plan Date is Required when Create Delivery UR is 'Y'";
	public static final String SCHE_PERM_SERV_REQ = "Schedule Perm Service must be N when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_DATE_BLANK = "Schedule Perm Service Date must be blank when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_DATE_GREATER = "Create Delivery UR is 'Y' Schedule Perm Service Date must be equal or greater than Container Start Date";
	public static final String SCHE_PERM_SERV_PLAN_DATE_BLANK = "Schedule Perm Service Plan Date must be blank when Container Company Number is 625";
	public static final String SCHE_PERM_SERV_PLAN_DATE_GREATER = "Create Delivery UR is 'Y' Schedule Perm Service Plan Date must be equal or greater than Container Start Date";
	public static final String RTE_COMP_NUMBER_MATCH = "Route Company Number must match Rate Company Number";
	public static final String RTE_ACCT_NUMBER_MATCH = "Route Account Number must match Rate Account Number";
	public static final String RTE_SITE_NUMBER_MATCH = "Route Site Number must match Rate Site Number";
	public static final String RTE_CONTAINER_NUMBER_MATCH = "Route Container Group Number must match Rate Container Group Number";
	public static final String CREATE_DEL_UR_N = "If Create Delivery UR is 'N' then UR Plan Date and UR Effective Date must be blank";
	public static final String CONT_SITE_DATE_GREATER = "Containter Start Date must be greater than or equal to Site Start Date";
	public static final String UOM_REQ = "UOM is required when Container Type is RO, IR, SC, or TL";
	public static final String MINTOTAL_LIFTS = "Minimun and total number of lifts can not be blank and accept only numeric values";

	public static final Logger logger = Logger.getLogger(AobContainerRowValidator.class.getName());
	
	public static List<String> validateRow(AobContainerInformation row) {
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
		String siteEffDate = row.getAobSiteInformation() != null ? row.getAobSiteInformation().getStartDate() : null;
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
				if("Y".equalsIgnoreCase(createDelUr) && schPermSrvDte < urPlnDte  ) {
					errors.add(PERM_GREATER_UR_PLAN_DATE);	
				} 
			} catch(NumberFormatException nfe) {
				errors.add("Invalid UR Plan Date: " + urPlanDte + " or invalid Schedule Perm Service Date: " + schedulePermServDate);
			}
		}
		if (urPlanDte != null && !urPlanDte.isEmpty()) {
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
		
		if(!urPlanDte.equals(urEffecDate)) {
		errors.add("UR Plan Date must be equal to UR Effective Date");
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
		
		if(!schedulePermServDate.equals(schedulePermServPlanDate)) {
		errors.add("Schedule Perm Service Date must be equal Schedule Perm Service Plan Date ");
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

		return errors;
	}

	public static boolean isValidNumber(String str) {
        // Regular expression to check if the string contains only digits
        return str != null && str.matches("\\d+");
    }
}
