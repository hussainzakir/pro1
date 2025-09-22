package com.repsrv.csweb.core.account.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountDao extends InfoProDao {

	String getAccountDetail(@Param("company") String company, @Param("account") String account,
			@Param("site") String site, @Param("result") StoredProcCallResult result);

	String getAccountBalance(@Param("company") String company, @Param("account") String account,
			@Param("result") StoredProcCallResult result);
	
	String getAccountContacts(@Param("company") String company, @Param("account") String account,
			@Param("result") StoredProcCallResult result);

	String getAccountSiteContacts(@Param("company") String company, @Param("account") String account,
			@Param("site") String site, @Param("result") StoredProcCallResult result);
	
	String getAccountReage(@Param("company") String company, @Param("account") String account,
			@Param("result") StoredProcCallResult result);
	
	void updateCustomer(@Param("request") BaseRequest req);
	
	void updateCustomerContacts(@Param("request") BaseRequest req);
	
	void updateCustomerSiteContacts(@Param("request") BaseRequest req);
	
	void updateSite(@Param("request") BaseRequest req);

}
