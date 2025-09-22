package com.repsrv.csweb.core.obligations.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//import com.repsrv.csweb.core.MockUtil;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.obligationsSearch.Obligation;
import com.repsrv.csweb.core.model.obligationsSearch.ObligationRegion;
import com.repsrv.csweb.core.obligations.dao.ObligationsSearchDao;
import com.repsrv.csweb.core.obligations.json.ObligationsSearchRequest;
import com.repsrv.csweb.core.support.exception.SecurityUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityUtils.class)
public class ObligationSearchServiceImplTest {
	
	@InjectMocks
	ObligationsSearchServiceImpl service;
	
	@Mock
    ObligationsSearchDao dao;
	
	@Mock
	MockUtil util;
	
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	@Test
	public void getObligationRegionsList_Success() {
		List<ObligationRegion> obRegions = setupObligationRegions();

		when(dao.getObligationRegions()).thenReturn(obRegions);
		
		List<ObligationRegion> obRegionsList = service.getObligationRegionsList();

		assertTrue( obRegionsList.stream().anyMatch(p-> p.getDescription().equals("Florida Region")));
		verify(dao, times(1)).getObligationRegions();
	}

	@Test
	public void getObligationsSearchResults_Success(){
		List<Obligation> obligationRS = setupObligations();

		ObligationsSearchRequest req = new ObligationsSearchRequest("SAMANTHA", CSWebAction.GET_ALL,
				"1","16098","201808","201808",null,null);
		when(dao.getObligations(req)).thenReturn(obligationRS);

		List<Obligation> obligationList = dao.getObligations(req);

		verify(dao, times(1)).getObligations(req);
	}
	
    @Test
    public void testGetObligationsSearchResults() {
        // Prepare mock data
        List<Obligation> mockResults = setupObligations();

        // Mocking behavior
        when(dao.getObligations(Mockito.any(ObligationsSearchRequest.class))).thenReturn(mockResults);
        PowerMockito.mockStatic(SecurityUtils.class);
        when(SecurityUtils.getLoggedInUser()).thenReturn("SUFTAN");

        // Call the method to be tested
        List<Obligation> results = service.getObligationsSearchResults(
                "regionCode", "obligationId", "from", "to", "amountFrom", "amountTo");

        // Verify behavior
        verify(dao, times(1)).getObligations(Mockito.any(ObligationsSearchRequest.class));
        // Add additional verifications as needed
		assertTrue( results.stream().anyMatch(p-> p.getCompanyName().equals("JENNIFER KARR")));

    }

    @Test
    public void testGetObligationRegionsList() {
        // Prepare mock data
        List<ObligationRegion> mockRegions = new ArrayList<>();

        // Mocking behavior
        when(dao.getObligationRegions()).thenReturn(mockRegions);

        // Call the method to be tested
        List<ObligationRegion> regions = service.getObligationRegionsList();

        // Verify behavior
        verify(dao, times(1)).getObligationRegions();
    }

	private List<ObligationRegion> setupObligationRegions(){
		List<ObligationRegion> obRegions = new ArrayList<ObligationRegion>();
		ObligationRegion regionA = new ObligationRegion();
		regionA.setRegion("A");
		regionA.setDescription("Atlantic Region");
		ObligationRegion regionE = new ObligationRegion();
		regionE.setRegion("E");
		regionE.setDescription("Southeast Region");
		ObligationRegion regionF = new ObligationRegion();
		regionF.setRegion("F");
		regionF.setDescription("Florida Region");
		ObligationRegion regionM = new ObligationRegion();
		regionM.setRegion("M");
		regionM.setDescription("Midwest Region");
		ObligationRegion regionN = new ObligationRegion();
		regionN.setRegion("N");
		regionN.setDescription("Northeast Region");

		obRegions.add(regionA);
		obRegions.add(regionE);
		obRegions.add(regionF);
		obRegions.add(regionM);
		obRegions.add(regionN);

		return obRegions;
	}

	private List<Obligation> setupObligations(){
		List<Obligation> obligationRS = new ArrayList<Obligation>();
		Obligation rowOne = new Obligation();
		rowOne.setCompany("744");
		rowOne.setAccount("35928");
		rowOne.setCompanyName("HOSPICE COMPASSUS");
		rowOne.setObligationId("744001816098");
		rowOne.setObligationDate("2018-08-20");
		rowOne.setOpenAmount("0.00");
		rowOne.setTotalAmount("161.58");
		rowOne.setTransType("INV");
		rowOne.setStatus("Closed");
		rowOne.setRegion("A");
		rowOne.setAccountingPeriod("2018-08-01");
		rowOne.setSite("00001");
		Obligation rowTwo = new Obligation();
		rowTwo.setCompany("633");
		rowTwo.setAccount("34490");
		rowTwo.setCompanyName("JENNIFER KARR");
		rowTwo.setObligationId("633001116098");
		rowTwo.setObligationDate("2018-07-31");
		rowTwo.setOpenAmount("0.00");
		rowTwo.setTotalAmount("-40.19");
		rowTwo.setTransType("C/M");
		rowTwo.setStatus("Closed");
		rowTwo.setRegion("E");
		rowTwo.setAccountingPeriod("2018-08-01");
		rowTwo.setSite("00001");
		Obligation rowThree = new Obligation();
		rowThree.setCompany("902");
		rowThree.setAccount("114357");
		rowThree.setCompanyName("COSMO CONNECTORS CORP");
		rowThree.setObligationId("902008116098");
		rowThree.setObligationDate("2018-07-31");
		rowThree.setOpenAmount("0.00");
		rowThree.setTotalAmount("49.48");
		rowThree.setTransType("INV");
		rowThree.setStatus("Closed");
		rowThree.setRegion("E");
		rowThree.setAccountingPeriod("2018-08-01");
		rowThree.setSite("00001");
		Obligation rowFour = new Obligation();
		rowFour.setCompany("863");
		rowFour.setAccount("2688");
		rowFour.setCompanyName("ELIDA C CONTRERAS");
		rowFour.setObligationId("863001516098");
		rowFour.setObligationDate("2018-08-20");
		rowFour.setOpenAmount("0.00");
		rowFour.setTotalAmount("218.79");
		rowFour.setTransType("INV");
		rowFour.setStatus("Closed");
		rowFour.setRegion("E");
		rowFour.setAccountingPeriod("2018-08-01");
		rowFour.setSite("00001");
		Obligation rowFive = new Obligation();
		rowFive.setCompany("863");
		rowFive.setAccount("2688");
		rowFive.setCompanyName("ELIDA C CONTRERAS");
		rowFive.setObligationId("863001516098");
		rowFive.setObligationDate("2018-08-20");
		rowFive.setOpenAmount("0.00");
		rowFive.setTotalAmount("218.79");
		rowFive.setTransType("INV");
		rowFive.setStatus("Closed");
		rowFive.setRegion("E");
		rowFive.setAccountingPeriod("2018-08-01");
		rowFive.setSite("00002");

		obligationRS.add(rowOne);
		obligationRS.add(rowTwo);
		obligationRS.add(rowThree);
		obligationRS.add(rowFour);
		obligationRS.add(rowFive);
		
		return obligationRS;
	}

}
