package com.repsrv.csweb.core.model.account.imports;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ImportValidationResult {

	boolean hasErrors();
	
	int getMappingErrorsCount();
	
	int getValidationErrorsCount();
	
	Map<String, List<? extends Row>> getSheetsPreview();
	
	Map<String, Collection<RowError>> getUnmappedRows();
	
	Map<String, Collection<RowError>> getValidationErrors();
}
