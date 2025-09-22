package com.repsrv.csweb.core.account.container.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.repsrv.csweb.core.account.container.dao.ContainerDao;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation.HaulerInfo;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation;
import com.repsrv.csweb.core.account.container.model.HcmtRatesDto;
import com.repsrv.csweb.core.account.container.model.ContainerServiceHistory;
import com.repsrv.csweb.core.account.container.model.IndustrialStatServiceHistory;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.support.exception.SecurityUtils;

import flexjson.JSONSerializer;

@RunWith(PowerMockRunner.class)

@PrepareForTest({ContainerServiceImpl.class, SecurityUtils.class})

public class ContainerServiceImplTest {
    @Test
    public void testGetHcmtRates() {
        ContainerServiceImpl serviceImpl = new ContainerServiceImpl();
        ContainerDao dao = PowerMockito.mock(ContainerDao.class);
        PowerMockito.mockStatic(SecurityUtils.class);
        when(SecurityUtils.getLoggedInUser()).thenReturn("VARELAD");
        HcmtRatesDto rates1 = new HcmtRatesDto();
        rates1.setCompany("625");
        rates1.setAccount("12561");
        rates1.setChargeCode("REA");
        rates1.setChargeCodeDesc(" ");
        rates1.setCloseDate("09/09/2022");
        rates1.setContainer("1");
        rates1.setEffectiveDate("07/12/2020");
        rates1.setFrfAllowed("No");
        rates1.setHaulerCost("218.22");
        rates1.setSite("00001");
        rates1.setUnitOfMeasure("Gallons");
        rates1.setVendorNumber("1323232");
        rates1.setVendorType("ZZ001");
        List<HcmtRatesDto> list = new ArrayList<>();
        list.add(rates1);
        String jsonString = new JSONSerializer().exclude("*.class").deepSerialize(list);
        PowerMockito.doReturn(jsonString).when(dao).getHcmtRates(any(BaseRequest.class), any(StoredProcCallResult.class));
        Whitebox.setInternalState(serviceImpl, "containerDao", dao);
        HaulerInfo haulerInfo = new HaulerInfo(" ", " ", " ", " " ," ", " ");
        HaulerCostInformation haulerCost = new HaulerCostInformation("VARELAD", CSWebAction.GET_ALL, haulerInfo);
        List<HcmtRatesDto> rates = serviceImpl.getHcmtRates(haulerInfo);
        assertNotNull(haulerCost.getRequestedObject());
        assertFalse(rates.isEmpty());
       }
    
 @Test
    public void testGetIndustrialstatisticsServiceHistory() {
        ContainerServiceImpl serviceImpl = new ContainerServiceImpl();
        ContainerDao dao = PowerMockito.mock(ContainerDao.class);
        Whitebox.setInternalState(serviceImpl, "containerDao", dao);
        StoredProcCallResult result = new StoredProcCallResult();
        String jsonString = "[{\"company\":\"625\",\"account\":\"12561\",\"site\":\"00001\",\"container\":1,\"dateServed\":20220909}]";
        PowerMockito.doReturn(jsonString).when(dao).getIndustrialstatisticsServiceHistory(any(String.class), any(String.class), any(String.class), any(int.class), any(int.class), any(StoredProcCallResult.class));
        List<IndustrialStatServiceHistory> history = serviceImpl.getIndustrialstatisticsServiceHistory("625", "12561", "00001", 1, 20220909);
        assertNotNull(history);
        assertFalse(history.isEmpty());
    }

    @Test
    public void testGetContainerServiceHistory() {
        ContainerServiceImpl serviceImpl = new ContainerServiceImpl();
        ContainerDao dao = PowerMockito.mock(ContainerDao.class);
        Whitebox.setInternalState(serviceImpl, "containerDao", dao);
        StoredProcCallResult result = new StoredProcCallResult();
        String jsonString = "[{\"company\":\"625\",\"account\":\"12561\",\"site\":\"00001\",\"container\":1,\"dateServed\":20220909}]";
        PowerMockito.doReturn(jsonString).when(dao).getContainerServiceHistory(any(String.class), any(String.class), any(String.class), any(int.class), any(int.class), any(StoredProcCallResult.class));
        List<ContainerServiceHistory> history = serviceImpl.getContainerServiceHistory("625", "12561", "00001", 1, 20220909);
        assertNotNull(history);
        assertFalse(history.isEmpty());
    }
    
}
