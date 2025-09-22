package com.repsrv.csweb.core.account.onboarding.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;
import com.repsrv.csweb.core.model.account.onboarding.AobAccountInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobContainerInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobRateInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobSiteInformation;


public class AobSheetRowValidator implements ConstraintValidator<AobValidSheetRowData, Row>{
	private final Logger logger = LoggerFactory.getLogger(AobSheetRowValidator.class);
	
	private SheetId sheet;
	
	public void initialize(AobValidSheetRowData sheet) {
		this.sheet = sheet.sheetId();
    }
	
	@Override
	public boolean isValid(Row value, ConstraintValidatorContext context) {
		List<String> errors = null;
		switch(sheet) {
		case ACCOUNT:
			errors = AobAccountRowValidator.validateRow((AobAccountInformation)value);
			break;
		case SITE:
			errors = AobSiteRowValidator.validateRow((AobSiteInformation)value);
			break;
		case CONTAINER:
			errors = AobContainerRowValidator.validateRow((AobContainerInformation)value);
			break;
		case RATE: 
			errors = AobRateRowValidator.validateRow((AobRateInformation)value);
			break;
		default:
			errors = new ArrayList<>();
		}
		
		try {
			value.correctNullStrings();
		} catch (BeansException | IllegalArgumentException | IllegalAccessException e1) {
			logger.error("Unable to set null string for object", e1);
		}
		
		if(!errors.isEmpty()) {
			errors.forEach(e -> 
				setNewErrorMessages(e, context)
			);
			
			return false;
		}
		
		return true;
		
	}

	public static void setNewErrorMessages(String newErrorMessage, ConstraintValidatorContext context) {
    String sanitizedMessage = newErrorMessage.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(sanitizedMessage)
            .addConstraintViolation();
}
	
}
