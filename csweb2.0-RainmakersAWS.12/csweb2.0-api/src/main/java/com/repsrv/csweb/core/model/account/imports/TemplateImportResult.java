package com.repsrv.csweb.core.model.account.imports;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Valid
public class TemplateImportResult<T> implements ImportValidationResult {

	@JsonIgnore
	@Setter
	@Valid
	private List<T> accounts;
	
	private Map<String, Collection<RowError>> unmappedRows;
	private Map<String, Collection<RowError>> validationErrors;
	private Map<String, List<? extends Row>> sheetsPreview;
	
	public void setValidationErrors(Map<String, Collection<RowError>> errors) {
		this.validationErrors = errors;
	}
	
	@Override
	public boolean hasErrors() {
		return (this.validationErrors != null 
				&& !this.validationErrors.isEmpty())
				|| 
				(this.unmappedRows != null 
				&& !this.unmappedRows.isEmpty());
	}

	@Override
	public int getMappingErrorsCount() {
		if(unmappedRows == null) return 0;
		
		return unmappedRows.values().stream()
		.flatMap(col -> col.stream())
		.mapToInt(i -> i.getErrors().size()).sum();
	}

	@Override
	public int getValidationErrorsCount() {
		if(validationErrors == null) return 0;
		
		return validationErrors.values().stream()
				.flatMap(col -> col.stream())
				.mapToInt(i -> i.getErrors().size()).sum();
	}

	@Override
	public Map<String, Collection<RowError>> getValidationErrors() {
		return this.validationErrors;
	}

	@Override
	public Map<String, List<? extends Row>> getSheetsPreview() {
		return sheetsPreview;
	}

	public void setUnmappedRows(Map<String, Collection<RowError>> unmappedRows) {
		this.unmappedRows = unmappedRows;
	}
}
