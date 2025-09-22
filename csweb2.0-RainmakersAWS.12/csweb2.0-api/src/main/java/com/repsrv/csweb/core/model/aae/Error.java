package com.repsrv.csweb.core.model.aae;

public class Error {
	
	private String stagingId;
	private String identifier;
	private String key;
	private String value;
	private String description;
	private String error;
	
	public String getStagingId() {
		return stagingId;
	}
	public void setStagingId(String stagingId) {
		this.stagingId = stagingId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
