package com.repsrv.csweb.core.account.imports.validators;

import org.apache.commons.lang3.StringUtils;

public class BaseValidator {

	
	protected static boolean isAllChars(String theChar, String theString) {
		if(theString == null || StringUtils.isBlank(theString))
			return false;
		else {
			return theChar.equals(theString.substring(0, 1))
					&& (theString.chars().distinct().count() == 1);
		}
	}
}
