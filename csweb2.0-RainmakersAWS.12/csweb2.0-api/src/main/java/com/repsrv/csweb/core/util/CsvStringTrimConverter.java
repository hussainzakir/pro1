package com.repsrv.csweb.core.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CsvStringTrimConverter extends AbstractBeanField<String> {
	public CsvStringTrimConverter() {}
	
	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		if(value == null)
			return "";
		else return value.trim();
	}

}
