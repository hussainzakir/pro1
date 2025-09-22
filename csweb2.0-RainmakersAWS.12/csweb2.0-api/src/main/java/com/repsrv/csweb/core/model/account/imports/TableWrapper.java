package com.repsrv.csweb.core.model.account.imports;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class TableWrapper {

	@JsonProperty("table")
	private Object table;

	public TableWrapper(Object table) {
		super();
		this.table = table;
	}
	
}
