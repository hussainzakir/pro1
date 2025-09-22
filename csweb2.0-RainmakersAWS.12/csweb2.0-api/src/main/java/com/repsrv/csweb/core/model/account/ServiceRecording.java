package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.repsrv.csweb.core.util.AS400DateUtil.format10DigitDate;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceRecording {

	@JsonProperty("AccountID")
	private String account;
	
	@JsonProperty("CompanyID")
	private String company;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("AssetGroup")
	private String assetGroup;
	
	@JsonProperty("AssignedToUser")
	private String assignedToUser;
	
	@JsonProperty("ContainerDescription")
	private String containerDescription;
	
	@JsonProperty("ContainerType")
	private String containerType;
	
	@JsonProperty("ExtraLiftsFlag")
	private String extraLiftFlag;
	
	@JsonProperty("LastUpdateDate")
	private String lastUpdateDate;
	
	@JsonProperty("LastUpdateTime")
	private String lastUpdateTime;
	
	@JsonProperty("LastUpdateUser")
	private String lastUpdateUser;
	
	@JsonProperty("NumberOfLifts")
	private int numberOfLifts;
	
	@JsonProperty("OriginalRecordingType")
	private String originalRecordingType;
	
	@JsonProperty("OriginalUser")
	private String originalUser;
	
	@JsonProperty("PurchaseOrderNumber")
	private String purchaseOrderNumber;
	
	@JsonProperty("RecordClosedFlag")
	private String recordClosedFlag;
	
	@JsonProperty("RecordingPriority")
	private String recordingPriority;
	
	@JsonProperty("ReportedByName")
	private String reportedByName;
	
	@JsonProperty("RouteNumber")
	private String routeNumber;
	
	@JsonProperty("ScheduleCompletionDate")
	private String scheduleCompletionDate;
	
	@JsonProperty("ServiceCode")
	private String serviceCode;
	
	@JsonProperty("ServiceDescription")
	private String serviceDescription;
	
	@JsonProperty("ServiceRecordingCompositeKey")
	private String serviceRecordingCompositeKey;
	
	@JsonProperty("ServiceRecordDate")
	private String serviceRecordDate;
	
	@JsonProperty("ServiceRecordNote")
	private String ServiceRecordNote;
	
	@JsonProperty("ServiceRecordNumber")
	private int serviceRecordingNumber;
	
	@JsonProperty("ServiceRequestCompositeKey")
	private String serviceRequestCompositeKey;
	
	@JsonProperty("ServiceRequestNumber")
	private String serviceRequestNumber;
	
	@JsonProperty("ServiceRequestScheduled")
	private String serviceRequestScheduled;
	
	@JsonProperty("ServiceResolutionNote")
	private String serviceResolutionNote;
	
	@JsonProperty("SiteCompositeKey")
	private String siteCompositeKey;
	
	@JsonProperty("SiteID")
	private String siteId;
	
	@JsonProperty("TimeRequestedAfter")
	private int timeRequestedAfter;
	
	@JsonProperty("TimeRequestedBefore")
	private int timeRequestedBefore;
	
	@JsonProperty("TransactionCode")
	private String transactionCode;
	
	@JsonProperty("TransactionDescription")
	private String transactionDescription;
	
	@JsonProperty("UnscheduledRequestNote")
	private String unscheduledRequestNote;

	public ServiceRecording() {}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssetGroup() {
		return assetGroup;
	}

	public void setAssetGroup(String assetGroup) {
		this.assetGroup = assetGroup;
	}

	public String getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(String assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public String getContainerDescription() {
		return containerDescription;
	}

	public void setContainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getExtraLiftFlag() {
		return extraLiftFlag;
	}

	public void setExtraLiftFlag(String extraLiftFlag) {
		this.extraLiftFlag = extraLiftFlag;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public int getNumberOfLifts() {
		return numberOfLifts;
	}

	public void setNumberOfLifts(int numberOfLifts) {
		this.numberOfLifts = numberOfLifts;
	}

	public String getOriginalRecordingType() {
		return originalRecordingType;
	}

	public void setOriginalRecordingType(String originalRecordingType) {
		this.originalRecordingType = originalRecordingType;
	}

	public String getOriginalUser() {
		return originalUser;
	}

	public void setOriginalUser(String originalUser) {
		this.originalUser = originalUser;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getRecordClosedFlag() {
		return recordClosedFlag;
	}

	public void setRecordClosedFlag(String recordClosedFlag) {
		this.recordClosedFlag = recordClosedFlag;
	}

	public String getRecordingPriority() {
		return recordingPriority;
	}

	public void setRecordingPriority(String recordingPriority) {
		this.recordingPriority = recordingPriority;
	}

	public String getReportedByName() {
		return reportedByName;
	}

	public void setReportedByName(String reportedByName) {
		this.reportedByName = reportedByName;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getScheduleCompletionDate() {
		return scheduleCompletionDate;
	}

	public void setScheduleCompletionDate(String scheduleCompletionDate) {
		this.scheduleCompletionDate = format10DigitDate(scheduleCompletionDate);
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceRecordingCompositeKey() {
		return serviceRecordingCompositeKey;
	}

	public void setServiceRecordingCompositeKey(String serviceRecordingCompositeKey) {
		this.serviceRecordingCompositeKey = serviceRecordingCompositeKey;
	}

	public String getServiceRecordDate() {
		return serviceRecordDate;
	}

	public void setServiceRecordDate(String serviceRecordDate) {
		this.serviceRecordDate = format10DigitDate(serviceRecordDate);
	}

	public String getServiceRecordNote() {
		return ServiceRecordNote;
	}

	public void setServiceRecordNote(String serviceRecordNote) {
		ServiceRecordNote = serviceRecordNote;
	}

	public int getServiceRecordingNumber() {
		return serviceRecordingNumber;
	}

	public void setServiceRecordingNumber(int serviceRecordingNumber) {
		this.serviceRecordingNumber = serviceRecordingNumber;
	}

	public String getServiceRequestCompositeKey() {
		return serviceRequestCompositeKey;
	}

	public void setServiceRequestCompositeKey(String serviceRequestCompositeKey) {
		this.serviceRequestCompositeKey = serviceRequestCompositeKey;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public String getServiceRequestScheduled() {
		return serviceRequestScheduled;
	}

	public void setServiceRequestScheduled(String serviceRequestScheduled) {
		this.serviceRequestScheduled = serviceRequestScheduled;
	}

	public String getServiceResolutionNote() {
		return serviceResolutionNote;
	}

	public void setServiceResolutionNote(String serviceResolutionNote) {
		this.serviceResolutionNote = serviceResolutionNote;
	}

	public String getSiteCompositeKey() {
		return siteCompositeKey;
	}

	public void setSiteCompositeKey(String siteCompositeKey) {
		this.siteCompositeKey = siteCompositeKey;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public int getTimeRequestedAfter() {
		return timeRequestedAfter;
	}

	public void setTimeRequestedAfter(int timeRequestedAfter) {
		this.timeRequestedAfter = timeRequestedAfter;
	}

	public int getTimeRequestedBefore() {
		return timeRequestedBefore;
	}

	public void setTimeRequestedBefore(int timeRequestedBefore) {
		this.timeRequestedBefore = timeRequestedBefore;
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

	public String getUnscheduledRequestNote() {
		return unscheduledRequestNote;
	}

	public void setUnscheduledRequestNote(String unscheduledRequestNote) {
		this.unscheduledRequestNote = unscheduledRequestNote;
	}
	
	
	
}
