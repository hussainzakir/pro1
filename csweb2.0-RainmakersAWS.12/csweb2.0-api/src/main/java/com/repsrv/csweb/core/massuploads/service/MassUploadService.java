package com.repsrv.csweb.core.massuploads.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.repsrv.csweb.core.model.massupload.*;
import com.repsrv.csweb.core.support.exception.StoredProcedureException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateValidationException;

public interface MassUploadService {

	GetTemplatesDto getTemplates();

	List<MuHistory> getHistory();
	
	List<ColumnDefinition> getTemplateColumns(String templateId);
	
	MuTemplate getTemplate(String templateId, String changeNumber);

	MuTemplate getTemplateForDownload(String templateId, String changeNumber, String username);


	boolean processMassUpload(MuTemplate template, 
			InputStream uploadInputStream, 
			String changeNumber, String userFileName) 
					throws TemplateNotReadableException, 
					TemplateValidationException, StoredProcedureException;
	
	byte[] getErrorFile(String path) throws IOException;

	MuHistory getHistoryEntry(String logId, MuSystem system);

	List<List<String>> getErrorData(String schema, String tableToUpdate, MuSystem muSystem);

	void updateErrorFileGenerationStatus(String logId, int status, String s3Path, MuSystem muSystem);
}
