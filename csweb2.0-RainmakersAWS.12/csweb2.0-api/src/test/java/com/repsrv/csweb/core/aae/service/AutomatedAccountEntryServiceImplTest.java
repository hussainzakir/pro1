package com.repsrv.csweb.core.aae.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.repsrv.csweb.core.aae.dao.AutomatedAccountEntryDao;
import com.repsrv.csweb.core.aae.model.AaeAccountEditRequest;
import com.repsrv.csweb.core.aae.model.AaeContainerEditRequest;
import com.repsrv.csweb.core.aae.model.AaeContractInfoRequest;
import com.repsrv.csweb.core.aae.model.AaeErrorReportRequest;
import com.repsrv.csweb.core.aae.model.AaeExceptionReportRequest;
import com.repsrv.csweb.core.aae.model.AaeFinalizationSubmitRequest;
import com.repsrv.csweb.core.aae.model.AaeRateCreateRequest;
import com.repsrv.csweb.core.aae.model.AaeRateEditRequest;
import com.repsrv.csweb.core.aae.model.AaeSearchRequest;
import com.repsrv.csweb.core.aae.model.AaeSiteEditRequest;
import com.repsrv.csweb.core.aae.model.StatusCodeRequest;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest.ActionType;
import com.repsrv.csweb.core.aae.model.ListDataRequest;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest;
import com.repsrv.csweb.core.aae.model.QuoteMetadataRequest;
import com.repsrv.csweb.core.model.aae.AaeErrorReport;
import com.repsrv.csweb.core.model.aae.AaeExceptionReport;
import com.repsrv.csweb.core.model.aae.AccountEdit;
import com.repsrv.csweb.core.model.aae.ContainerEdit;
import com.repsrv.csweb.core.model.aae.FinalizationParams;
import com.repsrv.csweb.core.model.aae.RateCreate;
import com.repsrv.csweb.core.model.aae.RateEdit;
import com.repsrv.csweb.core.model.aae.SearchParams;
import com.repsrv.csweb.core.model.aae.SiteEdit;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;


@RunWith(PowerMockRunner.class)
@PrepareForTest(CswebSecurityUtils.class)
public class AutomatedAccountEntryServiceImplTest {
	
	  	@InjectMocks
	    private AutomatedAccountEntryServiceImpl service;

	    @Mock
	    private AutomatedAccountEntryDao aaeDao;

	    @Captor
	    ArgumentCaptor<AaeRateCreateRequest> captor;
	    
	    @Captor
	    ArgumentCaptor<AaeAccountEditRequest> accountCaptor;
	    
	    @Captor
	    ArgumentCaptor<AaeSiteEditRequest> siteCaptor;
	    
	    @Captor
	    ArgumentCaptor<AaeContainerEditRequest> containerCaptor;
	    
	    @Captor
	    ArgumentCaptor<AaeRateEditRequest> rateCaptor;

		@Captor
	    ArgumentCaptor<AaeFinalizationSubmitRequest> finalizeCaptor;

		@Captor
	    ArgumentCaptor<QuoteAssigneeRequest> quoteAssignCaptor;

		@Captor
	    ArgumentCaptor<QuoteMetadataRequest> quoteMetaCaptor;

		@Captor
	    ArgumentCaptor<AaeRateCreateRequest> rateCreateCaptor;

		@Captor
	    ArgumentCaptor<AaeContractInfoRequest> contractInfoCaptor;

		@Captor
	    ArgumentCaptor<StatusCodeRequest> statusCodeCaptor;

		@Captor
	    ArgumentCaptor<ListDataRequest> dropDownCaptor;
		
		@Captor
	    private ArgumentCaptor<AaeErrorReportRequest> reqCaptor;

		@Captor
	    private ArgumentCaptor<AaeExceptionReportRequest> exReqCaptor;

		@Before
   		public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CswebSecurityUtils.class);
        when(CswebSecurityUtils.getLoggedInUser()).thenReturn("mockUser");
    }
		
		@Test
		public void testGetAccountDetail() {
			String company = "800";
		    String projectId = "256565";
		    StoredProcCallResult result = new StoredProcCallResult();

			aaeDao.getAccountDetail(company, projectId, result);
			verify(aaeDao, times(1)).getAccountDetail(company, projectId, result);
		}

		@Test
		public void testSearchAccounts() {
			SearchParams parms = new SearchParams();
			parms.setCompanyNumber("800");
			parms.setCustomerAccount("852656");

			String division = "800";
			int pageNumber = 1;
			int pageSize = 10;

			AaeSearchRequest req = new AaeSearchRequest(parms, division, pageNumber, pageSize);
			aaeDao.searchQuotes(req);
			verify(aaeDao, times(1)).searchQuotes(req);
		}

	 	@Test
	 	public void testGetErrorResponse() {
	 		String quoteId = "testQuoteId";

			StoredProcCallResult result = new StoredProcCallResult();

		 	aaeDao.getErrorResponse(quoteId, result);
		 	verify(aaeDao, times(1)).getErrorResponse(quoteId, result);
		}

	    @Test
	    public void testUpdateAccountInformation() {
	    	AccountEdit parms = new AccountEdit();
			parms.setAccountStagingId("4558865");
			parms.setCompanyNumber("800");
			parms.setCustomerAccount("5458556");
			parms.setAccountContactName("BOB SMITH");
			parms.setAccountContactEmail("B_SMITH@TEST.COM");
	    	parms.setAccountContactTelephone1("6025556565");
	    	
	    	AaeAccountEditRequest req = new AaeAccountEditRequest(CSWebAction.UPDATE, "SMITHBO", parms);
	    	aaeDao.updateAccountInfo(req);
	    	verify(aaeDao, times(1)).updateAccountInfo(accountCaptor.capture());
	    	
	    	AaeAccountEditRequest capturedReq = accountCaptor.getValue();
	    	
	    	assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
	        assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("4558865", ((AccountEdit) capturedReq.getRequestedObject()).getAccountStagingId());
			assertEquals("800", ((AccountEdit) capturedReq.getRequestedObject()).getCompanyNumber());
			assertEquals("5458556", ((AccountEdit) capturedReq.getRequestedObject()).getCustomerAccount());
			assertEquals("BOB SMITH", ((AccountEdit) capturedReq.getRequestedObject()).getAccountContactName());
			assertEquals("B_SMITH@TEST.COM", ((AccountEdit) capturedReq.getRequestedObject()).getAccountContactEmail());
	        assertEquals("6025556565", ((AccountEdit) capturedReq.getRequestedObject()).getAccountContactTelephone1());
	    }
	    
	    @Test
	    public void testUpdateSiteInformation() {
	    	SiteEdit parms = new SiteEdit();
	    	parms.setStagingId("5658858");
			parms.setSite("00001");
			parms.setSiteName("TEST SITE");
			parms.setSiteAddress1("1234 TEST");
			parms.setCity("PHOENIX");
			parms.setState("AZ");
			parms.setZipCode("85001");
			parms.setPhoneNumber("6025556565");
			parms.setFaxNumber("6235557878");
			parms.setEffectiveDate("20240203");
			
			AaeSiteEditRequest req = new AaeSiteEditRequest(CSWebAction.UPDATE, "SMITHBO", parms);
			aaeDao.updateSiteInfo(req);
			verify(aaeDao, times(1)).updateSiteInfo(siteCaptor.capture());
			
			AaeSiteEditRequest capturedReq = siteCaptor.getValue();

			assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("5658858", ((SiteEdit) capturedReq.getRequestedObject()).getStagingId());
			assertEquals("00001", ((SiteEdit) capturedReq.getRequestedObject()).getSite());
			assertEquals("TEST SITE", ((SiteEdit) capturedReq.getRequestedObject()).getSiteName());
			assertEquals("1234 TEST", ((SiteEdit) capturedReq.getRequestedObject()).getSiteAddress1());
			assertEquals("PHOENIX", ((SiteEdit) capturedReq.getRequestedObject()).getCity());
			assertEquals("AZ", ((SiteEdit) capturedReq.getRequestedObject()).getState());
			assertEquals("85001", ((SiteEdit) capturedReq.getRequestedObject()).getZipCode());
			assertEquals("6025556565", ((SiteEdit) capturedReq.getRequestedObject()).getPhoneNumber());
			assertEquals("6235557878", ((SiteEdit) capturedReq.getRequestedObject()).getFaxNumber());
			assertEquals("20240203", ((SiteEdit) capturedReq.getRequestedObject()).getEffectiveDate());    	  	
	    }
	    
	    @Test
	    public void testUpdateContainerInformation() {
	    	ContainerEdit parms = new ContainerEdit();
			parms.setStagingId("5659585");
			parms.setContainerGroup("1");
			parms.setContainerSize("20.0");
			parms.setContainerType("D");
			parms.setCloseDate("20241212");
			parms.setOncallFlag("Y");
			parms.setContainerQtyOnSite("1");
	    	parms.setBilledToDate("20240203");
	    	
	    	AaeContainerEditRequest req = new AaeContainerEditRequest(CSWebAction.UPDATE, "SMITHBO", parms);
	    	aaeDao.updateContainerInfo(req);
	    	verify(aaeDao, times(1)).updateContainerInfo(containerCaptor.capture());
	    	
	    	AaeContainerEditRequest capturedReq = containerCaptor.getValue();
	    	
	    	assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
	        assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("5659585", ((ContainerEdit) capturedReq.getRequestedObject()).getStagingId());
			assertEquals("1", ((ContainerEdit) capturedReq.getRequestedObject()).getContainerGroup());
			assertEquals("20.0", ((ContainerEdit) capturedReq.getRequestedObject()).getContainerSize());
			assertEquals("D", ((ContainerEdit) capturedReq.getRequestedObject()).getContainerType());
			assertEquals("20241212", ((ContainerEdit) capturedReq.getRequestedObject()).getCloseDate());
			assertEquals("Y", ((ContainerEdit) capturedReq.getRequestedObject()).getOncallFlag());
			assertEquals("1", ((ContainerEdit) capturedReq.getRequestedObject()).getContainerQtyOnSite());
	        assertEquals("20240203", ((ContainerEdit) capturedReq.getRequestedObject()).getBilledToDate());
	    }  

		@Test 
		public void testUpdateRateInformation() {
			RateEdit parms = new RateEdit();
			parms.setRateStagingId("0236565");
			parms.setCompanyNumber("800");
			parms.setCustomerAccount("RAYS");
			parms.setSite("00001");
			parms.setContainerGroup("1");
			parms.setChargeCode("C");
			parms.setChargeType("A");
			parms.setChargeMethod("A");
			parms.setRateEffectiveDate("20240303");
			parms.setCloseDate("20241212");
			parms.setChargeRate("18.00");

			AaeRateEditRequest req = new AaeRateEditRequest(CSWebAction.UPDATE, "SMITHBO", parms);
			aaeDao.updateRateInfo(req);
			verify(aaeDao, times(1)).updateRateInfo(rateCaptor.capture());

			AaeRateEditRequest capturedReq = rateCaptor.getValue();

			assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("0236565", ((RateEdit) capturedReq.getRequestedObject()).getRateStagingId());
			assertEquals("800", ((RateEdit) capturedReq.getRequestedObject()).getCompanyNumber());
			assertEquals("RAYS", ((RateEdit) capturedReq.getRequestedObject()).getCustomerAccount());
			assertEquals("00001", ((RateEdit) capturedReq.getRequestedObject()).getSite());
			assertEquals("1", ((RateEdit) capturedReq.getRequestedObject()).getContainerGroup());
			assertEquals("C", ((RateEdit) capturedReq.getRequestedObject()).getChargeCode());
			assertEquals("A", ((RateEdit) capturedReq.getRequestedObject()).getChargeType());
			assertEquals("A", ((RateEdit) capturedReq.getRequestedObject()).getChargeMethod());
			assertEquals("20240303", ((RateEdit) capturedReq.getRequestedObject()).getRateEffectiveDate());
			assertEquals("20241212", ((RateEdit) capturedReq.getRequestedObject()).getCloseDate());
			assertEquals("18.00", ((RateEdit) capturedReq.getRequestedObject()).getChargeRate());
		}

		@Test
		public void testSubmitFinalizationInformation() {
			FinalizationParams parms = new FinalizationParams();
			parms.setProjectId("688888");
			parms.setValidateOnly("1");

			AaeFinalizationSubmitRequest req = new AaeFinalizationSubmitRequest(CSWebAction.UPDATE, "SMITHBO", parms);
			aaeDao.submitFinalizationInfo(req);
			verify(aaeDao, times(1)).submitFinalizationInfo(finalizeCaptor.capture());

			AaeFinalizationSubmitRequest capturedReq = finalizeCaptor.getValue();

			assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("688888", ((FinalizationParams) capturedReq.getRequestedObject()).getProjectId());
			assertEquals("1", ((FinalizationParams) capturedReq.getRequestedObject()).getValidateOnly());
		}

		@Test
		public void testAssignQuote() {
			String assignee = "CARLSTE";
			String requestingUser = "SMITHBO";
			String quoteId = "5656565";
			
			QuoteAssigneeRequest req = new QuoteAssigneeRequest(requestingUser, quoteId, assignee, ActionType.ASSIGN);
			aaeDao.updateQuoteAssignee(req);
			verify(aaeDao, times(1)).updateQuoteAssignee(quoteAssignCaptor.capture());
		}

		@Test
		public void testUnassignQuote() {
			String assignee = "CARLSTE";
			String requestingUser = "SMITHBO";
			String quoteId = "5656565";

			QuoteAssigneeRequest req = new QuoteAssigneeRequest(requestingUser, quoteId, assignee, ActionType.UNASSIGN);
			aaeDao.updateQuoteAssignee(req);
			verify(aaeDao, times(1)).updateQuoteAssignee(quoteAssignCaptor.capture());
		}

		@Test
		public void testGetQuoteMetadata() {
			String quoteId = "5656565";

			QuoteMetadataRequest req = new QuoteMetadataRequest(quoteId, SecurityUtils.getLoggedInUser());
			aaeDao.getQuoteMetadata(req);
			verify(aaeDao, times(1)).getQuoteMetadata(req);
		}

		@Test
		public void testDeleteRateInformation() {
			RateEdit parms = new RateEdit();
			parms.setRateStagingId("0236565");
			parms.setCompanyNumber("800");
			parms.setCustomerAccount("RAYS");
			parms.setSite("00001");
			parms.setContainerGroup("1");
			parms.setChargeCode("C");
			parms.setChargeType("A");
			parms.setChargeMethod("A");
			parms.setRateEffectiveDate("20240303");
			parms.setCloseDate("20241212");
			parms.setChargeRate("18.00");

			AaeRateEditRequest req = new AaeRateEditRequest(CSWebAction.DELETE, "SMITHBO", parms);
			aaeDao.updateRateInfo(req);
			verify(aaeDao, times(1)).updateRateInfo(rateCaptor.capture());

			AaeRateEditRequest capturedReq = rateCaptor.getValue();

			assertEquals(CSWebAction.DELETE, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
			assertEquals("0236565", ((RateEdit) capturedReq.getRequestedObject()).getRateStagingId());
			assertEquals("800", ((RateEdit) capturedReq.getRequestedObject()).getCompanyNumber());
			assertEquals("RAYS", ((RateEdit) capturedReq.getRequestedObject()).getCustomerAccount());
			assertEquals("00001", ((RateEdit) capturedReq.getRequestedObject()).getSite());
			assertEquals("1", ((RateEdit) capturedReq.getRequestedObject()).getContainerGroup());
			assertEquals("C", ((RateEdit) capturedReq.getRequestedObject()).getChargeCode());
			assertEquals("A", ((RateEdit) capturedReq.getRequestedObject()).getChargeType());
			assertEquals("A", ((RateEdit) capturedReq.getRequestedObject()).getChargeMethod());
			assertEquals("20240303", ((RateEdit) capturedReq.getRequestedObject()).getRateEffectiveDate());
			assertEquals("20241212", ((RateEdit) capturedReq.getRequestedObject()).getCloseDate());
			assertEquals("18.00", ((RateEdit) capturedReq.getRequestedObject()).getChargeRate());
		}			
	    
	    @Test
	    public void testCreateRateInformation() {
	    	RateCreate parms = new RateCreate();
	    	parms.setRateStagingId("0");
	    	parms.setProjectId("256565");
	    	parms.setCompanyNumber("800");
	    	parms.setCustomerAccount("54585");
	    	parms.setSite("00001");
	    	parms.setContainerGroup("1");
	    	parms.setChargeCode("C");
	    	parms.setChargeType("A");
	    	parms.setChargeMethod("A");
	    	parms.setRateEffectiveDate("20240303");
	    	parms.setCloseDate("20241212");
	    	parms.setChargeRate("18.00");
	    	
	    	AaeRateCreateRequest req = new AaeRateCreateRequest(CSWebAction.INSERT, "SMITBO", parms);
	    	aaeDao.updateRateInfo(req);
	    	verify(aaeDao, times(1)).updateRateInfo(captor.capture());
	    	
	    	 AaeRateCreateRequest capturedReq = captor.getValue();

	         assertEquals(CSWebAction.INSERT, capturedReq.getAction());
	         assertEquals("SMITBO", capturedReq.getRequestedUser());
	         assertEquals("0", ((RateCreate) capturedReq.getRequestedObject()).getRateStagingId());
	         assertEquals("256565", ((RateCreate) capturedReq.getRequestedObject()).getProjectId());
	         assertEquals("800", ((RateCreate) capturedReq.getRequestedObject()).getCompanyNumber());
	         assertEquals("54585", ((RateCreate) capturedReq.getRequestedObject()).getCustomerAccount());
	         assertEquals("00001", ((RateCreate) capturedReq.getRequestedObject()).getSite());
	         assertEquals("1", ((RateCreate) capturedReq.getRequestedObject()).getContainerGroup());
	         assertEquals("C", ((RateCreate) capturedReq.getRequestedObject()).getChargeCode());
	         assertEquals("A", ((RateCreate) capturedReq.getRequestedObject()).getChargeType());
	         assertEquals("A", ((RateCreate) capturedReq.getRequestedObject()).getChargeMethod());
	         assertEquals("20240303", ((RateCreate) capturedReq.getRequestedObject()).getRateEffectiveDate());
	         assertEquals("20241212", ((RateCreate) capturedReq.getRequestedObject()).getCloseDate());
	         assertEquals("18.00", ((RateCreate) capturedReq.getRequestedObject()).getChargeRate());
	     }

		 @Test
		 public void testGetContractInfo() {
			 String quoteId = "5656565";

			 AaeContractInfoRequest req = new AaeContractInfoRequest(CSWebAction.GET_ALL, "SMITHBO", quoteId);
			 aaeDao.getContractInfo(req);
			 verify(aaeDao, times(1)).getContractInfo(contractInfoCaptor.capture());

			 AaeContractInfoRequest capturedReq = contractInfoCaptor.getValue();
			 assertEquals(CSWebAction.GET_ALL, capturedReq.getAction());
	         assertEquals("SMITHBO", capturedReq.getRequestedUser());
		 }
	    
	    @Test
	    public void testUpdateStatusCode() {
	    	String quoteId = "5658565";
	    	String statusCode = "C";
	    	String notes = "test1";
	    	StatusCodeRequest req = new StatusCodeRequest(CSWebAction.UPDATE, "SMITHBO", statusCode, quoteId, notes);
	    	aaeDao.updateStatusCode(req);
	    	verify(aaeDao, times(1)).updateStatusCode(statusCodeCaptor.capture());

			StatusCodeRequest capturedReq = statusCodeCaptor.getValue();
			assertEquals(CSWebAction.UPDATE, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
	    }
	    
	    @Test
	    public void testGetDropdownOptions() {
	    	String division = "800";
	    	
	    	ListDataRequest req = new ListDataRequest(CSWebAction.GET_ALL, "SMITHBO", division);
	    	aaeDao.getAccountListData(req);
	    	verify(aaeDao, times (1)).getAccountListData(dropDownCaptor.capture());

			ListDataRequest capturedReq = dropDownCaptor.getValue();
			assertEquals(CSWebAction.GET_ALL, capturedReq.getAction());
			assertEquals("SMITHBO", capturedReq.getRequestedUser());
	    }
	    
	    @Test
	    public void testGetAaeErrorReport() {
        	AaeErrorReport errorReport = new AaeErrorReport();
        	errorReport.setReportFromDate("2024-09-06");
        	errorReport.setReportToDate("2024-11-11");
        	errorReport.setIncludeAll("Y");

        	service.getAaeErrorReport(errorReport);

        	verify(aaeDao, times(1)).getErrorReport(reqCaptor.capture());
        	AaeErrorReportRequest capturedReq = reqCaptor.getValue();

        	assertEquals(CSWebAction.AAE_ERROR_REPORT, capturedReq.getAction());
        	assertEquals("mockUser", capturedReq.getRequestedUser());
        	assertEquals(errorReport, capturedReq.getRequestedObject());
    	}

		@Test
		public void testGetAaeExceptionReport() {
			AaeExceptionReport errorReport = new AaeExceptionReport("2024-06-01", "2024-06-30");
			service.getAaeExceptionReport(errorReport);

			verify(aaeDao, times(1)).getExceptionReport(exReqCaptor.capture());
			AaeExceptionReportRequest capturedReq = exReqCaptor.getValue();

			assertEquals(CSWebAction.AAE_EXCEP_REPORT, capturedReq.getAction());
			assertEquals("mockUser", capturedReq.getRequestedUser());
			assertEquals(errorReport, capturedReq.getRequestedObject());
		}
 }

