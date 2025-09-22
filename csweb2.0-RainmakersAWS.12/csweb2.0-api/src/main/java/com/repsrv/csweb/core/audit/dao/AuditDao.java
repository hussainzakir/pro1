package com.repsrv.csweb.core.audit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.repsrv.csweb.core.model.audit.AuditRequest;
import com.repsrv.csweb.core.model.audit.AuditResults;
import com.repsrv.csweb.core.mybatis.dao.InfoProDao;

public interface AuditDao extends InfoProDao{
	List<AuditResults> getAuditResults(@Param("request")AuditRequest request);
}
