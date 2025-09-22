package com.repsrv.csweb.core.account.imports.validators.resicontract;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.repsrv.csweb.core.model.account.imports.SheetId;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SheetRowValidator.class })
public @interface ValidSheetRowData {

	String message() default "Row validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    SheetId sheetId();
}
