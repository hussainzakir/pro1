package com.repsrv.csweb.core.support.exception;

public class StoredProcedureException extends RuntimeException{

	public StoredProcedureException() {
		super();
	}

	public StoredProcedureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public StoredProcedureException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StoredProcedureException(String arg0) {
		super(arg0);
	}

	public StoredProcedureException(Throwable arg0) {
		super(arg0);
	}

}
