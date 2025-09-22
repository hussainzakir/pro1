package com.repsrv.csweb.core.model.account.imports;

import java.util.List;

public class AccountImport {

	private List<AccountInformation> accounts;
	
	private List<ImportError> rowErrors;

	public List<AccountInformation> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountInformation> accounts) {
		this.accounts = accounts;
	}

	public List<ImportError> getRowErrors() {
		return rowErrors;
	}

	public void setRowErrors(List<ImportError> rowErrors) {
		this.rowErrors = rowErrors;
	}
	
	
}
