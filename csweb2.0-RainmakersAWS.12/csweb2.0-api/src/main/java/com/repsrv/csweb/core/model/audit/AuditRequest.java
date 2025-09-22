package com.repsrv.csweb.core.model.audit;

import java.math.BigDecimal;

public class AuditRequest {

	private String cbs;
	private String fromDate;
	private String toDate;
	private String division;
	private String accountNumber;
	private String site;
	private String group;
	private String fileFilter;
	private int startIndex;
	private int endIndex;
	private String sort;
	private BigDecimal totalRows;
	
	public String getCbs() {
		return cbs;
	}
	public void setCbs(String cbs) {
		this.cbs = cbs;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getFileFilter() {
		return fileFilter;
	}
	public void setFileFilter(String fileFilter) {
		this.fileFilter = fileFilter;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public BigDecimal getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigDecimal totalRows) {
		this.totalRows = totalRows;
	}

	
}
