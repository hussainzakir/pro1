package com.repsrv.csweb.core.support.exception.recordings;

public class ServiceInterruptionException extends Exception{

	public ServiceInterruptionException() {
		super();
	}

	public ServiceInterruptionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ServiceInterruptionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceInterruptionException(String arg0) {
		super(arg0);
	}

	public ServiceInterruptionException(Throwable arg0) {
		super(arg0);
	}

	
}
