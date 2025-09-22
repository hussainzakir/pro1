package com.repsrv.csweb.core.account.imports.factory;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.imports.service.ResiContractImportService;
import com.repsrv.csweb.core.account.onboarding.service.AccountOnBoardingService;
import com.repsrv.csweb.core.account.openmarket.service.OpenMarketService;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

@Component
public class AccountImportProcessor {
	
	@Autowired
	private ResiContractImportService resiService;
	
	@Autowired
	private AccountOnBoardingService aobImportService;

	@Autowired
	private OpenMarketService openMarketService;

	public boolean doUpload(AccountImportType type, String json) {
		return true;
	}
	
	public ImportValidationResult doValidation(AccountImportType type, InputStream is) throws TemplateNotReadableException {
		
		ImportValidationResult results = null;
		switch(type) {
			case RCNTRT:
				results = resiService.doValidation(is);
				break;
			case AOB:
				results = aobImportService.doValidation(is);
				break;
			case OPENMARKET:
				results = openMarketService.doValidation(is);
				break;
			default:
				break;
			}
			
			return results;
	} 

}
