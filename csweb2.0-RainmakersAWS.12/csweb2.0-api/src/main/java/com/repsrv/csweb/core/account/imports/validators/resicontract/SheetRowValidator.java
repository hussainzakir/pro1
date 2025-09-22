package com.repsrv.csweb.core.account.imports.validators.resicontract;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.ContainerInformation;
import com.repsrv.csweb.core.model.account.imports.RateInformation;
import com.repsrv.csweb.core.model.account.imports.RouteInformation;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.SheetId;
import com.repsrv.csweb.core.model.account.imports.SiteInformation;

public class SheetRowValidator implements ConstraintValidator<ValidSheetRowData, Row>{
	private final Logger logger = LoggerFactory.getLogger(SheetRowValidator.class);
	
	private SheetId sheet;
	
	public void initialize(ValidSheetRowData sheet) {
		this.sheet = sheet.sheetId();
    }
	
	@Override
	public boolean isValid(Row value, ConstraintValidatorContext context) {
		List<String> errors = null;
		switch(sheet) {
		case ACCOUNT:
			errors = AccountRowValidator.validateRow((AccountInformation)value);
			break;
		case SITE:
			errors = SiteRowValidator.validateRow((SiteInformation)value);
			break;
		case CONTAINER:
			errors = ContainerRowValidator.validateRow((ContainerInformation)value);
			break;
		case ROUTE:
			errors = RouteRowValidator.validateRow((RouteInformation)value);
			break;
		case RATE: 
			errors = RateRowValidator.validateRow((RateInformation)value);
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
