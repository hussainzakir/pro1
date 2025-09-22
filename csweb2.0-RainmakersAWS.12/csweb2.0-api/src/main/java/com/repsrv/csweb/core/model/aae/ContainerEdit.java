package com.repsrv.csweb.core.model.aae;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerEdit {
	
	@JsonProperty("containerStagingId")
    private String stagingId;
	
	private String companyNumber;
	
    private String containerGroup;
    
    private String accountType;
    
    private String containerType;
    
    private String containerSize;
    
    private String compactorFlag;
    
    private String containerQtyOrder;

	private String routeRequestedDelivery;
    
    private String containerQtyOnSite;
    
    private String closeDate;
    
    private String containerStatus;
    
    private String specialHandling;
    
    private String specialHandlingDescription;
    
    private String containerOwned;
    
    private String purchaseOrderRequired;
    
    private String oncallFlag;
    
    private String recurringChargeFrequency;
    
    private String revenueDistributionCode;
    
    private String sharedContainerID;
    
    private String gridNumber;
    
    private String contractGroupNumber;
    
    private String contractNumber;
    
    private String originalStartDate;
    
    private String startDate;
    
    private String removalDate;
    
    private String minNumberOfLifts;
    
    private String totalLifts;
    
	private String mondayRequired;
	
	private String tuesdayRequired;
	
	private String wednesdayRequired;
	
	private String thursdayRequired;
	
	private String fridayRequired;
	
	private String saturdayRequired;
	
	private String sundayRequired;
	
	private String mondayLiftDay;
	
	private String tuesdayLiftDay;
	
	private String wednesdayLiftDay;
	
	private String thursdayLiftDay;
	
	private String fridayLiftDay;
	
	private String saturdayLiftDay;
	
	private String sundayLiftDay;
    
    private String periodLength;
    
    private String peoridUnit;
    
    private String disposalCode;
    
    private String disposalPriceCode;
    
    private String unitOfMeasure;
    
	private String weightLimit;
	
	private String recurMnthsAdvBill;
	
	private String disposalRateRestriction;
	
	private String cityAccountNumber;
	
	private String receiptRequired;
	
	private String billedToDate;
	
	private String remoteMonitorFlag;
	
	private String rateRestrOperDate;
	
	private String lastServiceDate;
	
	private String containerIdCode;	
    
    private String stopCode;
    
    private String rateType;

	private String compactor;
    
	@JsonProperty("SDtransactionCode")
	private String sdtransactionCode;
	
	@JsonProperty("SDreasonCode")
	private String sdReasonCode;
	
	@JsonProperty("SDtransReasonDescription")
	private String sdTransReasonDescription;
	
	@JsonProperty("SDservicingRep")
	private String sdServicingRep;
	
	@JsonProperty("SDservicingRepName")
	private String sdServicingRepName;
	
	@JsonProperty("SDsigningRep")
	private String sdSigningRep;
	
	@JsonProperty("SDsigningRepName")
	private String sdSigningRepName;
	
	@JsonProperty("SDcompetitorCode")
	private String sdCompetitorCode;
	
	@JsonProperty("SDCSAContract#")
	private String sdCsaContractNumber;
	
	@JsonProperty("SDleadSource")
	private String sdLeadSource;
	
	@JsonProperty("SDleadSourceDescription")
	private String sdLeadSourceDescription;
	
	private String routeCreateDelUR;
	
	private String routeSchPermService;
	
	private String routeUrEffectiveDate;
	
	private String routeUrPlanDate;
	
	private String routeUrSchEffectiveDate;
	
	private String routeUrSchPlanDate;
	
	private String routePOnumber;
	
	private String routeNotes;
	
	private String routeWeekDelayService;
	
	private String districtCode;
	
	private String fsrEffectiveDate;
	
	private String fsrQtyOnOrder;
	
	private String fsrTotalLifts;
	
	private String fsrTimeFrame;
	
	private String fsrTimeFrameReference;
	
	private String fsrOnCall;
	
	private String fsrMinLifts;
	
	private String idCode;
    
	public String getStagingId() {
		return stagingId;
	}
	public void setStagingId(String stagingId) {
		this.stagingId = stagingId;
	}
	public String getContainerGroup() {
		return containerGroup;
	}
	public void setContainerGroup(String containerGroup) {
		this.containerGroup = containerGroup;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getContainerSize() {
		return containerSize;
	}
	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}
	public String getCompactorFlag() {
		return compactorFlag;
	}
	public void setCompactorFlag(String compactorFlag) {
		this.compactorFlag = compactorFlag;
	}
	public String getrouteRequestedDelivery() {
		return routeRequestedDelivery;
	}
	public void setrouteRequestedDelivery(String routeRequestedDelivery) {
		this.routeRequestedDelivery = routeRequestedDelivery;
	}

	public String getContainerQtyOrder() {
		return containerQtyOrder;
	}
	public void setContainerQtyOrder(String containerQtyOrder) {
		this.containerQtyOrder = containerQtyOrder;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getSpecialHandling() {
		return specialHandling;
	}
	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}
	public String getContainerOwned() {
		return containerOwned;
	}
	public void setContainerOwned(String containerOwned) {
		this.containerOwned = containerOwned;
	}
	public String getPurchaseOrderRequired() {
		return purchaseOrderRequired;
	}
	public void setPurchaseOrderRequired(String purchaseOrderRequired) {
		this.purchaseOrderRequired = purchaseOrderRequired;
	}
	public String getOncallFlag() {
		return oncallFlag;
	}
	public void setOncallFlag(String oncallFlag) {
		this.oncallFlag = oncallFlag;
	}
	public String getRecurringChargeFrequency() {
		return recurringChargeFrequency;
	}
	public void setRecurringChargeFrequency(String recurringChargeFrequency) {
		this.recurringChargeFrequency = recurringChargeFrequency;
	}
	public String getRevenueDistributionCode() {
		return revenueDistributionCode;
	}
	public void setRevenueDistributionCode(String revenueDistributionCode) {
		this.revenueDistributionCode = revenueDistributionCode;
	}
	public String getSharedContainerID() {
		return sharedContainerID;
	}
	public void setSharedContainerID(String sharedContainerID) {
		this.sharedContainerID = sharedContainerID;
	}
	public String getGridNumber() {
		return gridNumber;
	}
	public void setGridNumber(String gridNumber) {
		this.gridNumber = gridNumber;
	}
	public String getContractGroupNumber() {
		return contractGroupNumber;
	}
	public void setContractGroupNumber(String contractGroupNumber) {
		this.contractGroupNumber = contractGroupNumber;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getOriginalStartDate() {
		return originalStartDate;
	}
	public void setOriginalStartDate(String originalStartDate) {
		this.originalStartDate = originalStartDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRemovalDate() {
		return removalDate;
	}
	public void setRemovalDate(String removalDate) {
		this.removalDate = removalDate;
	}
	public String getMinNumberOfLifts() {
		return minNumberOfLifts;
	}
	public void setMinNumberOfLifts(String minNumberOfLifts) {
		this.minNumberOfLifts = minNumberOfLifts;
	}
	public String getPeriodLength() {
		return periodLength;
	}
	public void setPeriodLength(String periodLength) {
		this.periodLength = periodLength;
	}
	public String getPeoridUnit() {
		return peoridUnit;
	}
	public void setPeoridUnit(String peoridUnit) {
		this.peoridUnit = peoridUnit;
	}
	public String getDisposalCode() {
		return disposalCode;
	}
	public void setDisposalCode(String disposalCode) {
		this.disposalCode = disposalCode;
	}
	public String getDisposalPriceCode() {
		return disposalPriceCode;
	}
	public void setDisposalPriceCode(String disposalPriceCode) {
		this.disposalPriceCode = disposalPriceCode;
	}
	public String getStopCode() {
		return stopCode;
	}
	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getSdtransactionCode() {
		return sdtransactionCode;
	}
	public void setSdtransactionCode(String sdtransactionCode) {
		this.sdtransactionCode = sdtransactionCode;
	}
	public String getSdReasonCode() {
		return sdReasonCode;
	}
	public void setSdReasonCode(String sdReasonCode) {
		this.sdReasonCode = sdReasonCode;
	}
	public String getSdTransReasonDescription() {
		return sdTransReasonDescription;
	}
	public void setSdTransReasonDescription(String sdTransReasonDescription) {
		this.sdTransReasonDescription = sdTransReasonDescription;
	}
	public String getSdServicingRep() {
		return sdServicingRep;
	}
	public void setSdServicingRep(String sdServicingRep) {
		this.sdServicingRep = sdServicingRep;
	}
	public String getSdServicingRepName() {
		return sdServicingRepName;
	}
	public void setSdServicingRepName(String sdServicingRepName) {
		this.sdServicingRepName = sdServicingRepName;
	}
	public String getSdSigningRep() {
		return sdSigningRep;
	}
	public void setSdSigningRep(String sdSigningRep) {
		this.sdSigningRep = sdSigningRep;
	}
	public String getSdSigningRepName() {
		return sdSigningRepName;
	}
	public void setSdSigningRepName(String sdSigningRepName) {
		this.sdSigningRepName = sdSigningRepName;
	}
	public String getSdCompetitorCode() {
		return sdCompetitorCode;
	}
	public void setSdCompetitorCode(String sdCompetitorCode) {
		this.sdCompetitorCode = sdCompetitorCode;
	}
	public String getSdCsaContractNumber() {
		return sdCsaContractNumber;
	}
	public void setSdCsaContractNumber(String sdCsaContractNumber) {
		this.sdCsaContractNumber = sdCsaContractNumber;
	}
	public String getSdLeadSource() {
		return sdLeadSource;
	}
	public void setSdLeadSource(String sdLeadSource) {
		this.sdLeadSource = sdLeadSource;
	}
	public String getSdLeadSourceDescription() {
		return sdLeadSourceDescription;
	}
	public void setSdLeadSourceDescription(String sdLeadSourceDescription) {
		this.sdLeadSourceDescription = sdLeadSourceDescription;
	}
	public String getContainerQtyOnSite() {
		return containerQtyOnSite;
	}
	public void setContainerQtyOnSite(String containerQtyOnSite) {
		this.containerQtyOnSite = containerQtyOnSite;
	}
	public String getContainerStatus() {
		return containerStatus;
	}
	public void setContainerStatus(String containerStatus) {
		this.containerStatus = containerStatus;
	}
	public String getSpecialHandlingDescription() {
		return specialHandlingDescription;
	}
	public void setSpecialHandlingDescription(String specialHandlingDescription) {
		this.specialHandlingDescription = specialHandlingDescription;
	}
	public String getMondayRequired() {
		return mondayRequired;
	}
	public void setMondayRequired(String mondayRequired) {
		this.mondayRequired = mondayRequired;
	}
	public String getTuesdayRequired() {
		return tuesdayRequired;
	}
	public void setTuesdayRequired(String tuesdayRequired) {
		this.tuesdayRequired = tuesdayRequired;
	}
	public String getWednesdayRequired() {
		return wednesdayRequired;
	}
	public void setWednesdayRequired(String wednesdayRequired) {
		this.wednesdayRequired = wednesdayRequired;
	}
	public String getThursdayRequired() {
		return thursdayRequired;
	}
	public void setThursdayRequired(String thursdayRequired) {
		this.thursdayRequired = thursdayRequired;
	}
	public String getFridayRequired() {
		return fridayRequired;
	}
	public void setFridayRequired(String fridayRequired) {
		this.fridayRequired = fridayRequired;
	}
	public String getSaturdayRequired() {
		return saturdayRequired;
	}
	public void setSaturdayRequired(String saturdayRequired) {
		this.saturdayRequired = saturdayRequired;
	}
	public String getSundayRequired() {
		return sundayRequired;
	}
	public void setSundayRequired(String sundayRequired) {
		this.sundayRequired = sundayRequired;
	}
	public String getMondayLiftDay() {
		return mondayLiftDay;
	}
	public void setMondayLiftDay(String mondayLiftDay) {
		this.mondayLiftDay = mondayLiftDay;
	}
	public String getTuesdayLiftDay() {
		return tuesdayLiftDay;
	}
	public void setTuesdayLiftDay(String tuesdayLiftDay) {
		this.tuesdayLiftDay = tuesdayLiftDay;
	}
	public String getWednesdayLiftDay() {
		return wednesdayLiftDay;
	}
	public void setWednesdayLiftDay(String wednesdayLiftDay) {
		this.wednesdayLiftDay = wednesdayLiftDay;
	}
	public String getThursdayLiftDay() {
		return thursdayLiftDay;
	}
	public void setThursdayLiftDay(String thursdayLiftDay) {
		this.thursdayLiftDay = thursdayLiftDay;
	}
	public String getFridayLiftDay() {
		return fridayLiftDay;
	}
	public void setFridayLiftDay(String fridayLiftDay) {
		this.fridayLiftDay = fridayLiftDay;
	}
	public String getSaturdayLiftDay() {
		return saturdayLiftDay;
	}
	public void setSaturdayLiftDay(String saturdayLiftDay) {
		this.saturdayLiftDay = saturdayLiftDay;
	}
	public String getSundayLiftDay() {
		return sundayLiftDay;
	}
	public void setSundayLiftDay(String sundayLiftDay) {
		this.sundayLiftDay = sundayLiftDay;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public String getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(String weightLimit) {
		this.weightLimit = weightLimit;
	}
	public String getRecurMnthsAdvBill() {
		return recurMnthsAdvBill;
	}
	public void setRecurMnthsAdvBill(String recurMnthsAdvBill) {
		this.recurMnthsAdvBill = recurMnthsAdvBill;
	}
	public String getDisposalRateRestriction() {
		return disposalRateRestriction;
	}
	public void setDisposalRateRestriction(String disposalRateRestriction) {
		this.disposalRateRestriction = disposalRateRestriction;
	}
	public String getCityAccountNumber() {
		return cityAccountNumber;
	}
	public void setCityAccountNumber(String cityAccountNumber) {
		this.cityAccountNumber = cityAccountNumber;
	}
	public String getReceiptRequired() {
		return receiptRequired;
	}
	public void setReceiptRequired(String receiptRequired) {
		this.receiptRequired = receiptRequired;
	}
	public String getBilledToDate() {
		return billedToDate;
	}
	public void setBilledToDate(String billedToDate) {
		this.billedToDate = billedToDate;
	}
	public String getRemoteMonitorFlag() {
		return remoteMonitorFlag;
	}
	public void setRemoteMonitorFlag(String remoteMonitorFlag) {
		this.remoteMonitorFlag = remoteMonitorFlag;
	}
	public String getRateRestrOperDate() {
		return rateRestrOperDate;
	}
	public void setRateRestrOperDate(String rateRestrOperDate) {
		this.rateRestrOperDate = rateRestrOperDate;
	}
	public String getLastServiceDate() {
		return lastServiceDate;
	}
	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	public String getContainerIdCode() {
		return containerIdCode;
	}
	public void setContainerIdCode(String containerIdCode) {
		this.containerIdCode = containerIdCode;
	}
	public String getRouteCreateDelUR() {
		return routeCreateDelUR;
	}
	public void setRouteCreateDelUR(String routeCreateDelUR) {
		this.routeCreateDelUR = routeCreateDelUR;
	}
	public String getRouteSchPermService() {
		return routeSchPermService;
	}
	public void setRouteSchPermService(String routeSchPermService) {
		this.routeSchPermService = routeSchPermService;
	}
	public String getRouteUrEffectiveDate() {
		return routeUrEffectiveDate;
	}
	public void setRouteUrEffectiveDate(String routeUrEffectiveDate) {
		this.routeUrEffectiveDate = routeUrEffectiveDate;
	}
	public String getRouteUrPlanDate() {
		return routeUrPlanDate;
	}
	public void setRouteUrPlanDate(String routeUrPlanDate) {
		this.routeUrPlanDate = routeUrPlanDate;
	}
	public String getRouteUrSchEffectiveDate() {
		return routeUrSchEffectiveDate;
	}
	public void setRouteUrSchEffectiveDate(String routeUrSchEffectiveDate) {
		this.routeUrSchEffectiveDate = routeUrSchEffectiveDate;
	}
	public String getRouteUrSchPlanDate() {
		return routeUrSchPlanDate;
	}
	public void setRouteUrSchPlanDate(String routeUrSchPlanDate) {
		this.routeUrSchPlanDate = routeUrSchPlanDate;
	}
	public String getRoutePOnumber() {
		return routePOnumber;
	}
	public void setRoutePOnumber(String routePOnumber) {
		this.routePOnumber = routePOnumber;
	}
	public String getRouteNotes() {
		return routeNotes;
	}
	public void setRouteNotes(String routeNotes) {
		this.routeNotes = routeNotes;
	}
	public String getRouteWeekDelayService() {
		return routeWeekDelayService;
	}
	public void setRouteWeekDelayService(String routeWeekDelayService) {
		this.routeWeekDelayService = routeWeekDelayService;
	}
	public String getTotalLifts() {
		return totalLifts;
	}
	public void setTotalLifts(String totalLifts) {
		this.totalLifts = totalLifts;
	}
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getCompactor() {
		return compactor;
	}
	public void setCompactor(String compactor) {
		this.compactor = compactor;
	}
	public String getFsrEffectiveDate() {
		return fsrEffectiveDate;
	}
	public void setFsrEffectiveDate(String fsrEffectiveDate) {
		this.fsrEffectiveDate = fsrEffectiveDate;
	}
	public String getFsrQtyOnOrder() {
		return fsrQtyOnOrder;
	}
	public void setFsrQtyOnOrder(String fsrQtyOnOrder) {
		this.fsrQtyOnOrder = fsrQtyOnOrder;
	}
	public String getFsrTotalLifts() {
		return fsrTotalLifts;
	}
	public void setFsrTotalLifts(String fsrTotalLifts) {
		this.fsrTotalLifts = fsrTotalLifts;
	}
	public String getFsrTimeFrame() {
		return fsrTimeFrame;
	}
	public void setFsrTimeFrame(String fsrTimeFrame) {
		this.fsrTimeFrame = fsrTimeFrame;
	}
	public String getFsrTimeFrameReference() {
		return fsrTimeFrameReference;
	}
	public void setFsrTimeFrameReference(String fsrTimeFrameReference) {
		this.fsrTimeFrameReference = fsrTimeFrameReference;
	}
	public String getFsrOnCall() {
		return fsrOnCall;
	}
	public void setFsrOnCall(String fsrOnCall) {
		this.fsrOnCall = fsrOnCall;
	}
	public String getFsrMinLifts() {
		return fsrMinLifts;
	}
	public void setFsrMinLifts(String fsrMinLifts) {
		this.fsrMinLifts = fsrMinLifts;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
		
}
