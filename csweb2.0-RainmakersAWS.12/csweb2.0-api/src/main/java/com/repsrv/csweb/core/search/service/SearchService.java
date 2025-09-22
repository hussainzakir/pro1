package com.repsrv.csweb.core.search.service;

import java.util.List;

import com.repsrv.csweb.core.model.search.AccountLookup;

public interface SearchService {

	List<AccountLookup> getSearchResults(String division,
			String locationId, String acctNum,
			String site, String acctSiteName,
			String streetAddress, String city, 
			String state, String zipCode,
			// String purchaseOrderNum,
			// String accountEmail,
			// String openObligationBalance,
			String phone, String includeClosedSites,
			String nationalAccountsOnly, String orderBy, String returnJson,
			int rows, String userId);
	
}
