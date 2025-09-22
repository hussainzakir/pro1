package com.repsrv.csweb.core.model.container;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerServiceInfo {

	@JsonProperty("Disposal_Code")
	private String disposalCode;
	
	@JsonProperty("Disposal_Price_Code")
	private String priceCode;
	
	@JsonProperty("Disposable_Code_Desc")
	private String priceCodeDesc;
	
	@JsonProperty("Grid_Number")
	private String gridNumber;
	
	@JsonProperty("Major_Day")
	private String gridMajorDay;
	
	@JsonProperty("Minor_Day")
	private String gridMinorDay;
	
	@JsonProperty("Municipal/Franchise_Contr")
	private String gridDesc;
	
	@JsonProperty("Monday_Required")
	private String requiredMon;
	
	@JsonProperty("Tuesday_Required")
	private String requiredTues;
	
	@JsonProperty("Wednesday_Required")
	private String requiredWed;
	
	@JsonProperty("Thursday_Required")
	private String requiredThurs;
	
	@JsonProperty("Friday_Required")
	private String requiredFri;
	
	@JsonProperty("Saturday_Required")
	private String requiredSat;
	
	@JsonProperty("Sunday_Required")
	private String requiredSun;
	
	@JsonProperty("Monday_Lift_Day")
	private String liftDayMon;
	
	@JsonProperty("Tuesday_Lift_Day")
	private String liftDayTues;
	
	@JsonProperty("Wednesday_Lift_Day")
	private String liftDayWed;
	
	@JsonProperty("Thursday_Lift_Day")
	private String liftDayThurs;
	
	@JsonProperty("Friday_Lift_Day")
	private String liftDayFri;
	
	@JsonProperty("Saturday_Lift_Day")
	private String liftDaySat;
	
	@JsonProperty("Sunday_Lift_Day")
	private String liftDaySun;
	
	@JsonProperty("Monday_Route")
	private String routeMon;
	
	@JsonProperty("Tuesday_Route")
	private String routeTues;
	
	@JsonProperty("Wednesday_Route")
	private String routeWed;
	
	@JsonProperty("Thursday_Route")
	private String routeThurs;
	
	@JsonProperty("Friday_Route")
	private String routeFri;
	
	@JsonProperty("Saturday_Route")
	private String routeSat;
	
	@JsonProperty("Sunday_Route")
	private String routeSun;
	
	@JsonProperty("Total_Lifts")
	private int totalLifts;
	
	@JsonProperty("Period_Length")
	private int periodLength;
	
	@JsonProperty("Minimum_#_Of_Lifts")
	private int minNumberOfLifts;
	
	@JsonProperty("Period_Unit")
	private String periodUnit;
	
	@JsonProperty("Special_Handling_Flag")
	private String specialHandlingCode;
	
	@JsonProperty("Special_CD_Desc")
	private String specialHandlingCodeDesc;
	
	@JsonProperty("Weight_Limit")
	private String weightLimit;

	public String getDisposalCode() {
		return disposalCode;
	}

	public void setDisposalCode(String disposalCode) {
		this.disposalCode = disposalCode;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public String getPriceCodeDesc() {
		return priceCodeDesc;
	}

	public void setPriceCodeDesc(String priceCodeDesc) {
		this.priceCodeDesc = priceCodeDesc;
	}

	public String getGridNumber() {
		return gridNumber;
	}

	public void setGridNumber(String gridNumber) {
		this.gridNumber = gridNumber;
	}

	public String getGridDesc() {
		return gridDesc;
	}

	public void setGridDesc(String gridDesc) {
		this.gridDesc = gridDesc;
	}

	public String getRequiredMon() {
		return requiredMon;
	}

	public void setRequiredMon(String requiredMon) {
		this.requiredMon = requiredMon;
	}

	public String getRequiredTues() {
		return requiredTues;
	}

	public void setRequiredTues(String requiredTues) {
		this.requiredTues = requiredTues;
	}

	public String getRequiredWed() {
		return requiredWed;
	}

	public void setRequiredWed(String requiredWed) {
		this.requiredWed = requiredWed;
	}

	public String getRequiredThurs() {
		return requiredThurs;
	}

	public void setRequiredThurs(String requiredThurs) {
		this.requiredThurs = requiredThurs;
	}

	public String getRequiredFri() {
		return requiredFri;
	}

	public void setRequiredFri(String requiredFri) {
		this.requiredFri = requiredFri;
	}

	public String getRequiredSat() {
		return requiredSat;
	}

	public void setRequiredSat(String requiredSat) {
		this.requiredSat = requiredSat;
	}

	public String getRequiredSun() {
		return requiredSun;
	}

	public void setRequiredSun(String requiredSun) {
		this.requiredSun = requiredSun;
	}

	public String getLiftDayMon() {
		return liftDayMon;
	}

	public void setLiftDayMon(String liftDayMon) {
		this.liftDayMon = liftDayMon;
	}

	public String getLiftDayTues() {
		return liftDayTues;
	}

	public void setLiftDayTues(String liftDayTues) {
		this.liftDayTues = liftDayTues;
	}

	public String getLiftDayWed() {
		return liftDayWed;
	}

	public void setLiftDayWed(String liftDayWed) {
		this.liftDayWed = liftDayWed;
	}

	public String getLiftDayThurs() {
		return liftDayThurs;
	}

	public void setLiftDayThurs(String liftDayThurs) {
		this.liftDayThurs = liftDayThurs;
	}

	public String getLiftDayFri() {
		return liftDayFri;
	}

	public void setLiftDayFri(String liftDayFri) {
		this.liftDayFri = liftDayFri;
	}

	public String getLiftDaySat() {
		return liftDaySat;
	}

	public void setLiftDaySat(String liftDaySat) {
		this.liftDaySat = liftDaySat;
	}

	public String getLiftDaySun() {
		return liftDaySun;
	}

	public void setLiftDaySun(String liftDaySun) {
		this.liftDaySun = liftDaySun;
	}

	public String getRouteMon() {
		return routeMon;
	}

	public void setRouteMon(String routeMon) {
		this.routeMon = routeMon;
	}

	public String getRouteTues() {
		return routeTues;
	}

	public void setRouteTues(String routeTues) {
		this.routeTues = routeTues;
	}

	public String getRouteWed() {
		return routeWed;
	}

	public void setRouteWed(String routeWed) {
		this.routeWed = routeWed;
	}

	public String getRouteThurs() {
		return routeThurs;
	}

	public void setRouteThurs(String routeThurs) {
		this.routeThurs = routeThurs;
	}

	public String getRouteFri() {
		return routeFri;
	}

	public void setRouteFri(String routeFri) {
		this.routeFri = routeFri;
	}

	public String getRouteSat() {
		return routeSat;
	}

	public void setRouteSat(String routeSat) {
		this.routeSat = routeSat;
	}

	public String getRouteSun() {
		return routeSun;
	}

	public void setRouteSun(String routeSun) {
		this.routeSun = routeSun;
	}

	public int getTotalLifts() {
		return totalLifts;
	}

	public void setTotalLifts(int totalLifts) {
		this.totalLifts = totalLifts;
	}

	public int getPeriodLength() {
		return periodLength;
	}

	public void setPeriodLength(int periodLength) {
		this.periodLength = periodLength;
	}

	public String getPeriodUnit() {
		return periodUnit;
	}

	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}

	public String getSpecialHandlingCode() {
		return specialHandlingCode;
	}

	public void setSpecialHandlingCode(String specialHandlingCode) {
		this.specialHandlingCode = specialHandlingCode;
	}

	public String getSpecialHandlingCodeDesc() {
		return specialHandlingCodeDesc;
	}

	public void setSpecialHandlingCodeDesc(String specialHandlingCodeDesc) {
		this.specialHandlingCodeDesc = specialHandlingCodeDesc;
	}

	public String getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(String weightLimit) {
		this.weightLimit = weightLimit;
	}

	public String getGridMajorDay() {
		return gridMajorDay;
	}

	public void setGridMajorDay(String gridMajorDay) {
		this.gridMajorDay = gridMajorDay;
	}

	public String getGridMinorDay() {
		return gridMinorDay;
	}

	public void setGridMinorDay(String gridMinorDay) {
		this.gridMinorDay = gridMinorDay;
	}

	public int getMinNumberOfLifts() {
		return minNumberOfLifts;
	}

	public void setMinNumberOfLifts(int minNumberOfLifts) {
		this.minNumberOfLifts = minNumberOfLifts;
	}
	
	
	
}
