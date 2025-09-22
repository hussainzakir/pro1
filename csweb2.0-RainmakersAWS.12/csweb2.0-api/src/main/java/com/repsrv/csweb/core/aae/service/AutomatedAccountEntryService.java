package com.repsrv.csweb.core.aae.service;


import java.util.List;
import java.util.Map;

import com.repsrv.csweb.core.aae.model.AaeSearchResult;
import com.repsrv.csweb.core.aae.model.ListDataEntityType;
import com.repsrv.csweb.core.aae.model.QuoteMetadata;
import com.repsrv.csweb.core.model.aae.AaeErrorReport;
import com.repsrv.csweb.core.model.aae.AaeExceptionReport;
import com.repsrv.csweb.core.model.aae.Account;
import com.repsrv.csweb.core.model.aae.AccountEdit;
import com.repsrv.csweb.core.model.aae.ContainerEdit;
import com.repsrv.csweb.core.model.aae.ContractResponse;
import com.repsrv.csweb.core.model.aae.ErrorResponse;
import com.repsrv.csweb.core.model.aae.FinalizationParams;
import com.repsrv.csweb.core.model.aae.RateCreate;
import com.repsrv.csweb.core.model.aae.RateEdit;
import com.repsrv.csweb.core.model.aae.SearchParams;
import com.repsrv.csweb.core.model.aae.SiteEdit;
import com.repsrv.csweb.core.model.listData.ListDataResponse;


public interface AutomatedAccountEntryService {
	
	Account getAccountDetail(String division, String quoteId);
	
  AaeSearchResult searchAccounts( SearchParams searchForm, String division, 
			int pageNumber, int pageSize);
  
  AaeSearchResult searchDivisionalAccounts( SearchParams searchForm, String division, 
			int pageNumber, int pageSize);

	public List<ErrorResponse> getErrorResponse(String quoteId);
	
	void updateAccountInformation(AccountEdit accountEdit);
	
	void updateSiteInformation(SiteEdit siteEdit);
	
	void updateContainerInformation(ContainerEdit containerEdit);
	
	void deleteContainerInformation(ContainerEdit containerEdit);
	
	void updateRateInformation(RateEdit rateEdit);
	
	void deleteRateInformation(RateEdit rateEdit);
	
	void createRateInformation(RateCreate rateCreate);

	void submitFinalizationInformation(FinalizationParams finalizationParams);

	Map<String,String> assignQuote(String assignee, String requestingUser, String quoteId );

	void unassignQuote(String assignee, String requestingUser, String quoteId );
	
	QuoteMetadata getQuoteMetadata(String quoteId);

	public ListDataResponse getListDataOptions(String division, ListDataEntityType selected);
	
	ContractResponse getContractInfo(String quoteId);

	public String getFinalAccountNumber(String projectId);

	public void resetFinalAccountNumber(String quoteId);
	
	void deleteFsrInformation(ContainerEdit containerEdit);
	
	void getAaeErrorReport(AaeErrorReport errorReport);
	
	void getAaeExceptionReport(AaeExceptionReport exceptionReport);
}
