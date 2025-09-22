package com.repsrv.csweb.core.account.imports.service;

import org.apache.poi.ss.usermodel.Workbook;

import com.repsrv.csweb.core.model.account.imports.ImportHistory;

public interface ErrorFileService  {

	
	Workbook generateErrorFile(ImportHistory item);
}
