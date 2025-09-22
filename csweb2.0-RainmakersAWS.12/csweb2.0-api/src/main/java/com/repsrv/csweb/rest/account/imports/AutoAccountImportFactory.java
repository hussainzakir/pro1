package com.repsrv.csweb.rest.account.imports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.imports.service.AccountImportService;
import com.repsrv.csweb.core.account.imports.service.ResiContractImportService;
import com.repsrv.csweb.core.account.onboarding.service.AccountOnBoardingService;
import com.repsrv.csweb.core.account.openmarket.service.OpenMarketService;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;

@Component
public class AutoAccountImportFactory {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ResiContractImportService resiService;
	@Autowired
	private AccountOnBoardingService aobService;
	@Autowired
	private OpenMarketService openMarketService;
	
	public AccountImportService<?> getUploadService(AccountImportType uType) {

		switch(uType) {
			case RCNTRT:
				return resiService;
			case AOB:
				return aobService;
			case OPENMARKET:
				return openMarketService;
			default:
				throw new RuntimeException("Upload type invalid " + uType.name()) ;
		}
	}

}
