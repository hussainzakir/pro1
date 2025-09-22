package com.repsrv.csweb.core.account.openmarket.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.repsrv.csweb.core.model.account.openmarket.OpenMarketRouteInformation;

public class OpenMarketRouteRowValidator {

    	public static List<String> validateRow(OpenMarketRouteInformation row) {
		List<String> errors = new ArrayList<>();
		
		if(row.getOpenMarketContainerInformationontainer() != null) {
			try {
				Integer periodLen = Integer.parseInt(row.getOpenMarketContainerInformationontainer().getPeriodLength());
				if(periodLen > 1 && 
						(StringUtils.isBlank(row.getFirstServiceDate()) || row.getFirstServiceDate().equals("0"))){
						errors.add("First Service Date required when Container period length > 1 ");
					}
			} catch(NumberFormatException nfe){
				errors.add("Invalid Period Length in associated container: " + row.getOpenMarketContainerInformationontainer().getPeriodLength());
			}
		}
		
		return errors;
	}
}
