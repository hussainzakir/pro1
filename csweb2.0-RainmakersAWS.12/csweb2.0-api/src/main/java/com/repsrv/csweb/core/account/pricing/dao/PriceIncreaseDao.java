package com.repsrv.csweb.core.account.pricing.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.account.pricing.PriceIncreaseBulkRequest;
import com.repsrv.csweb.core.mybatis.dao.CbsDao;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface PriceIncreaseDao extends InfoProDao{

	public void processUpload(@Param("request")PriceIncreaseBulkRequest request, 
			@Param("uploadType")String type);
}
