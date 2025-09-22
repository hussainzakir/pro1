package com.repsrv.csweb.rest.exception;

public class StoredProcedureErrorException extends RuntimeException {

	public StoredProcedureErrorException() {
		super();
	}

	public StoredProcedureErrorException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public StoredProcedureErrorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StoredProcedureErrorException(String arg0) {
		super(arg0);
	}

	public StoredProcedureErrorException(Throwable arg0) {
		super(arg0);
	}

}
