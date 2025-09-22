package com.repsrv.csweb.core.account.servicerecordings.model;

import java.util.Arrays;

import com.repsrv.csweb.core.model.account.RecordingStatus;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.util.RepSrvStringUtils;

public class AddRecordingRequest extends StoredProcCallResult{

	private RecordingStatus status;
	private String requestType;
	private String account;
	private String company;
	private String site;
	private String siteNumber;
	private String originatedBy;
	private String reportedBy;
	private String routeNumber;
	private String truckNumber;
	private String employeeNumber;
	private String description;
	private String username;
	private String priority;
	private String systemGenerated;
	private String escalationLevel;
	private String assignedTo;//employee select different
	private String resolution;
	private String[] descriptionArray;
	private String routeTo;
	
	public AddRecordingRequest() {}

	public RecordingStatus getStatus() {
		return status;
	}

	public AddRecordingRequest setStatus(RecordingStatus status) {
		this.status = status;

		return this;
	}

	public String getRequestType() {
		return requestType;
	}

	public AddRecordingRequest setRequestType(String requestType) {
		this.requestType = requestType;

		return this;
	}

	public String getAccount() {
		return account;
	}

	public AddRecordingRequest setAccount(String account) {
		this.account = account;

		return this;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public AddRecordingRequest setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;

		return this;
	}

	public String getOriginatedBy() {
		return originatedBy;
	}

	public AddRecordingRequest setOriginatedBy(String originatedBy) {
		this.originatedBy = originatedBy;

		return this;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public AddRecordingRequest setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
		
		return this;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public AddRecordingRequest setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;

		return this;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public AddRecordingRequest setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;

		return this;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public AddRecordingRequest setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;

		return this;
	}

	public String getDescription() {
		return description;
	}

	public AddRecordingRequest setDescription(String description) {
		this.description = description;
		this.descriptionArray = RepSrvStringUtils.textToArrayByLine(description, 8, 60,'\n');
		
		return this;
	}

	public String getUsername() {
		return username;
	}

	public AddRecordingRequest setUsername(String username) {
		this.username = username;

		return this;
	}

	public String getPriority() {
		return priority;
	}

	public AddRecordingRequest setPriority(String priority) {
		this.priority = priority;

		return this;
	}

	public String getSystemGenerated() {
		return systemGenerated;
	}

	public AddRecordingRequest setSystemGenerated(String systemGenerated) {
		this.systemGenerated = systemGenerated;

		return this;
	}

	public String getEscalationLevel() {
		return escalationLevel;
	}

	public AddRecordingRequest setEscalationLevel(String escalationLevel) {
		this.escalationLevel = escalationLevel;

		return this;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public AddRecordingRequest setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;

		return this;
	}

	public String getResolution() {
		return resolution;
	}

	public AddRecordingRequest setResolution(String resolution) {
		this.resolution = resolution;
		
		return this;
	}

	public String getCompany() {
		return company;
	}

	public AddRecordingRequest setCompany(String company) {
		this.company = company;
		
		return this;
	}

	public String getSite() {
		return site;
	}

	public AddRecordingRequest setSite(String site) {
		this.site = site;
		
		return this;
	}
	
	public String[] getDescriptionArray() {
		return this.descriptionArray;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public AddRecordingRequest setRouteTo(String routeTo) {
		this.routeTo = routeTo;
		
		return this;
	}

	@Override
	public String toString() {
		return "AddRecordingRequest [status=" + status + ", requestType=" + requestType + ", account=" + account
				+ ", company=" + company + ", site=" + site + ", siteNumber=" + siteNumber + ", originatedBy="
				+ originatedBy + ", reportedBy=" + reportedBy + ", routeNumber=" + routeNumber + ", truckNumber="
				+ truckNumber + ", employeeNumber=" + employeeNumber + ", description=" + description + ", username="
				+ username + ", priority=" + priority + ", systemGenerated=" + systemGenerated + ", escalationLevel="
				+ escalationLevel + ", assignedTo=" + assignedTo + ", resolution=" + resolution + ", descriptionArray="
				+ Arrays.toString(descriptionArray) + ", routeTo=" + routeTo + "]";
	}
	
}
