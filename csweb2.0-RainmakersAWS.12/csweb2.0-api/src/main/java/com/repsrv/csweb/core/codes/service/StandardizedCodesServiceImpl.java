package com.repsrv.csweb.core.codes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.codes.dao.StandardizedCodesDao;
import com.repsrv.csweb.core.model.codes.CodeWrapper;
import com.repsrv.csweb.core.model.codes.StandardizedCode;
import com.repsrv.csweb.core.model.codes.StandardizedCodeType;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("standardizedCodesService")
public class StandardizedCodesServiceImpl extends JsonResultService implements StandardizedCodesService {

	@Autowired
	private StandardizedCodesDao standardizedCodesDao;

	@Override
	public List<StandardizedCode> getStandardizedCodesByType(String company, StandardizedCodeType type, CodeStatus status) {
		StoredProcCallResult result = new StoredProcCallResult();

		String jsonResponse = this.standardizedCodesDao
				.getStandardizedCodesByType(company, type.name(), type.getCode(),
				status.getCode(), result);
		
		
		logger.debug("JSON string result: {}", jsonResponse);
		CodeWrapper wrapper = getJsonDataObject(jsonResponse, CodeWrapper.class);
		return wrapper.codes;
	}

	
}
