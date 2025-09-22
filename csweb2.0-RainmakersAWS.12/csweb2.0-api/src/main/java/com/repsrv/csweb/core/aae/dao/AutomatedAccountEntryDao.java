package com.repsrv.csweb.core.aae.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.aae.model.AaeSearchRequest;
import com.repsrv.csweb.core.aae.model.ListDataRequest;
import com.repsrv.csweb.core.aae.model.GetAccountNumberRequest;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest;
import com.repsrv.csweb.core.aae.model.QuoteMetadataRequest;
import com.repsrv.csweb.core.aae.model.StatusCodeRequest;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AutomatedAccountEntryDao extends InfoProDao {
	
	String getAccountDetail(@Param("company") String company, @Param("projectId") String projectId, @Param("result") StoredProcCallResult result);

	String searchQuotes(@Param("request") AaeSearchRequest request);
	
	String searchDivisionalQuotes(@Param("request") AaeSearchRequest request);
	
	String getErrorResponse(@Param("quoteId") String quoteId, @Param("result") StoredProcCallResult result);
	
	void updateAccountInfo(@Param("request") BaseRequest req);
	
	void updateSiteInfo(@Param("request") BaseRequest req);
	
	void updateContainerInfo(@Param("request") BaseRequest req);
	
	void updateRateInfo(@Param("request") BaseRequest req);

	void submitFinalizationInfo(@Param("request") BaseRequest req);
	
	void updateQuoteAssignee(@Param("request") QuoteAssigneeRequest req);
	
	String getQuoteMetadata(@Param("request") QuoteMetadataRequest req);

	String getAccountListData(@Param("request") ListDataRequest req);

	String getSiteListData(@Param("request") ListDataRequest req);

	String getContainerListData(@Param("request") ListDataRequest req);

	String getRatesListData(@Param("request") ListDataRequest req);

	String getSalesListData(@Param("request") ListDataRequest req);

	String getContractInfo(@Param("request") BaseRequest req);
	
	void updateStatusCode(@Param("request") StatusCodeRequest req);

	void getNewAccountNumber(@Param("projectId") String projectId, @Param("result") GetAccountNumberRequest result);
	
	void resetNewAccountNumber(@Param("quoteId") String quoteId,  @Param("result") GetAccountNumberRequest result);
	
	void getErrorReport(@Param("request") BaseRequest req);
	
	void getExceptionReport(@Param("request") BaseRequest req);

	String getQuoteNotes(@Param("request")BaseRequest req );
}
