package com.repsrv.csweb.core.model.audit;

import java.io.Serializable;
import com.repsrv.csweb.core.util.AS400DateUtil;

public class AuditResults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cbs;
	private String serviceAccount;
	private String serviceName;
	private String locId;
	private String site;
	private String gValue;
	private String field;
	private String oldValue;
	private String newValue;
	private String date;
	private String time;
	private String userId;
	private String company;
	
	public String getCbs() {
		return cbs;
	}
	public void setCbs(String cbs) {
		this.cbs = cbs;
	}
	public String getServiceAccount() {
		return serviceAccount;
	}
	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getgValue() {
		return gValue;
	}
	public void setgValue(String gValue) {
		this.gValue = gValue;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = AS400DateUtil.format8DigitDateTo6DigitDate(date);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = AS400DateUtil.formatTime(time);
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String toCSV() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", cbs,company+" "+serviceAccount,serviceName,locId,site,gValue,field,oldValue,newValue,date,time,userId);
	}
}
