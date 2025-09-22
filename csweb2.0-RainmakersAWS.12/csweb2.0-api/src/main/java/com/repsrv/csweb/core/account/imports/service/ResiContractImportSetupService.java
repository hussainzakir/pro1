package com.repsrv.csweb.core.account.imports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.account.imports.dao.AccountImportDao;
import com.repsrv.csweb.core.model.account.imports.ResiContractHandoff;

@Service
public class ResiContractImportSetupService {

	@Autowired
	private AccountImportDao accountImportDao;

	public void copyData(ResiContractHandoff handoffRequest) {
		// TODO Auto-generated method stub
		
	}
	
}
