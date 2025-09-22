package com.repsrv.csweb.core.model.account;

import static com.repsrv.csweb.core.util.AS400DateUtil.format8DigitDate;
import static com.repsrv.csweb.core.util.TimeUtil.formatFourDigitTime;
import static com.repsrv.csweb.core.util.TimeUtil.formatSixDigitTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MaintainRecording {
	
	@JsonProperty("ReportedbyName")
	public String reportedByName;
	
	@JsonProperty("ServiceRecordNumber")
	public String serviceRecordNumber;
	
	@JsonProperty("Status")
	public String status;
	
	@JsonProperty("RecordingPriority")
	public String recordingPriority;
	
	@JsonProperty("ScheduledCompletionDate")
	public String scheduledCompletionDate;
	
	@JsonProperty("TransactionCode")
	public String transactionCode;
	
	@JsonProperty("TransactionDescription")
	public String transactionDescription;
	
	@JsonProperty("AssignedToUser")
	public String assignedToUser;
	
	@JsonProperty("SatelliteLocation")
	public String satelliteLocation;
	
	@JsonProperty("ServiceRecordNote")
	public String serviceRecordNote;
	
	@JsonProperty("ServiceResolutionNote")
	public String serviceResolutionNote;
	
	@JsonProperty("FirstName")
	public String firstName;
	
	@JsonProperty("LastName")
	public String lastName;
	
	@JsonProperty("OriginalServiceCode")
	public String originalServiceCode;
	
	@JsonProperty("EventDate")
	public String eventDate;
	
	@JsonProperty("EventTime")
	public String eventTime;
	
	@JsonProperty("OriginalUser")
	public String originalUser;
	
	@JsonProperty("LastUpdateDate")
	public String lastUpdateDate;
	
	@JsonProperty("LastUpdateTime")
	public String lastUpdateTime;
	
	@JsonProperty("LastUpdateUser")
	public String lastUpdateUser;
	
	public String getServiceRecordNumber() {
		return serviceRecordNumber;
	}

	public void setServiceRecordNumber(String serviceRecordNumber) {
		this.serviceRecordNumber = serviceRecordNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordingPriority() {
		return recordingPriority;
	}

	public void setRecordingPriority(String recordingPriority) {
		this.recordingPriority = recordingPriority;
	}

	public String getScheduledCompletionDate() {
		return scheduledCompletionDate;
	}

	public void setScheduledCompletionDate(String scheduledCompletionDate) {
		this.scheduledCompletionDate = format8DigitDate(scheduledCompletionDate);
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(String assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public String getSatelliteLocation() {
		return satelliteLocation;
	}

	public void setSatelliteLocation(String satelliteLocation) {
		this.satelliteLocation = satelliteLocation;
	}

	public String getServiceRecordNote() {
		return serviceRecordNote;
	}

	public void setServiceRecordNote(String serviceRecordNote) {
		this.serviceRecordNote = serviceRecordNote;
	}

	public String getServiceResolutionNote() {
		return serviceResolutionNote;
	}

	public void setServiceResolutionNote(String serviceResolutionNote) {
		this.serviceResolutionNote = serviceResolutionNote;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOriginalServiceCode() {
		return originalServiceCode;
	}

	public void setOriginalServiceCode(String originalServiceCode) {
		this.originalServiceCode = originalServiceCode;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = format8DigitDate(eventDate);
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = formatFourDigitTime(eventTime);
	}

	public String getOriginalUser() {
		return originalUser;
	}

	public void setOriginalUser(String originalUser) {
		this.originalUser = originalUser;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = format8DigitDate(lastUpdateDate);
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = formatSixDigitTime(lastUpdateTime);
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getReportedByName() {
		return reportedByName;
	}

	public void setReportedByName(String reportedByName) {
		this.reportedByName = reportedByName;
	}
}
