package com.repsrv.csweb.core.account.imports3.service;

import java.io.InputStream;
import java.util.*;

import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import org.apache.poi.ss.usermodel.Workbook;

import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult;
import com.repsrv.csweb.core.model.account.imports.RowError;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

public abstract class AccountImport3Service<T> {

	private static final String OUTDATED_TEMPLATE = "Outdated/Incorrect template, please download the most recent version of this template.";
	private static final String EMPTY_TEMPLATE = "Empty/Incomplete template, please fill out all the required fields and resubmit.";


	public ImportValidationResult doValidation(InputStream is) throws TemplateNotReadableException {
		
		Workbook workbook = read(is);
		
		ImportValidationResult result = serialize(workbook);
		
		if (!validateTemplateVersion(workbook)){
		TemplateImportResult resiResult = (TemplateImportResult) result;
		Map<String, Collection<RowError>> errors = new HashMap<>();
			RowError error = new RowError();
			error.addError(OUTDATED_TEMPLATE);
			errors.put("Outdated/Incorrect template",Arrays.asList(error));
			resiResult.setValidationErrors(errors);
			return result;
		}

		if (!validateEmptyTemplate(workbook)){
			TemplateImportResult resiResult = (TemplateImportResult) result;
			Map<String, Collection<RowError>> errors = new HashMap<>();
				RowError error = new RowError();
				error.addError(EMPTY_TEMPLATE);
				errors.put("Empty/Incomplete template",Arrays.asList(error));
				resiResult.setValidationErrors(errors);
				return result;
		}

		validate(result);		

		finalizeForPreview(result);
		
		return result;
	}

	protected Workbook read(InputStream is) throws TemplateNotReadableException {
		Workbook workbook = PoiUtil.safeOpenUploadFile(is);
		if(workbook == null)
			throw new TemplateNotReadableException("Template is not readable");
		
		return workbook;
		
	}	
	protected abstract ImportValidationResult serialize(Workbook workbook);
	
	protected abstract void validate(ImportValidationResult type);
	
	protected abstract void finalizeForPreview(ImportValidationResult type);

	protected abstract boolean validateTemplateVersion(Workbook workbook);

	protected abstract boolean validateEmptyTemplate(Workbook workbook);

	public abstract void scheduleUpload(String projectId, String scheduleDate, String scheduleTime);

	public abstract Object changeScheduleUpload(String projectId, String scheduleDate, String scheduleTime, String action);

	public abstract String uploadHistory();

	public abstract void persistData(
			List<?> acctsDeser,
			String fileName,
			String changeNumber,
			String uploadType,
			String aquiName,
			String projectId) throws AccountImportSetupException;

}
