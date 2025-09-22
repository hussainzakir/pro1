package com.repsrv.csweb.core.account.service;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.AccountContactEditRequest;
import com.repsrv.csweb.core.account.AccountDetailEditRequest;
import com.repsrv.csweb.core.account.SiteContactEditRequest;
import com.repsrv.csweb.core.account.SiteDetailEditRequest;
import com.repsrv.csweb.core.account.dao.AccountDao;
import com.repsrv.csweb.core.model.account.AccountBalance;
import com.repsrv.csweb.core.model.account.AccountContact;
import com.repsrv.csweb.core.model.account.AccountContactEdit;
import com.repsrv.csweb.core.model.account.AccountDetailEdit;
import com.repsrv.csweb.core.model.account.AccountReage;
import com.repsrv.csweb.core.model.account.AccountSiteContact;
import com.repsrv.csweb.core.model.account.AccountSiteContactEdit;
import com.repsrv.csweb.core.model.account.SiteDetailEdit;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.search.AccountDetail;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;



@Service("accountService")
public class AccountServiceImpl extends JsonResultService implements AccountService{

	private static final String ALL_SITES = " ";
	
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public AccountDetail getAccountDetailAllSites(String company, String account, String site) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.accountDao.getAccountDetail(company, account, ALL_SITES, result);
		
		return hydrateAccountDetail(company, account, site, jsonString);
	}
	
	@Override
	public AccountDetail getAccountDetail(String company, String account, String site) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.accountDao.getAccountDetail(company, account, site, result);
		
		return hydrateAccountDetail(company, account, site, jsonString);

	}
	
	private AccountDetail hydrateAccountDetail(String company, String account, String site, String jsonString ) {
		logger.debug("Account Detail JSON is: {}",jsonString);
		
		AccountDetail detail = getJsonDataObject(jsonString, AccountDetail.class);
		if (detail != null) {
			detail.setAccountSiteContact(getAccountSiteContacts(company, account, site));
			detail.setAccountContact(getAccountContacts(company, account));
		}
		
		return detail;
	}

	@Override
	public AccountBalance getAccountBalance(String company, String account) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.accountDao.getAccountBalance(company, account, result);
		
		return getJsonDataObject(jsonString, AccountBalance.class);
	}

	public AccountSiteContact getAccountSiteContacts(String company, String account, String site) {
		StoredProcCallResult result = new StoredProcCallResult();
		String recs = this.accountDao.getAccountSiteContacts(company, account, site, result);
		logger.debug("Account site contacts JSON string result: {}", recs);
		
		List<AccountSiteContact> list =  getJsonDataObject(recs, new TypeReference<List<AccountSiteContact>>(){});

		return isEmpty(list) ? null : list.get(0);
	}
	
	public AccountContact getAccountContacts(String company, String account) {
		StoredProcCallResult result = new StoredProcCallResult();
		String recs = this.accountDao.getAccountContacts(company, account, result);
		logger.debug("Account contacts JSON string result: {}", recs);
		
		List<AccountContact> list =  getJsonDataObject(recs, new TypeReference<List<AccountContact>>(){});
		AccountContact contact = isEmpty(list) ? null : list.get(0);
		
		return contact;
	}
	
	@Override
	public AccountReage getAccountReage(String company, String account) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.accountDao.getAccountReage(company, account, result);
		logger.debug("Account Reage JSON is: {}",jsonString);
		
		return getJsonDataObject(jsonString, AccountReage.class);
	}
	
	
	@Override
	public void updateAccountDetail(AccountDetailEdit accountDetail) {
		
		AccountDetailEditRequest req = new AccountDetailEditRequest(CSWebAction.UPDATE, 
				CswebSecurityUtils.getLoggedInUser(), 
				accountDetail);
		
		this.accountDao.updateCustomer(req);
		logger.debug("updateAccountDetail JSON: {}", req.getJson());
		
		logger.info("Response AccountUpdate is: {}", req.getReturnErrorMsg());
		logger.info("Response AccountUpdate is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}

	@Override
	public void updateAccountContacts(AccountContactEdit accountContact) {
		
		AccountContactEditRequest req = new AccountContactEditRequest(CSWebAction.UPDATE, 
				CswebSecurityUtils.getLoggedInUser(), 
				accountContact);
		
		this.accountDao.updateCustomerContacts(req);
		logger.debug("updateAccountContacts JSON: {}" , req.getJson());
		
		logger.info("Response AccountContactUpdate is: {}", req.getReturnErrorMsg());
		logger.info("Response AccountContactUpdate is: {}", req.getReturnStatus());
		
		req.validateResponse();
		
	}
	
	@Override
	public void updateSiteContacts(AccountSiteContactEdit accountSiteContact) {
		
		SiteContactEditRequest req = new SiteContactEditRequest(CSWebAction.UPDATE, 
				CswebSecurityUtils.getLoggedInUser(), 
				accountSiteContact);
		
		this.accountDao.updateCustomerSiteContacts(req);
		logger.debug("updateSiteContacts JSON: {}", req.getJson());
		
		logger.info("Response SiteContactUpdate is: {}",  req.getReturnErrorMsg());
		logger.info("Response SiteContactUpdate is: {}",  req.getReturnStatus());
		
		req.validateResponse();
		
	}
	
	@Override
	public void updateSiteDetail(SiteDetailEdit siteDetail) {
		
		SiteDetailEditRequest req = new SiteDetailEditRequest(CSWebAction.UPDATE, 
				CswebSecurityUtils.getLoggedInUser(), 
				siteDetail);
		
		this.accountDao.updateSite(req);
		logger.debug("updateSiteDetail JSON: {}" , req.getJson());
		
		logger.info("Response SiteUpdate is: {}", req.getReturnErrorMsg());
		logger.info("Response SiteUpdate is: {}", req.getReturnStatus());
		
		req.validateResponse();
	}
	
	
	
}
