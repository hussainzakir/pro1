package com.repsrv.csweb.core.account.container.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.account.container.dao.ContainerDao;
import com.repsrv.csweb.core.account.container.model.ContainerHaulerDto;
import com.repsrv.csweb.core.account.container.model.ContainerRateDto;
import com.repsrv.csweb.core.account.container.model.ContainerServiceHistory;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation.HaulerInfo;
import com.repsrv.csweb.core.account.container.model.HcmtRatesDto;
import com.repsrv.csweb.core.account.container.model.IndustrialStatServiceHistory;
import com.repsrv.csweb.core.model.container.ContainerGroupDetail;
import com.repsrv.csweb.core.model.container.ContainerGroupHauler;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.SecurityUtils;

@Service("containerService")
public class ContainerServiceImpl extends JsonResultService implements ContainerService {

	@Autowired
	private ContainerDao containerDao;
	
	@Override
	public ContainerGroupDetail getContainerDetail(String company, String account, String site, int container) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.containerDao.getContainerDetail(company, account, site, container, result);
		
		logger.debug("Container Detail JSON is: {}",jsonString);
		
		ContainerGroupDetail detail = getJsonDataObject(jsonString, ContainerGroupDetail.class);
		
		return detail;
	}

	@Override
	public Map<String, Set<ContainerGroupRate>> getContainerRates(String company, String account, String site) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.containerDao.getContainerRates(company, account, site, 0, "0", result);
		
		logger.debug("Container	Rates JSON is: {}",jsonString);
		
		List<ContainerRateDto> rates = getJsonDataObject(jsonString, new TypeReference<List<ContainerRateDto>>(){});
		
		
		Map<String, Set<ContainerGroupRate>> containers = groupRates(rates);

		return containers;
	}
	
	private Map<String, Set<ContainerGroupRate>> groupRates(List<ContainerRateDto> rates) {
		
		Map<String, Set<ContainerGroupRate>> ds = rates.stream().collect(
			    Collectors.groupingBy(ContainerRateDto::getContainerGroup, HashMap::new, 
			    Collectors.mapping(ContainerRateDto::toGroupRate, Collectors.toSet()))
			);
		
		return ds;
	}

	@Override
	public Map<String, Set<ContainerGroupHauler>> getHaulerDetail(String company, String account, String site) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.containerDao.getHaulerDetail(company, account, site, result);
		
		logger.debug("Container	Hauler JSON is: {}",jsonString);
		
		List<ContainerHaulerDto> haulers = getJsonDataObject(jsonString, new TypeReference<List<ContainerHaulerDto>>(){});
		
		Map<String, Set<ContainerGroupHauler>> containers = groupHaulers(haulers);

		return containers;
	}
	
	private Map<String, Set<ContainerGroupHauler>> groupHaulers(List<ContainerHaulerDto> haulers) {
		
		Map<String, Set<ContainerGroupHauler>> haulerMap = haulers.stream().collect(
			    Collectors.groupingBy(ContainerHaulerDto::getContainerGroup, HashMap::new, 
			    Collectors.mapping(ContainerHaulerDto::toGroupHauler, Collectors.toSet()))
			);
		
		return haulerMap;
	}

	@Override
	public List<HcmtRatesDto> getHcmtRates(HaulerInfo haulerInfo) {
		StoredProcCallResult result = new StoredProcCallResult();
		HaulerCostInformation request = new HaulerCostInformation(SecurityUtils.getLoggedInUser(), CSWebAction.GET_ALL, haulerInfo);
		String jsonString = this.containerDao.getHcmtRates(request, result);
		return getJsonDataObject(jsonString, new TypeReference<List<HcmtRatesDto>>(){});
	}
	
	@Override
	public List<IndustrialStatServiceHistory> getIndustrialstatisticsServiceHistory(String company, String account, String site, int container, int DateServed) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.containerDao.getIndustrialstatisticsServiceHistory(company,account, site, container, DateServed,  result);
		logger.debug("Industrial Statistic Service History is: {} ",jsonString);
		List<IndustrialStatServiceHistory> IndustrialStatisticsservhistory = getJsonDataObject(jsonString, new TypeReference<List<IndustrialStatServiceHistory>>(){});
		return IndustrialStatisticsservhistory;
	}
	
	@Override
	public List<ContainerServiceHistory> getContainerServiceHistory(String company, String account, String site, int container, int DateServed) {
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.containerDao.getContainerServiceHistory(company,account, site, container, DateServed,  result);
		logger.debug("Container service history is: {} ",jsonString);
		List<ContainerServiceHistory> containerservhistory = getJsonDataObject(jsonString, new TypeReference<List<ContainerServiceHistory>>(){});
	    Set<ContainerServiceHistory> uniqueSet = new HashSet<>(containerservhistory);
	    List<ContainerServiceHistory> uniqueList = new ArrayList<>(uniqueSet);
	    return uniqueList;
	}
}