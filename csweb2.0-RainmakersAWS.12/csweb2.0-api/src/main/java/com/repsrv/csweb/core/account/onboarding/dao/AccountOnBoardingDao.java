package com.repsrv.csweb.core.account.onboarding.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.account.imports.DeleteStagingDataRequest;
import com.repsrv.csweb.core.model.account.onboarding.AobCreateProjectRequest;
import com.repsrv.csweb.core.model.account.onboarding.AobHandoff;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountOnBoardingDao extends InfoProDao {

    void handOffAobUpload(@Param("request")AobHandoff request);

    	// Saves the data with associated metadata
	void doSaveData(@Param("metadata")String metadata, 
			@Param("tableData")String json,
			@Param("result")StoredProcCallResult result);
	
	void deleteStagingData(@Param("request")DeleteStagingDataRequest request);
	
	String getUserImportHistory(@Param("userId")String userId, 
			@Param("result")StoredProcCallResult result);

	void aobProjectCreation(@Param("request") AobCreateProjectRequest request);

	void aobSaveProjectData(@Param("request") String request, @Param("result") StoredProcCallResult result);

	void aobRequestDriver(@Param("request") AobRequestDriverReq request);

	String getErrorData(@Param("projectId")String projectId, 
	@Param("request")StoredProcCallResult result);

}

