package com.repsrv.csweb.core.obligations.dao;

import java.util.List;

import com.repsrv.csweb.core.model.obligationsSearch.ObligationRegion;
import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

import com.repsrv.csweb.core.model.obligationsSearch.Obligation;
import com.repsrv.csweb.core.model.json.BaseActionRequest;

public interface ObligationsSearchDao extends InfoProDao {
	List<Obligation> getObligations(@Param("request") BaseActionRequest req);

	List<ObligationRegion> getObligationRegions();

}
