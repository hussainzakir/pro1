package com.repsrv.csweb.core.massuploads.dao;

import java.util.List;
import java.util.Map;

import com.repsrv.csweb.core.mybatis.type.custom.handlers.MassUploadErrorDataHandler;
import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.massupload.MuHistory;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.CbsDao;
import org.apache.ibatis.session.ResultHandler;

public interface MassUploadsCbsDao extends CbsDao{

	List<MuHistory> getHistory(@Param("username")String username, @Param("result")StoredProcCallResult result);

	MuHistory getHistoryEntry(@Param("logId") String logId, @Param("result") StoredProcCallResult result);

	void saveUpload(@Param("templateId")Integer templateId, @Param("uploadJson")String json, @Param("result")StoredProcCallResult result);

	void getErrorData(@Param("schema")String schema, @Param("table")String table, ResultHandler<Map<String, Object>> handler);

	void updateQueueRecord(@Param("logId") String logId, @Param("status") int status,
						   @Param("s3Path") String s3Path, @Param("result") StoredProcCallResult result);

}
