package com.repsrv.csweb.core.account.servicerecordings.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.account.servicerecordings.model.AddRecordingRequest;
import com.repsrv.csweb.core.account.servicerecordings.model.F2NoteRequest;
import com.repsrv.csweb.core.account.servicerecordings.model.MpuDownRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface ServiceRecordingDao extends InfoProDao{

	String getAccountServiceRecordings(@Param("key")String compositeKey, 
			@Param("status")String status, @Param("result") StoredProcCallResult result);
	
	String getServiceRecording(@Param("key")String compositeKey, 
			@Param("result") StoredProcCallResult result);

	void getNextRecordingNumber(@Param("company") String company, @Param("user") String user,
			@Param("date") String date, @Param("result")StoredProcCallResult result);

	void addServiceRecording(@Param("request")AddRecordingRequest request); 

	void getMpuDownFlag(@Param("request")MpuDownRequest request);

	void createF2Note(@Param("request")F2NoteRequest req);
}
