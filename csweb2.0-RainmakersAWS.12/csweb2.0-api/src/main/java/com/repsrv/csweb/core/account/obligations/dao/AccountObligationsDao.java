package com.repsrv.csweb.core.account.obligations.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountObligationsDao extends InfoProDao {

	String getObligations(@Param("company") String company, @Param("account") String account,
			@Param("type") String type, @Param("result") StoredProcCallResult result);

	String getReconciliations(@Param("company") String company, @Param("account") String account,
			@Param("type") String type, @Param("result") StoredProcCallResult result);
}
