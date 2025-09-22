package com.repsrv.csweb.core.account.openmarket.validators;

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
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketAccountInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketContainerInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketRateInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketRouteInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketSiteInformation;


public class OpenMarketSheetRowValidator implements ConstraintValidator<OpenMarketValidSheetRowData, Row>{
	private final Logger logger = LoggerFactory.getLogger(OpenMarketSheetRowValidator.class);
	
	private SheetId sheet;
	
	public void initialize(OpenMarketValidSheetRowData sheet) {
		this.sheet = sheet.sheetId();
    }
	
	@Override
	public boolean isValid(Row value, ConstraintValidatorContext context) {
		List<String> errors = null;
		switch(sheet) {
		case ACCOUNT:
			errors = OpenMarketAccountRowValidator.validateRow((OpenMarketAccountInformation)value);
			break;
		case SITE:
			errors = OpenMarketSiteRowValidator.validateRow((OpenMarketSiteInformation)value);
			break;
		case CONTAINER:
			errors = OpenMarketContainerRowValidator.validateRow((OpenMarketContainerInformation)value);
			break;
		case RATE: 
			errors = OpenMarketRateRowValidator.validateRow((OpenMarketRateInformation)value);
			break;
		case ROUTE: 
			errors = OpenMarketRouteRowValidator.validateRow((OpenMarketRouteInformation)value);
			break;
		default:
			errors = new ArrayList<>();
		}
		
		try {
			value.correctNullStrings();
		} catch (BeansException | IllegalArgumentException | IllegalAccessException e1) {
			logger.warn("Unable to set null string for object", e1);
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
