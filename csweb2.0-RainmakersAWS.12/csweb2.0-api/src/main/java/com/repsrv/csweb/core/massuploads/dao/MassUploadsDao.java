package com.repsrv.csweb.core.massuploads.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.massupload.ColumnDefinition;
import com.repsrv.csweb.core.model.massupload.MuHistory;
import com.repsrv.csweb.core.model.massupload.MuTemplate;
import com.repsrv.csweb.core.model.search.AccountDetail;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;
import org.apache.ibatis.session.ResultHandler;

public interface MassUploadsDao extends InfoProDao {

	String getTemplates(@Param("username") String username, @Param("system") String system,
			@Param("result") StoredProcCallResult result);

	List<MuHistory> getHistory(@Param("username") String username, @Param("result") StoredProcCallResult result);

	MuHistory getHistoryEntry(@Param("logId") String logId, @Param("result") StoredProcCallResult result);

	List<ColumnDefinition> getTemplateColumns(@Param("templateId") String templateId,
			@Param("result") StoredProcCallResult result);

	String getTemplate(@Param("templateId") String templateId, @Param("changeNumber") String changeNumber, @Param("username") String username, @Param("DT") String downloadTemplate, @Param("result") StoredProcCallResult result);

	MuTemplate getTemplateRS(@Param("templateId") String templateId, @Param("result") StoredProcCallResult result);

	void saveUpload(@Param("templateId") Integer templateId, @Param("uploadJson") String json,
			@Param("result") StoredProcCallResult result);

	void getErrorData(@Param("schema")String schema, @Param("table")String table, ResultHandler<Map<String, Object>> handler);

	void updateQueueRecord(@Param("logId") String logId, @Param("status") int status,
						   @Param("s3Path") String s3Path, @Param("result") StoredProcCallResult result);
}
