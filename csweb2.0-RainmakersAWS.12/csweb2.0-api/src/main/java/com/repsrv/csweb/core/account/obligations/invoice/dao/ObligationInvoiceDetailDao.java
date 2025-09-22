package com.repsrv.csweb.core.account.obligations.invoice.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface ObligationInvoiceDetailDao extends InfoProDao {

	String getObligationInvoiceDetails(@Param("company") String company, @Param("account") String account,
			@Param("obligationId") String obligationId, @Param("result") StoredProcCallResult result);
}
