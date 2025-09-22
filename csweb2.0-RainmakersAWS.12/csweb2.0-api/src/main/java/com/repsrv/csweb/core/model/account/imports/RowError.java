package com.repsrv.csweb.core.model.account.imports;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RowError {

	private int row;
	
	private List<String> errors;
	
	public RowError() {}
	
	public RowError(int row, String error) {
		this.row = row;
		addError(error);
	}

	public void addError(String message) {
		if(this.errors == null)
			this.errors = new ArrayList<>();
		
		this.errors.add(message);
	}
}
