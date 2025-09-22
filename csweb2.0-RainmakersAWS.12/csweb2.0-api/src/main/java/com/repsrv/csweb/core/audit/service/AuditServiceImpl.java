package com.repsrv.csweb.core.audit.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.repsrv.csweb.core.audit.dao.AuditDao;
import com.repsrv.csweb.core.model.audit.AuditRequest;
import com.repsrv.csweb.core.model.audit.AuditResults;

@Service("auditService")
public class AuditServiceImpl implements AuditService {
	private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
	@Autowired
	private AuditDao auditDao;

	@Override
	public List<AuditResults> getAuditResults(AuditRequest auditRequest) {
		auditRequest.setCbs(auditRequest.getCbs() != null ? auditRequest.getCbs() : "");
		auditRequest.setFromDate(auditRequest.getFromDate().replace("-", ""));
		auditRequest.setToDate(auditRequest.getToDate().replace("-", ""));
		auditRequest.setFileFilter(auditRequest.getFileFilter() != null ? auditRequest.getFileFilter() : "");
		auditRequest.setStartIndex(auditRequest.getStartIndex());
		auditRequest.setEndIndex(auditRequest.getEndIndex());
		auditRequest.setSort("");
		auditRequest.setTotalRows(new BigDecimal("0.0"));
		auditRequest.setDivision(auditRequest.getDivision() != null ? auditRequest.getDivision() : "");
		auditRequest.setSite(auditRequest.getSite() != null ? auditRequest.getSite() : "");
		auditRequest.setGroup(auditRequest.getGroup() != null ? auditRequest.getGroup() : "");
		auditRequest.setAccountNumber(auditRequest.getAccountNumber() != null ? auditRequest.getAccountNumber() : "");
		return auditDao.getAuditResults(auditRequest);
	}
	
	public void exportAuditResults(AuditRequest auditRequest,ByteArrayOutputStream outputStream) {
		try(CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {
		List<AuditResults> auditResults =  getAuditResults(auditRequest);
		StringBuilder csvStringBuilder = new StringBuilder();
		String[] header = {"CBS","Service Account","Service Name","Loc Id","Site","G","Field","Old Value","New Value","Date","Time","User Id"};
		csvWriter.writeNext(header);
		for (AuditResults auditResult : auditResults) {
			csvStringBuilder.setLength(0);
			csvStringBuilder.append(auditResult.toCSV());
			String[] csvData = csvStringBuilder.toString().split(",");
			csvWriter.writeNext(csvData);
		}
		} catch (IOException e) {
			logger.info("Exception while writing the Audit Results Data");
		}
	}
}
