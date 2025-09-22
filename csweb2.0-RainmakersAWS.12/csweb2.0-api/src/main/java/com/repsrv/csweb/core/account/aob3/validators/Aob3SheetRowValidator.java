package com.repsrv.csweb.core.account.aob3.validators;

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
import com.repsrv.csweb.core.model.account.aob3.Aob3AccountInfo;
import com.repsrv.csweb.core.model.account.aob3.Aob3ContainerInfo;
import com.repsrv.csweb.core.model.account.aob3.Aob3RateInfo;
import com.repsrv.csweb.core.model.account.aob3.Aob3SiteInfo;


public class Aob3SheetRowValidator implements ConstraintValidator<Aob3ValidSheetRowData, Row>{
	private final Logger logger = LoggerFactory.getLogger(Aob3SheetRowValidator.class);
	
	private SheetId sheet;
	
	public void initialize(Aob3ValidSheetRowData sheet) {
		this.sheet = sheet.sheetId();
    }
	
	@Override
	public boolean isValid(Row value, ConstraintValidatorContext context) {
		List<String> errors = null;
		switch(sheet) {
		case ACCOUNT:
			errors = Aob3AccountRowValidator.validateRow((Aob3AccountInfo)value);
			break;
		case SITE:
			errors = Aob3SiteRowValidator.validateRow((Aob3SiteInfo)value);
			break;
		case CONTAINER:
			errors = Aob3ContainerRowValidator.validateRow((Aob3ContainerInfo)value);
			break;
		case RATE: 
			errors = Aob3RateRowValidator.validateRow((Aob3RateInfo)value);
			break;
		default:
		  throw new RuntimeException("Unsupported or unhandled sheet type: " + sheet);
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