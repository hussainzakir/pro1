package com.repsrv.csweb.core.account.pricing.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.repsrv.csweb.core.account.pricing.PriceIncreaseBulkRequest;
import com.repsrv.csweb.core.account.pricing.dao.PriceIncreaseDao;
import com.repsrv.csweb.core.account.pricing.model.PriceIncreaseRow;
import com.repsrv.csweb.core.account.pricing.model.PriceIncreaseUploadType;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.support.exception.priceincrease.PriceIncreaseException;
import com.repsrv.csweb.rest.massuploads.MassUploadsResource;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

@Service("priceIncreaseService")
public class PriceIncreaseService {

	protected final Logger logger = LoggerFactory.getLogger(PriceIncreaseService.class);
	
	@Autowired
	private PriceIncreaseDao priceIncreaseDao;
  
    
	public void importPriceIncreases(List<PriceIncreaseRow> inRows, String type) {
		
		List<List<PriceIncreaseRow>> listRows = Lists.partition(new ArrayList<>(inRows), PriceIncreaseBulkRequest.maxRecordsPerRequest);
		logger.debug("Processing {} batches", listRows.size());
		
		int batchNo = 1;
		for(List<PriceIncreaseRow> rows : listRows) {
			PriceIncreaseBulkRequest request = new PriceIncreaseBulkRequest(
					CSWebAction.INSERT, 
					CswebSecurityUtils.getLoggedInUser(), rows);
			
			this.priceIncreaseDao.processUpload(request, type);
			
			logger.debug("Batch no {} result: ", batchNo++, request.getReturnErrorMsg());
			
			
		}

	}
	
	public boolean validateTemplateHeaders(List<String> actualHeaders, PriceIncreaseUploadType uploadType) {
	    String[] expectedHeaders = uploadType.getExpectedHeaders();
	    return actualHeaders.equals(Arrays.asList(expectedHeaders));
	}
}
