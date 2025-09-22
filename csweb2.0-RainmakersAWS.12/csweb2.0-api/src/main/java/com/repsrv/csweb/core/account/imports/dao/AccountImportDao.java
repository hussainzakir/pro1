package com.repsrv.csweb.core.account.imports.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.account.openmarket.dao.ChangeScheduleReq;
import com.repsrv.csweb.core.account.openmarket.dao.ScheduleUploadRequest;
import com.repsrv.csweb.core.model.account.imports.DeleteStagingDataRequest;
import com.repsrv.csweb.core.model.account.imports.ResiContractHandoff;
import com.repsrv.csweb.core.model.account.onboarding.AobUploadHistoryRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountImportDao extends InfoProDao {
	
	void handOffResiContractUpload(@Param("request")ResiContractHandoff request);

	// Saves the data with associated metadata
	void doSaveData(@Param("metadata")String metadata, 
			@Param("tableData")String json,
			@Param("result")StoredProcCallResult result);
	
	void deleteStagingData(@Param("request")DeleteStagingDataRequest request);
	
	String getUserImportHistory(@Param("userId")String userId, 
			@Param("result")StoredProcCallResult result);

	String getUploadHistory(@Param("request") AobUploadHistoryRequest request);

	void scheduleUpload(@Param("request") ScheduleUploadRequest request);

	void changeScheduleUpload(@Param("request") ChangeScheduleReq request);
}
