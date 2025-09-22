package com.repsrv.csweb.core.account.openmarket.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.account.imports.DeleteStagingDataRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketCreateProjectRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketDeleteRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketHandOff;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;


public interface OpenMarketDao extends InfoProDao{

    void handOffOpenMarketUpload(@Param("request")OpenMarketHandOff request);

    	// Saves the data with associated metadata
	void doSaveData(@Param("metadata")String metadata, 
			@Param("tableData")String json,
			@Param("result")StoredProcCallResult result);
	
	void openMarketDeleteStagingData(@Param("request")OpenMarketDeleteRequest request);
	
	String getUserImportHistory(@Param("userId")String userId, 
			@Param("result")StoredProcCallResult result);

	void openMarketProjectCreation(@Param("request") OpenMarketCreateProjectRequest request);

	void openMarketSaveProjectData(@Param("request") String request, @Param("result") StoredProcCallResult result);

	void openMarketRequestDriver(@Param("request") OpenMarketRequestDriverReq request);

	String getErrorData(@Param("projectId")String projectId, 
	@Param("request")StoredProcCallResult result);

}
