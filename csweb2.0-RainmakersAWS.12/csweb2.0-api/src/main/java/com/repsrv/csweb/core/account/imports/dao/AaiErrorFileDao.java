package com.repsrv.csweb.core.account.imports.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AaiErrorFileDao extends InfoProDao {

	String getTableRows(@Param("payload")String payload, 
			@Param("request")StoredProcCallResult result);
}
