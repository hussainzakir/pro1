package com.repsrv.csweb.core.account.aob3.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.account.imports.DeleteStagingDataRequest;
import com.repsrv.csweb.core.model.account.aob3.Aob3CreateProjectRequest;
import com.repsrv.csweb.core.model.account.aob3.Aob3Handoff;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface Aob3Dao extends InfoProDao {

    void handOffAobUpload(@Param("request")Aob3Handoff request);

    	// Saves the data with associated metadata
	void doSaveData(@Param("metadata")String metadata, 
			@Param("tableData")String json,
			@Param("result")StoredProcCallResult result);
	
	void deleteStagingData(@Param("request")DeleteStagingDataRequest request);
	
	String getUserImportHistory(@Param("userId")String userId, 
			@Param("result")StoredProcCallResult result);

	void aobProjectCreation(@Param("request") Aob3CreateProjectRequest request);

	void aobSaveProjectData(@Param("request") String request, @Param("result") StoredProcCallResult result);

	void aobRequestDriver(@Param("request") Aob3RequestDriverReq request);

	String getErrorData(@Param("projectId")String projectId, 
	@Param("request")StoredProcCallResult result);

}
