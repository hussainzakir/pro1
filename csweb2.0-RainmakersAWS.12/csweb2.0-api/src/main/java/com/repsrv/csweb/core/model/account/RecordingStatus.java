package com.repsrv.csweb.core.model.account;

public enum RecordingStatus {
	OPEN(" "),
	PENDING("P"),
	CLOSED("Y");
	
	private String code;
	
	private RecordingStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static RecordingStatus getFromString(String status_) {
		for(RecordingStatus status : RecordingStatus.values()) {
			if(status.name().equalsIgnoreCase(status_))
				return status;
		}
		return RecordingStatus.OPEN;
	}
	
	public String getString() {
		return this.name();
	}
}
