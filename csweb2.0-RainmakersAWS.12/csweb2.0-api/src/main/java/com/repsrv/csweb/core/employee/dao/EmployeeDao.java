package com.repsrv.csweb.core.employee.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface EmployeeDao extends InfoProDao{

	String getCompanyEmployees(@Param("company")String company, 
			@Param("result") StoredProcCallResult result);
}
