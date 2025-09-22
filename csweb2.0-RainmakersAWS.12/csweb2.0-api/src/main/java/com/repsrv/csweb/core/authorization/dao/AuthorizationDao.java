package com.repsrv.csweb.core.authorization.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.UserAuthorization;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AuthorizationDao extends InfoProDao{

	List<UserAuthorization> getUserAuthorizations(String username);
	
	String getUserAuthDetail(@Param("system")String system, @Param("username")String username,
			@Param("result")StoredProcCallResult result);
}
