package com.repsrv.csweb.core.account.imports3.dao;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.account.imports3.model.Aob3ProjectDetailsRequest;
import com.repsrv.csweb.core.model.account.aob3.Aob3UploadHistoryRequest;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AccountImport3Dao extends InfoProDao {

    String getProjectsList(@Param("request") Aob3UploadHistoryRequest request);

    String getProjectDetails(@Param("request") Aob3ProjectDetailsRequest request);
    
}

