package com.repsrv.csweb.core.account.obligations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.obligations.dao.AccountObligationsDao;
import com.repsrv.csweb.core.model.account.AccountBalance;
import com.repsrv.csweb.core.model.account.AccountContact;
import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.account.AccountReconciliation;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("accountObligationService")
public class AccountObligationsServiceImpl extends JsonResultService implements AccountObligationsService {

	@Autowired
	private AccountObligationsDao obligationsDao;

	/**
	 * 
	 * @param company
	 * @param account
	 * @param type - C or O (closed or open)
	 * @return
	 */
	private List<AccountObligation> getObligations(String company, String account, String type){
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.obligationsDao.getObligations(company, account, type, result);
		
		return getJsonDataObject(jsonString, new TypeReference<List<AccountObligation>>(){});
	}
	
	/**
	 * Return a list of open obligations only
	 */
	@Override
	public List<AccountObligation> getOpenObligations(String company, String account){
		return getObligations(company, account, "O");
	}
	
	/**
	 * Return a list of Closed obligations
	 */
	@Override
	public List<AccountObligation> getClosedObligations(String company, String account){
		return getObligations(company, account, "C");
	}
	
	/**
	 * 
	 * @param company
	 * @param account
	 * @param type - A or O (all or open)
	 * @return
	 */
	private List<AccountReconciliation> getReconciliations(String company, String account, String type){
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.obligationsDao.getReconciliations(company, account, type, result);
		
		return getJsonDataObject(jsonString, new TypeReference<List<AccountReconciliation>>(){});
	}
	
	/**
	 * Return a list of open reconciliations only
	 */
	@Override
	public List<AccountReconciliation> getOpenReconciliations(String company, String account){
		return getReconciliations(company, account, "O");
	}
	
	/**
	 * Return a list of all reconciliations
	 */
	@Override
	public List<AccountReconciliation> getAllReconciliations(String company, String account){
		return getReconciliations(company, account, "A");
	}

}
