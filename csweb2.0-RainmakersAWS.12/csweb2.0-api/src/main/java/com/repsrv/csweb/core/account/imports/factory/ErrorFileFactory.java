package com.repsrv.csweb.core.account.imports.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.imports.service.ResiContractErrorFileService;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.ImportHistory;
import com.repsrv.csweb.core.account.imports.service.ErrorFileService;

@Component
public class ErrorFileFactory {

	@Autowired
	private ResiContractErrorFileService resiContractService;
	
	public ErrorFileService getErrorFileService(ImportHistory item) {
		AccountImportType type = AccountImportType.valueOfSafe(item.getType());
		switch(type) {
			case RCNTRT:
				return resiContractService;
			default:
				throw new RuntimeException("Type "+ type+" not currently supported");
		}
	}
	
}
