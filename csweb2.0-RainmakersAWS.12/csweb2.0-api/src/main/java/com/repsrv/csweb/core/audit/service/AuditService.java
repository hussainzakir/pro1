package com.repsrv.csweb.core.audit.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import com.repsrv.csweb.core.model.audit.AuditRequest;
import com.repsrv.csweb.core.model.audit.AuditResults;

public interface AuditService {

	List<AuditResults> getAuditResults(AuditRequest auditRequest);
	public void exportAuditResults(AuditRequest auditRequest, ByteArrayOutputStream outputStream);
}
