package com.repsrv.csweb.core.massuploads.template;

import java.util.ArrayList;
import java.util.List;

public class RowErrorLog {
	private int rowNumber;
	private int errorCount = 0;
	private List<String> columnErroMsg = new ArrayList<>();

	public void addColumnError(String errorMsg) {
		columnErroMsg.add(errorMsg);
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void incrementErrorCount() {
		this.errorCount++;
	}

	public List<String> getColumnErroMsg() {
		return columnErroMsg;
	}


}
