package com.repsrv.csweb.core.codes.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface StandardizedCodesDao extends InfoProDao{

	String getStandardizedCodesByType(@Param("company")String company, 
			@Param("tableName")String tableName,
			@Param("type")String type, 
			@Param("status")String status, 
			@Param("result") StoredProcCallResult result);
}
