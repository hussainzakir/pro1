package com.repsrv.csweb.core.account.service;

import com.repsrv.csweb.core.model.account.AccountBalance;
import com.repsrv.csweb.core.model.account.AccountContactEdit;
import com.repsrv.csweb.core.model.account.AccountDetailEdit;
import com.repsrv.csweb.core.model.account.AccountReage;
import com.repsrv.csweb.core.model.account.AccountSiteContactEdit;
import com.repsrv.csweb.core.model.account.SiteDetailEdit;
import com.repsrv.csweb.core.model.search.AccountDetail;

public interface AccountService {

	AccountDetail getAccountDetail(String company, String account, String site);
	
	AccountBalance getAccountBalance(String company, String account);
	
	AccountReage getAccountReage(String company, String account);

	/**
	 * Updated the account details 
	 * @param accountDetail - the details to persist
	 * @return true if the update succeeded, false otherwise
	 */
	void updateAccountDetail(AccountDetailEdit accountDetail);
	
	void updateAccountContacts(AccountContactEdit accountContact);
	
	void updateSiteContacts(AccountSiteContactEdit accountSiteContact);
	
	void updateSiteDetail(SiteDetailEdit siteDetail);

	AccountDetail getAccountDetailAllSites(String company, String account, String site);
	
}
