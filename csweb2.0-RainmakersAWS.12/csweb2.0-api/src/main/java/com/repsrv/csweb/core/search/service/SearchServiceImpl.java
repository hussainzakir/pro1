package com.repsrv.csweb.core.search.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.model.search.AccountLookup;
import com.repsrv.csweb.core.model.search.AccountSearchRequest;
import com.repsrv.csweb.core.search.dao.SearchDao;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public List<AccountLookup> getSearchResults(String division,
			String locationId, String acctNum,
			String site, String acctSiteName,			 
			String streetAddress, String city, 
			String state, String zipcode,
			String phone, 
			// String purchaseOrderNum,
			// String openObligationBalance, String accountEmail,
			String includeClosedSites,
			String nationalAccountsOnly, String orderBy, String returnJson,
			int rows, String userId) {
		
		AccountSearchRequest request = new AccountSearchRequest();
		
		request.setDivision(division == null ? "" : division.trim().toUpperCase());
		request.setLocationId(locationId == null ? "" : locationId.trim().toUpperCase());
		request.setAcctNum(acctNum == null ? "" : acctNum.trim().toUpperCase());
		request.setSite(site == null ? "" : site.trim().toUpperCase());
		request.setAccountSiteName(acctSiteName == null ? "" : acctSiteName.trim().toUpperCase());
		request.setStreetAddress(streetAddress == null ? "" : streetAddress.trim().toUpperCase());
		request.setCity(city == null ? "" : city.trim().toUpperCase());
		request.setState(state == null ? "" : state.trim().toUpperCase());
		request.setZipcode(zipcode == null ? "" : zipcode.trim().toUpperCase());
		request.setPhone(phone == null ? "" : phone.trim().toUpperCase());
		// request.setPurchaseOrderNum(purchaseOrderNum == null ? "" : purchaseOrderNum.trim().toUpperCase());
		// request.setOpenObligationBalance(openObligationBalance == null ? "" : openObligationBalance.trim().toUpperCase());
		// request.setAccountEmail(accountEmail == null ? "" : accountEmail.trim().toUpperCase());
		request.setIncludeClosedSites(includeClosedSites == null ? "" : includeClosedSites.trim().toUpperCase());
		request.setNationalAccountsOnly(nationalAccountsOnly == null ? "" : nationalAccountsOnly.trim().toUpperCase());
		request.setOrderBy(orderBy == null ? "" : orderBy.trim().toUpperCase());
		request.setReturnJson(returnJson == null ? "" : returnJson.trim().toUpperCase());
		request.setRows(rows == 0 ? 10 : rows);
		request.setUserId(userId == null ? "" : userId.trim().toUpperCase());
		
		return searchDao.getSearchAccounts(request);
	}

}
