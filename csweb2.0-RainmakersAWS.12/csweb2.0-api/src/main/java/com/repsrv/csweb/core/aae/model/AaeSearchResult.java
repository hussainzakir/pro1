package com.repsrv.csweb.core.aae.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AaeSearchResult {

	private List<AaeSearchRecord> records;
	
	private int page;
	
	private int totalRecords;
	
	private int pageSize;
}
