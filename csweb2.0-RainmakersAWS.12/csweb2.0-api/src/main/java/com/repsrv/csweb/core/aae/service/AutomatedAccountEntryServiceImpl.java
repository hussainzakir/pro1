package com.repsrv.csweb.core.aae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.repsrv.csweb.core.aae.model.*;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest.ActionType;
import com.repsrv.csweb.core.model.aae.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.aae.dao.AutomatedAccountEntryDao;

import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.listData.ListDataResponse;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("automatedAccountEntryService")
public class AutomatedAccountEntryServiceImpl extends JsonResultService implements AutomatedAccountEntryService{
	
	@Autowired
	private AutomatedAccountEntryDao aaeDao;
	
	@Override
	public Account getAccountDetail(String company, String projectId) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.aaeDao.getAccountDetail(company, projectId, result);
		
		return getJsonDataObject(jsonString, Account.class);    
	}
  
  @Override
	public AaeSearchResult searchAccounts(SearchParams searchForm, String division, int pageNumber, int pageSize) {
		AaeSearchRequest request = new AaeSearchRequest(searchForm, division, pageNumber, pageSize);
		
		String json = aaeDao.searchQuotes(request);
		
		log.info("rowCount total: {}", request.getTotalRowCount().intValue());
		log.info("error msg: {}", request.getErrorMessage());
		log.info("error code: {}", request.getErrorCode());
		log.debug("response json: {}", json);
		
		List<AaeSearchRecord> records = getJsonDataObject(json, new TypeReference<List<AaeSearchRecord>>(){});
		
		return AaeSearchResult.builder()
		.page(pageNumber)
		.pageSize(pageSize)
		.records(records)
		.totalRecords(request.getTotalRowCount().intValue())
		.build();
	}
  
  @Override
	public AaeSearchResult searchDivisionalAccounts(SearchParams searchForm, String division, int pageNumber, int pageSize) {
		AaeSearchRequest request = new AaeSearchRequest(searchForm, division, pageNumber, pageSize);
		
		String json = aaeDao.searchDivisionalQuotes(request);
		
		log.debug("rowCount total: {}", request.getTotalRowCount().intValue());
		log.debug("error msg: {}", request.getErrorMessage());
		log.debug("error code: {}", request.getErrorCode());
		log.debug("response json: {}", json);
		
		List<AaeSearchRecord> records = getJsonDataObject(json, new TypeReference<List<AaeSearchRecord>>(){});
		
		return AaeSearchResult.builder()
		.page(pageNumber)
		.pageSize(pageSize)
		.records(records)
		.totalRecords(request.getTotalRowCount().intValue())
		.build();
	}

	@Override
	public List<ErrorResponse> getErrorResponse(String quoteId) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.aaeDao.getErrorResponse(quoteId, result);
		
		return getJsonDataObject(jsonString, new TypeReference<List<ErrorResponse>>(){});
	}
	
	@Override
	public void updateAccountInformation(AccountEdit accountEdit) {
		AaeAccountEditRequest req = new AaeAccountEditRequest(CSWebAction.UPDATE,
				CswebSecurityUtils.getLoggedInUser(), accountEdit);
		
		this.aaeDao.updateAccountInfo(req);
		logger.debug("updateAccountInfo JSON: {}", req.getJson());
		
		logger.info("Response updateAccountInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response updateAccountInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
	
	@Override
	public void updateSiteInformation(SiteEdit siteEdit) {
		AaeSiteEditRequest req = new AaeSiteEditRequest(CSWebAction.UPDATE,
				CswebSecurityUtils.getLoggedInUser(), siteEdit);
		
		this.aaeDao.updateSiteInfo(req);
		logger.debug("updateSiteInfo JSON: {}", req.getJson());
		
		logger.info("Response updateSiteInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response updateSiteInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
	
	@Override
	public void updateContainerInformation(ContainerEdit containerEdit) {
		AaeContainerEditRequest req = new AaeContainerEditRequest(CSWebAction.UPDATE,
				CswebSecurityUtils.getLoggedInUser(), containerEdit);
		
		this.aaeDao.updateContainerInfo(req);
		logger.debug("updateContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response updateContainerInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response updateContainerInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
	
	@Override
	public void updateRateInformation(RateEdit rateEdit) {
		AaeRateEditRequest req = new AaeRateEditRequest(CSWebAction.UPDATE,
				SecurityUtils.getLoggedInUser(), rateEdit);
		
		this.aaeDao.updateRateInfo(req);
		logger.debug("updateContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response updateRateInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response updateRateInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}

	@Override
	public void submitFinalizationInformation(FinalizationParams finalizationParams) {
		AaeFinalizationSubmitRequest req = new AaeFinalizationSubmitRequest(CSWebAction.UPDATE,
				SecurityUtils.getLoggedInUser(), finalizationParams);

		logger.debug("submitFinalizationInfo JSON: {}", req.getJson());

		this.aaeDao.submitFinalizationInfo(req);

		logger.info("Response submitFinalizationInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response submitFinalizationInfo is: {}" , req.getReturnStatus());

		req.validateResponse();
	}

	@Override
	public Map<String,String> assignQuote(String assignee, String requestingUser, String quoteId) {
		QuoteAssigneeRequest req = new QuoteAssigneeRequest(requestingUser, quoteId, assignee, ActionType.ASSIGN);
		Map<String,String> response = new HashMap<>(); 
		this.aaeDao.updateQuoteAssignee(req);
		response.put("username", req.getQuoteAssignedTo());
		response.put("errorMsg", req.getErrorMessage());
		return response;
	}

	@Override
	public void unassignQuote(String assignee, String requestingUser, String quoteId) {
		if(!CswebSecurityUtils.isSuperAdmin())
			throw new RuntimeException("User "+CswebSecurityUtils.getLoggedInUser()+" not a super admin - cannot unassign");
		
		QuoteAssigneeRequest req = new QuoteAssigneeRequest(requestingUser, quoteId, assignee, ActionType.UNASSIGN);
		this.aaeDao.updateQuoteAssignee(req);
	}

	@Override
	public QuoteMetadata getQuoteMetadata(String quoteId) {
		QuoteMetadataRequest req = new QuoteMetadataRequest(quoteId, SecurityUtils.getLoggedInUser());
		String json = this.aaeDao.getQuoteMetadata(req);
		
		return getJsonDataObject(json, QuoteMetadata.class);
	}

	@Override
	public void deleteRateInformation(RateEdit rateEdit) {
		AaeRateEditRequest req = new AaeRateEditRequest(CSWebAction.DELETE,
				SecurityUtils.getLoggedInUser(), rateEdit);
		
		this.aaeDao.updateRateInfo(req);
		logger.debug("deleteContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response deleteRateInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response deleteRateInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
	
	@Override
	public void createRateInformation(RateCreate rateCreate) {
		AaeRateCreateRequest req = new AaeRateCreateRequest(CSWebAction.INSERT,
				SecurityUtils.getLoggedInUser(), rateCreate);
		
		this.aaeDao.updateRateInfo(req);
		logger.debug("deleteContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response deleteRateInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response deleteRateInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
	
	@Override
	public ContractResponse getContractInfo(String quoteId) {
		AaeContractInfoRequest req = new AaeContractInfoRequest(CSWebAction.GET_ALL,
				SecurityUtils.getLoggedInUser(), quoteId);
		String jsonString = this.aaeDao.getContractInfo(req);
		
		logger.debug("contractInfo JSON: {}", req.getJson());
		
		logger.info("Response contractinfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response contractinfostatus is: {}" , req.getReturnStatus());
		
		return getJsonDataObject(jsonString, ContractResponse.class); 
		
	}

	public List<QuoteNotes> getQuoteNotes(String quoteId) {
		AaeContractInfoRequest req = new AaeContractInfoRequest(CSWebAction.GET_ALL,
				SecurityUtils.getLoggedInUser(), quoteId);
		String jsonString = this.aaeDao.getQuoteNotes(req);
		return getJsonDataObject(jsonString, new TypeReference<List<QuoteNotes>>(){}); 
		
	}

    public String updateStatusCode(String quoteId, String statusCode, String note) {
		StatusCodeRequest req = new StatusCodeRequest(CSWebAction.UPDATE, SecurityUtils.getLoggedInUser(), statusCode, quoteId, note);
		this.aaeDao.updateStatusCode(req);
		return req.getPreviousStatusCode();
    }
		
	@Override
	public ListDataResponse getListDataOptions(String division, ListDataEntityType selected) {
		ListDataRequest req = new ListDataRequest(CSWebAction.GET_ALL ,SecurityUtils.getLoggedInUser(), division);
		String json = "";
		switch(selected){
			case ACCOUNT:
			 json = this.aaeDao.getAccountListData(req);
			break;
			case SITE:
			 json = this.aaeDao.getSiteListData(req);
			break;
			case CONTAINER:
			 json = this.aaeDao.getContainerListData(req);
			break;
			case RATE:
			 json = this.aaeDao.getRatesListData(req);
			break;
			case SALES:
			json = this.aaeDao.getSalesListData(req);
			break;
			case FSR:
				 json = this.aaeDao.getAccountListData(req);
				break;
		}
			return getJsonDataObject(json, ListDataResponse.class);
	}

	@Override
	public String getFinalAccountNumber(String projectId){
		GetAccountNumberRequest result = new GetAccountNumberRequest(projectId);
		this.aaeDao.getNewAccountNumber(projectId, result);
		return result.getOutNewAccount();
	}	
	
	@Override
	public void deleteContainerInformation(ContainerEdit containerEdit) {
		AaeContainerEditRequest req = new AaeContainerEditRequest(CSWebAction.DELETE,
				CswebSecurityUtils.getLoggedInUser(), containerEdit);
		
		this.aaeDao.updateContainerInfo(req);
		logger.debug("deleteContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response deleteContainerInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response deleteContainerInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}

    public void resetFinalAccountNumber(String quoteId) {
		GetAccountNumberRequest response = new GetAccountNumberRequest(quoteId);
		 this.aaeDao.resetNewAccountNumber(quoteId, response);
    }
    
    @Override
	public void deleteFsrInformation(ContainerEdit containerEdit) {
		AaeContainerEditRequest req = new AaeContainerEditRequest(CSWebAction.FSR_DELETE,
				CswebSecurityUtils.getLoggedInUser(), containerEdit);
		
		this.aaeDao.updateContainerInfo(req);
		logger.debug("deleteContainerInfo JSON: {}", req.getJson());
		
		logger.info("Response deleteContainerInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response deleteContainerInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
    
    @Override
	public void getAaeErrorReport(AaeErrorReport errorReport) {
		AaeErrorReportRequest req = new AaeErrorReportRequest(CSWebAction.AAE_ERROR_REPORT,
				CswebSecurityUtils.getLoggedInUser(), errorReport);
		
		this.aaeDao.getErrorReport(req);
		logger.debug("errorReport JSON: {}", req.getJson());
		
		logger.info("Response errorReportInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response errorReportInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
    
    @Override
	public void getAaeExceptionReport(AaeExceptionReport exceptionReport) {
		AaeExceptionReportRequest req = new AaeExceptionReportRequest(CSWebAction.AAE_EXCEP_REPORT,
				CswebSecurityUtils.getLoggedInUser(), exceptionReport);
		
		this.aaeDao.getExceptionReport(req);
		logger.debug("exceptionReport JSON: {}", req.getJson());
		
		logger.info("Response exceptionReportInfoMessage is: {}", req.getReturnErrorMsg());
		logger.info("Response exceptionReportInfo is: {}" , req.getReturnStatus());
		
		req.validateResponse();
	}
}
