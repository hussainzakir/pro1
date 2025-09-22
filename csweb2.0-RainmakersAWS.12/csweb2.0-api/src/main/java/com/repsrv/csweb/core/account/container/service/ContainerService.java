package com.repsrv.csweb.core.account.container.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.repsrv.csweb.core.account.container.model.ContainerServiceHistory;
import com.repsrv.csweb.core.account.container.model.IndustrialStatServiceHistory;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation.HaulerInfo;
import com.repsrv.csweb.core.account.container.model.HcmtRatesDto;
import com.repsrv.csweb.core.model.container.ContainerGroupDetail;
import com.repsrv.csweb.core.model.container.ContainerGroupHauler;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;

public interface ContainerService {

	ContainerGroupDetail getContainerDetail(String company, String account, String site, int container);
	
	Map<String, Set<ContainerGroupRate>> getContainerRates(String company, String account, String site);
	
	Map<String, Set<ContainerGroupHauler>> getHaulerDetail(String company, String account, String site);
	
	public List<HcmtRatesDto> getHcmtRates(HaulerInfo haulerInfo);
		List<ContainerServiceHistory> getContainerServiceHistory(String company, String account, String site, int container, int DateServed);
	List<IndustrialStatServiceHistory> getIndustrialstatisticsServiceHistory(String company, String account, String site, int container, int DateServed);
}
