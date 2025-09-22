package com.repsrv.csweb.core.account.imports.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.imports.dao.AccountImportDao;
import com.repsrv.csweb.core.account.imports.factory.ErrorFileFactory;
import com.repsrv.csweb.core.model.account.imports.ImportHistory;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service
public class AccountImportHistoryService extends  JsonResultService {

	@Autowired
	private AccountImportDao accountImportDao;
	
	@Autowired
	private ErrorFileFactory errorFileFactory;
	
	public List<ImportHistory> getUserImportHistory(String userId){
		
		StoredProcCallResult result = new StoredProcCallResult();
		String json = accountImportDao.getUserImportHistory(userId, result);
		
		return getJsonDataObject(json, new TypeReference<List<ImportHistory>>(){}); 
	}

	public Workbook fetchErrorFile(ImportHistory item) {
		
		// call a factory based on 'type' to get file
		ErrorFileService service = errorFileFactory.getErrorFileService(item);
		
		return service.generateErrorFile(item);
		
	}
}
