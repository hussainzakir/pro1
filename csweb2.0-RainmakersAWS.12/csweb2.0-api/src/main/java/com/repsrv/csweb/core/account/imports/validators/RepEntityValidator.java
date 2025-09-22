package com.repsrv.csweb.core.account.imports.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.repsrv.csweb.core.model.account.imports.EntityType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepEntityValidator implements ConstraintValidator<RepEntity, String>{

	
	private EntityType entityType;
	
	@Override
	public void initialize(RepEntity entity) {
		this.entityType = entity.entity();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		
		
		switch(this.entityType) {
		case COMPANY:
			return true;
		case ACCOUNT:
			return true;
		case SITE:
			return true;
		case CONTAINER:
			return true;
		default:
			return false;
		
		}
		
	}
	
}
