package com.repsrv.csweb.core.aae.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.model.aae.SearchParams;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AaeSearchRequest extends StoredProcCallResult{

	private SearchParams searchParams;
	private String division;
	private Integer pageNumber;
	private Integer pageSize;

	public AaeSearchRequest(SearchParams searchParams, String division, Integer pageNumber, Integer pageSize) {
		super();
		this.searchParams = searchParams;
		this.division = division;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}



	public String getJson() {
		return getJson(searchParams);
	}
	
	public String getDivision() {
		return this.division;
	}
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
}
