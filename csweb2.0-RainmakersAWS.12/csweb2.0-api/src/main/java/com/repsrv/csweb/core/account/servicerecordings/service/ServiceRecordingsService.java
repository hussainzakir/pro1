package com.repsrv.csweb.core.account.servicerecordings.service;

import java.util.List;

import com.repsrv.csweb.core.account.servicerecordings.model.AddRecordingDto;
import com.repsrv.csweb.core.model.account.MaintainRecording;
import com.repsrv.csweb.core.model.account.ServiceRecording;
import com.repsrv.csweb.core.model.codes.StandardizedCode;
import com.repsrv.csweb.core.model.codes.StandardizedCodeType;
import com.repsrv.csweb.core.support.exception.recordings.AddRecordingException;
import com.repsrv.csweb.core.support.exception.recordings.ServiceInterruptionException;

public interface ServiceRecordingsService {

	public List<ServiceRecording> getAllAccountServiceRecordings(String company, String account, String site);
	
	public List<ServiceRecording> getOpenAccountServiceRecordings(String company, String account, String site);
	
	public List<ServiceRecording> getClosedAccountServiceRecordings(String company, String account, String site);
	
	List<StandardizedCode> getRecordingTypes(String company, StandardizedCodeType code);
	
	public MaintainRecording getServiceRecording(String company, String account, String site, String serviceRecording, String date);

	/**
	 * 
	 * @param company - ie 625
	 * @param date - yyyymmdd
	 * @param user - the current user
	 * @return
	 */
	String getNextServiceRecordingNumber(String company, String date, String user);

	String createRecording(AddRecordingDto recording) throws AddRecordingException, ServiceInterruptionException;
	
	boolean isMpuFlagSet(String companyNumber, String serviceCode);
}
