package com.repsrv.csweb.core.model.aae;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.repsrv.csweb.core.model.listData.ListDataContainerSizeValues;
import com.repsrv.csweb.core.model.listData.ListDataDisposalCodeValues;
import com.repsrv.csweb.core.model.listData.ListDataResponse;
import com.repsrv.csweb.core.model.listData.ListDataTansactionReasonCodeValues;
import com.repsrv.csweb.core.model.listData.ListDataValues;

public class ListDataResponseTest {

    private ListDataResponse listDataResponse;

    @Before
    public void setUp() {
        listDataResponse = new ListDataResponse();
    }
    
    @Test
    public void testInitialization() {
        assertNull(listDataResponse.getInvoiceGroup());
        assertNull(listDataResponse.getSalesRep());
        assertNull(listDataResponse.getChargeCode());
        assertNull(listDataResponse.getLatePayFeePolicy());
        assertNull(listDataResponse.getStopCode());
        assertNull(listDataResponse.getTerritory());
        assertNull(listDataResponse.getCntrTypeSize());
        assertNull(listDataResponse.getAcquisitionCode());
        assertNull(listDataResponse.getCustomerCategory());
        assertNull(listDataResponse.getAccountClass());
        assertNull(listDataResponse.getResidentialDistrict());
        assertNull(listDataResponse.getContractStatus());
        assertNull(listDataResponse.getServicingRep());
        assertNull(listDataResponse.getSigningRep());
        assertNull(listDataResponse.getCompetitorCode());
        assertNull(listDataResponse.getTransactionReasonCode());
    }

    @Test
    public void testInvoiceGroup() {
        List<ListDataValues> invoiceGroup = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Resi 1/4/7/10");
        invoiceGroup.add(value1);
        listDataResponse.setInvoiceGroup(invoiceGroup);
        assertEquals(invoiceGroup, listDataResponse.getInvoiceGroup());
        assertEquals(1, listDataResponse.getInvoiceGroup().size());
        assertEquals("Resi 1/4/7/10", listDataResponse.getInvoiceGroup().get(0).getValue());
    }
    
    @Test
    public void testSalesRep() {
        List<ListDataValues> salesRep = new ArrayList<>();
        
        ListDataValues rep1 = new ListDataValues();
        rep1.setValue("House");
        salesRep.add(rep1);
        
        ListDataValues rep2 = new ListDataValues();
        rep2.setValue("Contract");
        salesRep.add(rep2);
        
        listDataResponse.setSalesRep(salesRep);
        
        assertEquals(salesRep, listDataResponse.getSalesRep());
        assertEquals(2, listDataResponse.getSalesRep().size());
        assertEquals("House", listDataResponse.getSalesRep().get(0).getValue());
        assertEquals("Contract", listDataResponse.getSalesRep().get(1).getValue());
    }

    @Test
    public void testChargeCode() {
        List<ListDataValues> chargeCode = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Holiday Service");
        chargeCode.add(value1);
        listDataResponse.setChargeCode(chargeCode);
        assertEquals(chargeCode, listDataResponse.getChargeCode());
        assertEquals(1, listDataResponse.getChargeCode().size());
        assertEquals("Holiday Service", listDataResponse.getChargeCode().get(0).getValue());
    }

    @Test
    public void testLatePayFeePolicy() {
        List<ListDataValues> latePayFeePolicy = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Default");
        latePayFeePolicy.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Resi");
        latePayFeePolicy.add(value2);
        
        listDataResponse.setLatePayFeePolicy(latePayFeePolicy);
        
        assertEquals(latePayFeePolicy, listDataResponse.getLatePayFeePolicy());
        assertEquals(2, listDataResponse.getLatePayFeePolicy().size());
        assertEquals("Default", listDataResponse.getLatePayFeePolicy().get(0).getValue());
        assertEquals("Resi", listDataResponse.getLatePayFeePolicy().get(1).getValue());
    }

    @Test
    public void testStopCode() {
        List<ListDataValues> stopCode = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Curbside");
        stopCode.add(value1);
        listDataResponse.setStopCode(stopCode);
        assertEquals(stopCode, listDataResponse.getStopCode());
        assertEquals(1, listDataResponse.getStopCode().size());
        assertEquals("Curbside", listDataResponse.getStopCode().get(0).getValue());
    }

    @Test
    public void testTerritory() {
        List<ListDataValues> territory = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Residential");
        territory.add(value1);
        listDataResponse.setTerritory(territory);
        assertEquals(territory, listDataResponse.getTerritory());
        assertEquals(1, listDataResponse.getTerritory().size());
        assertEquals("Residential", listDataResponse.getTerritory().get(0).getValue());
    }

    @Test
    public void testCntrTypeSize() {
        List<ListDataContainerSizeValues> cntrTypeSize = new ArrayList<>();
        
        ListDataContainerSizeValues value1 = new ListDataContainerSizeValues();
        value1.setDescription("FL 8.00: Waste Container 8 CU YD");
        value1.setContainerType("FL");
        value1.setContainerSize("8 CU");
        value1.setCompactorFlag("YD");
        cntrTypeSize.add(value1);
        
        listDataResponse.setCntrTypeSize(cntrTypeSize);
        
        assertEquals(cntrTypeSize, listDataResponse.getCntrTypeSize());
        assertEquals(1, listDataResponse.getCntrTypeSize().size());
        assertEquals("FL 8.00: Waste Container 8 CU YD", listDataResponse.getCntrTypeSize().get(0).getDescription());
        assertEquals("FL", listDataResponse.getCntrTypeSize().get(0).getContainerType());
        assertEquals("8 CU", listDataResponse.getCntrTypeSize().get(0).getContainerSize());
        assertEquals("YD", listDataResponse.getCntrTypeSize().get(0).getCompactorFlag());
    }

    @Test
    public void testAcquisitionCode() {
        List<ListDataValues> acquisitionCode = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Hickory");
        acquisitionCode.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Alpha Waste");
        acquisitionCode.add(value2);
        
        listDataResponse.setAcquisitionCode(acquisitionCode);
        
        assertEquals(acquisitionCode, listDataResponse.getAcquisitionCode());
        assertEquals(2, listDataResponse.getAcquisitionCode().size());
        assertEquals("Hickory", listDataResponse.getAcquisitionCode().get(0).getValue());
        assertEquals("Alpha Waste", listDataResponse.getAcquisitionCode().get(1).getValue());
    }

    @Test
    public void testCustomerCategory() {
        List<ListDataValues> customerCategory = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Residential");
        customerCategory.add(value1);
        listDataResponse.setCustomerCategory(customerCategory);
        assertEquals(customerCategory, listDataResponse.getCustomerCategory());
        assertEquals(1, listDataResponse.getCustomerCategory().size());
        assertEquals("Residential", listDataResponse.getCustomerCategory().get(0).getValue());
    }

    @Test
    public void testAccountClass() {
        List<ListDataValues> accountClass = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Foreign Canada");
        accountClass.add(value1);
        listDataResponse.setAccountClass(accountClass);
        assertEquals(accountClass, listDataResponse.getAccountClass());
        assertEquals(1, listDataResponse.getAccountClass().size());
        assertEquals("Foreign Canada", listDataResponse.getAccountClass().get(0).getValue());
    }

    @Test
    public void testResidentialDistrict() {
        List<ListDataValues> residentialDistrict = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Firestone");
        residentialDistrict.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Hawthorne");
        residentialDistrict.add(value2);
        
        listDataResponse.setResidentialDistrict(residentialDistrict);
        
        assertEquals(residentialDistrict, listDataResponse.getResidentialDistrict());
        assertEquals(2, listDataResponse.getResidentialDistrict().size());
        assertEquals("Firestone", listDataResponse.getResidentialDistrict().get(0).getValue());
        assertEquals("Hawthorne", listDataResponse.getResidentialDistrict().get(1).getValue());
    }

    @Test
    public void testContractStatus() {
        List<ListDataValues> contractStatus = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Signed");
        contractStatus.add(value1);
        listDataResponse.setContractStatus(contractStatus);
        assertEquals(contractStatus, listDataResponse.getContractStatus());
        assertEquals(1, listDataResponse.getContractStatus().size());
        assertEquals("Signed", listDataResponse.getContractStatus().get(0).getValue());
    }

    @Test
    public void testServicingRep() {
        List<ListDataValues> servicingRep = new ArrayList<>();
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Brian Thatcher");
        servicingRep.add(value1);
        listDataResponse.setServicingRep(servicingRep);
        assertEquals(servicingRep, listDataResponse.getServicingRep());
        assertEquals(1, listDataResponse.getServicingRep().size());
        assertEquals("Brian Thatcher", listDataResponse.getServicingRep().get(0).getValue());
    }

    @Test
    public void testSigningRep() {
        List<ListDataValues> signingRep = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Jim Smith");
        signingRep.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Jessie Nelson");
        signingRep.add(value2);
        
        listDataResponse.setSigningRep(signingRep);
        
        assertEquals(signingRep, listDataResponse.getSigningRep());
        assertEquals(2, listDataResponse.getSigningRep().size());
        assertEquals("Jim Smith", listDataResponse.getSigningRep().get(0).getValue());
        assertEquals("Jessie Nelson", listDataResponse.getSigningRep().get(1).getValue());
    }

    @Test
    public void testCompetitorCode() {
        List<ListDataValues> competitorCode = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Jet-A-Way");
        competitorCode.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Bugbee");
        competitorCode.add(value2);
        
        listDataResponse.setCompetitorCode(competitorCode);
        
        assertEquals(competitorCode, listDataResponse.getCompetitorCode());
        assertEquals(2, listDataResponse.getCompetitorCode().size());
        assertEquals("Jet-A-Way", listDataResponse.getCompetitorCode().get(0).getValue());
        assertEquals("Bugbee", listDataResponse.getCompetitorCode().get(1).getValue());
    }

    @Test
    public void testTransactionReasonCode() {
        List<ListDataTansactionReasonCodeValues> transReasCode = new ArrayList<>();
        
        ListDataTansactionReasonCodeValues value1 = new ListDataTansactionReasonCodeValues();
        value1.setTransactionCode("01");
        value1.setReasonCode("01");
        value1.setDescription("NEW NEW");
        transReasCode.add(value1);
        
        ListDataTansactionReasonCodeValues value2 = new ListDataTansactionReasonCodeValues();
        value2.setTransactionCode("03");
        value2.setReasonCode("43");
        value2.setDescription("PRICE INCREASE RESIDENTIAL REVENUE VERIFICATION");
        transReasCode.add(value2);
        
        listDataResponse.setTransactionReasonCode(transReasCode);
        
        assertEquals(transReasCode, listDataResponse.getTransactionReasonCode());
        assertEquals(2, listDataResponse.getTransactionReasonCode().size());
        assertEquals("01", listDataResponse.getTransactionReasonCode().get(0).getTransactionCode());
        assertEquals("01", listDataResponse.getTransactionReasonCode().get(0).getReasonCode());
        assertEquals("NEW NEW", listDataResponse.getTransactionReasonCode().get(0).getDescription());
        assertEquals("03", listDataResponse.getTransactionReasonCode().get(1).getTransactionCode());
        assertEquals("43", listDataResponse.getTransactionReasonCode().get(1).getReasonCode());
        assertEquals("PRICE INCREASE RESIDENTIAL REVENUE VERIFICATION", listDataResponse.getTransactionReasonCode().get(1).getDescription());
    }
    
    @Test
    public void testLeadsourceCode() {
        List<ListDataValues> leadsourceCode = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Direct Mail");
        leadsourceCode.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Phone Inquiry");
        leadsourceCode.add(value2);
        
        listDataResponse.setLeadsourceCode(leadsourceCode);
        
        assertEquals(leadsourceCode, listDataResponse.getLeadsourceCode());
        assertEquals(2, listDataResponse.getLeadsourceCode().size());
        assertEquals("Direct Mail", listDataResponse.getLeadsourceCode().get(0).getValue());
        assertEquals("Phone Inquiry", listDataResponse.getLeadsourceCode().get(1).getValue());
    }

    @Test
    public void testChargeType() {
        List<ListDataValues> chargeType = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Service");
        chargeType.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Product");
        chargeType.add(value2);
        
        listDataResponse.setChargeType(chargeType);
        
        assertEquals(chargeType, listDataResponse.getChargeType());
        assertEquals(2, listDataResponse.getChargeType().size());
        assertEquals("Service", listDataResponse.getChargeType().get(0).getValue());
        assertEquals("Product", listDataResponse.getChargeType().get(1).getValue());
    }

    @Test
    public void testChargeMethod() {
        List<ListDataValues> chargeMethod = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Volume");
        chargeMethod.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Flat");
        chargeMethod.add(value2);
        
        listDataResponse.setChargeMethod(chargeMethod);
        
        assertEquals(chargeMethod, listDataResponse.getChargeMethod());
        assertEquals(2, listDataResponse.getChargeMethod().size());
        assertEquals("Volume", listDataResponse.getChargeMethod().get(0).getValue());
        assertEquals("Flat", listDataResponse.getChargeMethod().get(1).getValue());
    }

    @Test
    public void testRevenueDisCode() {
        List<ListDataValues> revenueDisCode = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Rome Commercial");
        revenueDisCode.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Winder Commercial");
        revenueDisCode.add(value2);
        
        listDataResponse.setRevenueDisCode(revenueDisCode);
        
        assertEquals(revenueDisCode, listDataResponse.getRevenueDisCode());
        assertEquals(2, listDataResponse.getRevenueDisCode().size());
        assertEquals("Rome Commercial", listDataResponse.getRevenueDisCode().get(0).getValue());
        assertEquals("Winder Commercial", listDataResponse.getRevenueDisCode().get(1).getValue());
    }

    @Test
    public void testRateType() {
        List<ListDataValues> rateType = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Employee");
        rateType.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Hardship");
        rateType.add(value2);
        
        listDataResponse.setRateType(rateType);
        
        assertEquals(rateType, listDataResponse.getRateType());
        assertEquals(2, listDataResponse.getRateType().size());
        assertEquals("Employee", listDataResponse.getRateType().get(0).getValue());
        assertEquals("Hardship", listDataResponse.getRateType().get(1).getValue());
    }

    @Test
    public void testRecurringChargeFreq() {
        List<ListDataValues> recurringChargeFreq = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Annual");
        recurringChargeFreq.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Semi Annual");
        recurringChargeFreq.add(value2);
        
        listDataResponse.setRecurringChargeFreq(recurringChargeFreq);
        
        assertEquals(recurringChargeFreq, listDataResponse.getRecurringChargeFreq());
        assertEquals(2, listDataResponse.getRecurringChargeFreq().size());
        assertEquals("Annual", listDataResponse.getRecurringChargeFreq().get(0).getValue());
        assertEquals("Semi Annual", listDataResponse.getRecurringChargeFreq().get(1).getValue());
    }

    @Test
    public void testAccountType() {
        List<ListDataValues> accountType = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Seasonal");
        accountType.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Permanent");
        accountType.add(value2);
        
        listDataResponse.setAccountType(accountType);
        
        assertEquals(accountType, listDataResponse.getAccountType());
        assertEquals(2, listDataResponse.getAccountType().size());
        assertEquals("Seasonal", listDataResponse.getAccountType().get(0).getValue());
        assertEquals("Permanent", listDataResponse.getAccountType().get(1).getValue());
    }

    @Test
    public void testContainerIdcode() {
        List<ListDataValues> containerIdcode = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("HOA");
        containerIdcode.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Unknown");
        containerIdcode.add(value2);
        
        listDataResponse.setContainerIdcode(containerIdcode);
        
        assertEquals(containerIdcode, listDataResponse.getContainerIdcode());
        assertEquals(2, listDataResponse.getContainerIdcode().size());
        assertEquals("HOA", listDataResponse.getContainerIdcode().get(0).getValue());
        assertEquals("Unknown", listDataResponse.getContainerIdcode().get(1).getValue());
    }

    @Test
    public void testSpecialHandling() {
        List<ListDataValues> specialHandling = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Metal");
        specialHandling.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Drums");
        specialHandling.add(value2);
        
        listDataResponse.setSpecialHandling(specialHandling);
        
        assertEquals(specialHandling, listDataResponse.getSpecialHandling());
        assertEquals(2, listDataResponse.getSpecialHandling().size());
        assertEquals("Metal", listDataResponse.getSpecialHandling().get(0).getValue());
        assertEquals("Drums", listDataResponse.getSpecialHandling().get(1).getValue());
    }

    @Test
    public void testReceiptRequired() {
        List<ListDataValues> receiptRequired = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("No");
        receiptRequired.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Cash on Delivery");
        receiptRequired.add(value2);
        
        listDataResponse.setReceiptRequired(receiptRequired);
        
        assertEquals(receiptRequired, listDataResponse.getReceiptRequired());
        assertEquals(2, listDataResponse.getReceiptRequired().size());
        assertEquals("No", listDataResponse.getReceiptRequired().get(0).getValue());
        assertEquals("Cash on Delivery", listDataResponse.getReceiptRequired().get(1).getValue());
    }
    
    @Test
    public void testDisCdePriceCde() {
        List<ListDataDisposalCodeValues> disCdePriceCde = new ArrayList<>();
        
        ListDataDisposalCodeValues value1 = new ListDataDisposalCodeValues();
        value1.setDisposalCode("AR");
        value1.setDisposalPriceCode("01");
        value1.setDescription("AR 01 AMERICAN RECYCLE-ATL");
        disCdePriceCde.add(value1);
        
        ListDataDisposalCodeValues value2 = new ListDataDisposalCodeValues();
        value2.setDisposalCode("SG");
        value2.setDisposalPriceCode("04");
        value2.setDescription("SG 04 SAFEGUARD ASBESTOS");
        disCdePriceCde.add(value2);
        
        listDataResponse.setDisCdePriceCde(disCdePriceCde);
        
        assertEquals(disCdePriceCde, listDataResponse.getDisCdePriceCde());
        assertEquals(2, listDataResponse.getDisCdePriceCde().size());
        assertEquals("AR", listDataResponse.getDisCdePriceCde().get(0).getDisposalCode());
        assertEquals("01", listDataResponse.getDisCdePriceCde().get(0).getDisposalPriceCode());
        assertEquals("AR 01 AMERICAN RECYCLE-ATL", listDataResponse.getDisCdePriceCde().get(0).getDescription());
        assertEquals("SG", listDataResponse.getDisCdePriceCde().get(1).getDisposalCode());
        assertEquals("04", listDataResponse.getDisCdePriceCde().get(1).getDisposalPriceCode());
        assertEquals("SG 04 SAFEGUARD ASBESTOS", listDataResponse.getDisCdePriceCde().get(1).getDescription());
    }
    
    @Test
    public void testWasteMaterialType() {
        List<ListDataValues> wasteMaterialType = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Hazardous");
        wasteMaterialType.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Metal");
        wasteMaterialType.add(value2);
        
        listDataResponse.setWasteMaterialType(wasteMaterialType);
        
        assertEquals(wasteMaterialType, listDataResponse.getWasteMaterialType());
        assertEquals(2, listDataResponse.getWasteMaterialType().size());
        assertEquals("Hazardous", listDataResponse.getWasteMaterialType().get(0).getValue());
        assertEquals("Metal", listDataResponse.getWasteMaterialType().get(1).getValue());
    }

    @Test
    public void testUom() {
        List<ListDataValues> uom = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("Day");
        uom.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("Each");
        uom.add(value2);
        
        listDataResponse.setUom(uom);
        
        assertEquals(uom, listDataResponse.getUom());
        assertEquals(2, listDataResponse.getUom().size());
        assertEquals("Day", listDataResponse.getUom().get(0).getValue());
        assertEquals("Each", listDataResponse.getUom().get(1).getValue());
    }

    @Test
    public void testNaics() {
        List<ListDataValues> naics = new ArrayList<>();
        
        ListDataValues value1 = new ListDataValues();
        value1.setValue("National Security");
        naics.add(value1);
        
        ListDataValues value2 = new ListDataValues();
        value2.setValue("International Affairs");
        naics.add(value2);
        
        listDataResponse.setNaics(naics);
        
        assertEquals(naics, listDataResponse.getNaics());
        assertEquals(2, listDataResponse.getNaics().size());
        assertEquals("National Security", listDataResponse.getNaics().get(0).getValue());
        assertEquals("International Affairs", listDataResponse.getNaics().get(1).getValue());
    }
    
    @Test
    public void testNullHandling1() {
        listDataResponse.setInvoiceGroup(null);
        assertNull(listDataResponse.getInvoiceGroup());

        listDataResponse.setSalesRep(null);
        assertNull(listDataResponse.getSalesRep());

        listDataResponse.setChargeCode(null);
        assertNull(listDataResponse.getChargeCode());

        listDataResponse.setLatePayFeePolicy(null);
        assertNull(listDataResponse.getLatePayFeePolicy());

        listDataResponse.setStopCode(null);
        assertNull(listDataResponse.getStopCode());

        listDataResponse.setTerritory(null);
        assertNull(listDataResponse.getTerritory());

        listDataResponse.setCntrTypeSize(null);
        assertNull(listDataResponse.getCntrTypeSize());

        listDataResponse.setAcquisitionCode(null);
        assertNull(listDataResponse.getAcquisitionCode());

        listDataResponse.setCustomerCategory(null);
        assertNull(listDataResponse.getCustomerCategory());

        listDataResponse.setAccountClass(null);
        assertNull(listDataResponse.getAccountClass());

        listDataResponse.setResidentialDistrict(null);
        assertNull(listDataResponse.getResidentialDistrict());

        listDataResponse.setContractStatus(null);
        assertNull(listDataResponse.getContractStatus());

        listDataResponse.setServicingRep(null);
        assertNull(listDataResponse.getServicingRep());

        listDataResponse.setSigningRep(null);
        assertNull(listDataResponse.getSigningRep());

        listDataResponse.setCompetitorCode(null);
        assertNull(listDataResponse.getCompetitorCode());

        listDataResponse.setTransactionReasonCode(null);
        assertNull(listDataResponse.getTransactionReasonCode());
    }
    
    @Test
    public void testNullHandling2() {
         listDataResponse.setLeadsourceCode(null);
         assertNull(listDataResponse.getLeadsourceCode());

         listDataResponse.setChargeType(null);
         assertNull(listDataResponse.getChargeType());

         listDataResponse.setChargeMethod(null);
         assertNull(listDataResponse.getChargeMethod());

         listDataResponse.setRevenueDisCode(null);
         assertNull(listDataResponse.getRevenueDisCode());

         listDataResponse.setRateType(null);
         assertNull(listDataResponse.getRateType());

         listDataResponse.setRecurringChargeFreq(null);
         assertNull(listDataResponse.getRecurringChargeFreq());

         listDataResponse.setAccountType(null);
         assertNull(listDataResponse.getAccountType());

         listDataResponse.setContainerIdcode(null);
         assertNull(listDataResponse.getContainerIdcode());

         listDataResponse.setSpecialHandling(null);
         assertNull(listDataResponse.getSpecialHandling());

         listDataResponse.setReceiptRequired(null);
         assertNull(listDataResponse.getReceiptRequired());

         listDataResponse.setDisCdePriceCde(null);
         assertNull(listDataResponse.getDisCdePriceCde());

         listDataResponse.setWasteMaterialType(null);
         assertNull(listDataResponse.getWasteMaterialType());

         listDataResponse.setUom(null);
         assertNull(listDataResponse.getUom());

         listDataResponse.setNaics(null);
         assertNull(listDataResponse.getNaics());  	
    }
}
