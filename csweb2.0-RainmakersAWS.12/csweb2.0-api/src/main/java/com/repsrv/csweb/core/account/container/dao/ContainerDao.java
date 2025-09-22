package com.repsrv.csweb.core.account.container.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface ContainerDao extends InfoProDao{

	String getContainerDetail(@Param("company") String company, 
			@Param("account") String account,  
			@Param("site")String site, 
			@Param("container")int container, 
			@Param("result")StoredProcCallResult result);

	String getContainerRates(@Param("company") String company, 
			@Param("account") String account,  
			@Param("site")String site, 
			@Param("container")int container,
			@Param("urNumber")String urNumber,
			@Param("result")StoredProcCallResult result);
	
	String getHaulerDetail(@Param("company") String company, 
			@Param("account") String account,  
			@Param("site")String site,  
			@Param("result")StoredProcCallResult result);
	
	String getHcmtRates(@Param("request") BaseRequest request,
			@Param("result")StoredProcCallResult result);

	String getIndustrialstatisticsServiceHistory(@Param("company") String company, 
			@Param("account") String account,  
			@Param("site")String site, 
			@Param("container")int container, 
			@Param("DateServed")int DateServed,
			@Param("result")StoredProcCallResult result);	

	String getContainerServiceHistory(@Param("company") String company, 
			@Param("account") String account,  
			@Param("site")String site, 
			@Param("container")int container, 
			@Param("DateServed")int DateServed,
			@Param("result")StoredProcCallResult result);

}
