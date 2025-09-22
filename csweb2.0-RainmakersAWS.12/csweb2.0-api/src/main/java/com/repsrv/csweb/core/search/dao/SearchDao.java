package com.repsrv.csweb.core.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.search.AccountLookup;
import com.repsrv.csweb.core.model.search.AccountSearchRequest;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface SearchDao extends InfoProDao{

	List<AccountLookup> getSearchAccounts(@Param("request")AccountSearchRequest request);

}
