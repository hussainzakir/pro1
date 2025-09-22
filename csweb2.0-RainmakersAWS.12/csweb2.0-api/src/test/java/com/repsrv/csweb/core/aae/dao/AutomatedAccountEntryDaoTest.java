package com.repsrv.csweb.core.aae.dao;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.repsrv.csweb.core.aae.model.AaeAccountEditRequest;
import com.repsrv.csweb.core.aae.model.AaeContainerEditRequest;
import com.repsrv.csweb.core.aae.model.AaeContractInfoRequest;
import com.repsrv.csweb.core.aae.model.AaeErrorReportRequest;
import com.repsrv.csweb.core.aae.model.AaeExceptionReportRequest;
import com.repsrv.csweb.core.aae.model.AaeFinalizationSubmitRequest;
import com.repsrv.csweb.core.aae.model.AaeRateEditRequest;
import com.repsrv.csweb.core.aae.model.AaeSearchRequest;
import com.repsrv.csweb.core.aae.model.AaeSiteEditRequest;
import com.repsrv.csweb.core.aae.model.ListDataRequest;
import com.repsrv.csweb.core.aae.model.GetAccountNumberRequest;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest;
import com.repsrv.csweb.core.aae.model.QuoteMetadataRequest;
import com.repsrv.csweb.core.aae.model.StatusCodeRequest;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest.ActionType;
import com.repsrv.csweb.core.model.aae.AaeErrorReport;
import com.repsrv.csweb.core.model.aae.AaeExceptionReport;
import com.repsrv.csweb.core.model.aae.AccountEdit;
import com.repsrv.csweb.core.model.aae.ContainerEdit;
import com.repsrv.csweb.core.model.aae.FinalizationParams;
import com.repsrv.csweb.core.model.aae.RateEdit;
import com.repsrv.csweb.core.model.aae.SearchParams;
import com.repsrv.csweb.core.model.aae.SiteEdit;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

@RunWith(MockitoJUnitRunner.class)
public abstract class AutomatedAccountEntryDaoTest {
    
    @Mock
    private AutomatedAccountEntryDao automatedAccountEntryDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAccountDetail() {
        StoredProcCallResult result = new StoredProcCallResult();
        when(automatedAccountEntryDao.getAccountDetail("company1", "projectId1", result)).thenReturn("accountDetail");

        String accountDetail = automatedAccountEntryDao.getAccountDetail("company1", "projectId1", result);
        assertEquals("accountDetail", accountDetail);
    }

    @Test
    public void testSearchQuotes() {
        SearchParams searchParams = new SearchParams( "8001", "500", "800", "5656", "SMITHBO", "66", "5", "2024-12-12", "2025-12-12", "Y", "N", "N", "2", "2024-12-12", "SMITHBO", "Y", "TEST1");
        AaeSearchRequest request = new AaeSearchRequest(searchParams, "800", 1, 1);
        when(automatedAccountEntryDao.searchQuotes(request)).thenReturn("quotes");

        String quotes = automatedAccountEntryDao.searchQuotes(request);
        assertEquals("quotes", quotes);
    }

    @Test
    public void testGetErrorResponse() {
        StoredProcCallResult result = new StoredProcCallResult();
        when(automatedAccountEntryDao.getErrorResponse("quoteId1", result)).thenReturn("errorResponse");

        String errorResponse = automatedAccountEntryDao.getErrorResponse("quoteId1", result);
        assertEquals("errorResponse", errorResponse);
    }

    @Test
    public void testUpdateAccountInfo() {
        AccountEdit accountEdit = new AccountEdit();
        AaeAccountEditRequest request = new AaeAccountEditRequest(CSWebAction.UPDATE, "SMITHBO", accountEdit);
        doNothing().when(automatedAccountEntryDao).updateAccountInfo(request);

        automatedAccountEntryDao.updateAccountInfo(request);
        verify(automatedAccountEntryDao, times(1)).updateAccountInfo(request);
    }

    @Test
    public void testUpdateSiteInfo() {
        SiteEdit siteEdit = new SiteEdit(); 
        AaeSiteEditRequest request = new AaeSiteEditRequest(CSWebAction.UPDATE, "SMITHBO", siteEdit);
        doNothing().when(automatedAccountEntryDao).updateSiteInfo(request);

        automatedAccountEntryDao.updateSiteInfo(request);
        verify(automatedAccountEntryDao, times(1)).updateSiteInfo(request);
    }

    @Test
    public void testUpdateContainerInfo() {
        ContainerEdit containerEdit = new ContainerEdit(); 
        AaeContainerEditRequest request = new AaeContainerEditRequest(CSWebAction.UPDATE, "SMITHBO", containerEdit);    
        doNothing().when(automatedAccountEntryDao).updateContainerInfo(request);

        automatedAccountEntryDao.updateContainerInfo(request);
        verify(automatedAccountEntryDao, times(1)).updateContainerInfo(request);
    }

    @Test
    public void testUpdateRateInfo() {
        RateEdit rateEdit = new RateEdit(); 
        AaeRateEditRequest request = new AaeRateEditRequest(CSWebAction.UPDATE, "SMITHBO", rateEdit);
        doNothing().when(automatedAccountEntryDao).updateRateInfo(request);

        automatedAccountEntryDao.updateRateInfo(request);
        verify(automatedAccountEntryDao, times(1)).updateRateInfo(request);
    }

    @Test
    public void testSubmitFinalizationInfo() {
        FinalizationParams finalizationParams = new FinalizationParams(); //////
        AaeFinalizationSubmitRequest request = new AaeFinalizationSubmitRequest(CSWebAction.UPDATE, "SMITHBO", finalizationParams);
        doNothing().when(automatedAccountEntryDao).submitFinalizationInfo(request);

        automatedAccountEntryDao.submitFinalizationInfo(request);
        verify(automatedAccountEntryDao, times(1)).submitFinalizationInfo(request);
    }

    @Test
    public void testUpdateQuoteAssignee() {
        QuoteAssigneeRequest request = new QuoteAssigneeRequest("SMITHBO", "S23252552", "SMITHBO", ActionType.ASSIGN);
        doNothing().when(automatedAccountEntryDao).updateQuoteAssignee(request);

        automatedAccountEntryDao.updateQuoteAssignee(request);
        verify(automatedAccountEntryDao, times(1)).updateQuoteAssignee(request);
    }

    @Test
    public void testGetQuoteMetadata() {
        QuoteMetadataRequest request = new QuoteMetadataRequest("S5256525652", "SMITHBO");
        when(automatedAccountEntryDao.getQuoteMetadata(request)).thenReturn("quoteMetadata");

        String quoteMetadata = automatedAccountEntryDao.getQuoteMetadata(request);
        assertEquals("quoteMetadata", quoteMetadata);
    }

    @Test
    public void testGetAccountListData() {
        ListDataRequest request = new ListDataRequest(CSWebAction.UPDATE, "SMITHBO", "800");
        when(automatedAccountEntryDao.getAccountListData(request)).thenReturn("accountListData");

        String accountListData = automatedAccountEntryDao.getAccountListData(request);
        assertEquals("accountListData", accountListData);
    }

    @Test
    public void testGetSiteListData() {
        ListDataRequest request = new ListDataRequest(CSWebAction.UPDATE, "SMITHBO", "800");
        when(automatedAccountEntryDao.getSiteListData(request)).thenReturn("siteListData");

        String siteListData = automatedAccountEntryDao.getSiteListData(request);
        assertEquals("siteListData", siteListData);
    }

    @Test
    public void testGetContainerListData() {
        ListDataRequest request = new ListDataRequest(CSWebAction.UPDATE, "SMITHBO", "800");
        when(automatedAccountEntryDao.getContainerListData(request)).thenReturn("containerListData");

        String containerListData = automatedAccountEntryDao.getContainerListData(request);
        assertEquals("containerListData", containerListData);
    }

    @Test
    public void testGetRatesListData() {
        ListDataRequest request = new ListDataRequest(CSWebAction.UPDATE, "SMITHBO", "800");
        when(automatedAccountEntryDao.getRatesListData(request)).thenReturn("ratesListData");

        String ratesListData = automatedAccountEntryDao.getRatesListData(request);
        assertEquals("ratesListData", ratesListData);
    }

    @Test
    public void testGetSalesListData() {
        ListDataRequest request = new ListDataRequest(CSWebAction.UPDATE, "SMITHBO", "800");
        when(automatedAccountEntryDao.getSalesListData(request)).thenReturn("salesListData");

        String salesListData = automatedAccountEntryDao.getSalesListData(request);
        assertEquals("salesListData", salesListData);
    }

    @Test
    public void testGetContractInfo() {
        AaeContractInfoRequest request = new AaeContractInfoRequest(CSWebAction.GET_ALL, "SMITHBO", "S25256525");
        when(automatedAccountEntryDao.getContractInfo(request)).thenReturn("contractInfo");

        String contractInfo = automatedAccountEntryDao.getContractInfo(request);
        assertEquals("contractInfo", contractInfo);
    }

    @Test
    public void testUpdateStatusCode() {
        StatusCodeRequest request = new StatusCodeRequest(CSWebAction.UPDATE, "SMITHBO", "S25252525","S54554585", "Added Account");
        doNothing().when(automatedAccountEntryDao).updateStatusCode(request);

        automatedAccountEntryDao.updateStatusCode(request);
        verify(automatedAccountEntryDao, times(1)).updateStatusCode(request);
    }

    @Test
    public void testGetNewAccountNumber() {
        GetAccountNumberRequest result = new GetAccountNumberRequest("8585565");
        doNothing().when(automatedAccountEntryDao).getNewAccountNumber("projectId1", result);

        automatedAccountEntryDao.getNewAccountNumber("projectId1", result);
        verify(automatedAccountEntryDao, times(1)).getNewAccountNumber("projectId1", result);
    }

    @Test
    public void testResetNewAccountNumber() {
        GetAccountNumberRequest result = new GetAccountNumberRequest("856565655");
        doNothing().when(automatedAccountEntryDao).resetNewAccountNumber("quoteId1", result);

        automatedAccountEntryDao.resetNewAccountNumber("quoteId1", result);
        verify(automatedAccountEntryDao, times(1)).resetNewAccountNumber("quoteId1", result);
    }

    @Test
    public void testGetErrorReport() {
        AaeErrorReport errorReport = new AaeErrorReport();
        AaeErrorReportRequest request = new AaeErrorReportRequest(CSWebAction.AAE_ERROR_REPORT, "SMITHBO", errorReport);
        doNothing().when(automatedAccountEntryDao).getErrorReport(request);

        automatedAccountEntryDao.getErrorReport(request);
        verify(automatedAccountEntryDao, times(1)).getErrorReport(request);
    }

    @Test
    public void testGetExceptionReport() {
        AaeExceptionReport errorReport = new AaeExceptionReport("2024-06-01", "2024-06-30");
        AaeExceptionReportRequest request = new AaeExceptionReportRequest(CSWebAction.AAE_ERROR_REPORT, "SMITHBO", errorReport);
        doNothing().when(automatedAccountEntryDao).getExceptionReport(request);

        automatedAccountEntryDao.getExceptionReport(request);
        verify(automatedAccountEntryDao, times(1)).getExceptionReport(request);
    }

    @Test
    public void testGetQuoteNotes() {
        AaeContractInfoRequest request = new AaeContractInfoRequest(CSWebAction.GET_ALL, "SMITHBO", "S25256525");
        when(automatedAccountEntryDao.getQuoteNotes(request)).thenReturn("quoteNotes");

        String quoteNotes = automatedAccountEntryDao.getQuoteNotes(request);
        assertEquals("quoteNotes", quoteNotes);
    }
}
