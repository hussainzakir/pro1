package com.repsrv.csweb.rest.account.imports3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.aob3.service.Aob3Service;
import com.repsrv.csweb.core.account.imports3.service.AccountImport3Service;
import com.repsrv.csweb.core.model.imports3.AccountImport3Type;

@Component
public class AutoAccountImport3Factory {
    
protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Aob3Service aob3Service;

	public AccountImport3Service<?> getUploadService(AccountImport3Type uType) {

		switch(uType) {
			case AOB:
				return aob3Service;
			default:
				throw new RuntimeException("Upload type invalid " + uType.name()) ;
		}
	}

}

