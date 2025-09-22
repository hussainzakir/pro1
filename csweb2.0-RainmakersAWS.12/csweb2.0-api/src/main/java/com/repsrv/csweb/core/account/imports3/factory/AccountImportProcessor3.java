package com.repsrv.csweb.core.account.imports3.factory;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.aob3.service.Aob3Service;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

@Component
public class AccountImportProcessor3 {
	
	@Autowired
	private Aob3Service aob3ImportService;

	public boolean doUpload(AccountImportType type, String json) {
		return true;
	}
	
	public ImportValidationResult doValidation(AccountImportType type, InputStream is) throws TemplateNotReadableException {
		
		ImportValidationResult results = null;
		switch(type) {
			
			case AOB:
				results = aob3ImportService.doValidation(is);
				break;
			default:
				break;
			}
			
			return results;
	} 

}
