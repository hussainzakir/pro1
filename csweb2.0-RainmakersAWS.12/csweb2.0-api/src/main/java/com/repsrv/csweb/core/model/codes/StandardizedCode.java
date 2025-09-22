package com.repsrv.csweb.core.model.codes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class StandardizedCode {
	@JsonProperty("TableElement")
	private String tableElement;
	
	@JsonProperty("Description")
	private String description;

	public String getTableElement() {
		return tableElement;
	}
	public void setTableElement(String tableElement) {
		this.tableElement = tableElement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}