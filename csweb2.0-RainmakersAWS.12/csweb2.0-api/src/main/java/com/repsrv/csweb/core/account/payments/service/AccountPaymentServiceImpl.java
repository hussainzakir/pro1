package com.repsrv.csweb.core.account.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.payments.dao.AccountPaymentsDao;
import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.account.AccountPayment;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("accountPaymentsService")
public class AccountPaymentServiceImpl extends JsonResultService implements AccountPaymentsService{

	@Autowired
	private AccountPaymentsDao paymentDao;
	
	@Override
	public List<AccountPayment> getAccountPayments(String company, String account) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString =  this.paymentDao.getAccountPayments(company, account, result);
		
		return getJsonDataObject(jsonString, new TypeReference<List<AccountPayment>>(){});
	}

}
