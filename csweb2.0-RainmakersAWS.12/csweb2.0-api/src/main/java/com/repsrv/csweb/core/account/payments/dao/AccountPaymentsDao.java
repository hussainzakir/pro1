package com.repsrv.csweb.core.account.payments.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountPaymentsDao extends InfoProDao{

	String getAccountPayments(@Param("company") String company, @Param("account") String account, @Param("result") StoredProcCallResult result);

}
