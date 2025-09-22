package com.repsrv.csweb.core.account.charges.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.charges.dao.AccountChargesDao;
import com.repsrv.csweb.core.model.account.AccountCharge;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("AccountChargesService")
public class AccountChargesServiceImpl extends JsonResultService implements AccountChargesService{

	@Autowired
	private AccountChargesDao accountChargesDao;
	
	@Override
	public List<AccountCharge> getAccountCharges(String company, String account){
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.accountChargesDao.getAccountCharges(company, account, result);
		
		return getJsonDataObject(jsonString, new TypeReference<List<AccountCharge>>(){});
	}
}
