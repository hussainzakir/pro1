package com.repsrv.csweb.core.account.imports.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.repsrv.csweb.core.model.account.imports.EntityType;

@Documented
@Constraint(validatedBy = RepEntityValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RepEntity {

	String message() default "{RepEntity}";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	EntityType entity();

}
