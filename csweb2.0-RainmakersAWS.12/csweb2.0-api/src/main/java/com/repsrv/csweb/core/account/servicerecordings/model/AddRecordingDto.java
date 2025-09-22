	package com.repsrv.csweb.core.account.servicerecordings.model;

import com.repsrv.csweb.core.model.account.RecordingStatus;
import com.repsrv.csweb.core.model.employee.Employee;

public class AddRecordingDto {

	private String company, account, site;
	private EmployeeDto employee;
	private String department;
	private String description;
	private String resolution;
	private String recordingType;
	private String subject;
	private String priority;
	private String assignTo;
	private String routeTo;
	private String escalation;
	private String completedDate;
	private boolean nationalAccount;
	private String status;
	
	public AddRecordingDto() {}
	
	public EmployeeDto getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getRecordingType() {
		return recordingType;
	}
	public void setRecordingType(String recordingType) {
		this.recordingType = recordingType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getRouteTo() {
		return routeTo;
	}
	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}
	public String getEscalation() {
		return escalation;
	}
	public void setEscalation(String escalation) {
		this.escalation = escalation;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	public boolean isNationalAccount() {
		return nationalAccount;
	}
	public void setNationalAccount(boolean nationalAccount) {
		this.nationalAccount = nationalAccount;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}

	/**
	 * IS the jobRequestType CALL
	 * @return
	 */
	public boolean isJobRequest() {
		return "CALL".equalsIgnoreCase(this.recordingType);
	}

	@Override
	public String toString() {
		return "AddRecordingDto [company=" + company + ", account=" + account + ", site=" + site + ", employee="
				+ employee + ", department=" + department + ", description=" + description + ", resolution="
				+ resolution + ", recordingType=" + recordingType + ", subject=" + subject + ", priority=" + priority
				+ ", assignTo=" + assignTo + ", routeTo=" + routeTo + ", escalation=" + escalation + ", completedDate="
				+ completedDate + ", nationalAccount=" + nationalAccount + ", status=" + status + "]";
	}



}
