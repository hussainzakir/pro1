package com.repsrv.csweb.core.account.openmarket.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.account.imports.errorutility.ErrorFileGenerator;
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.openmarket.dao.OpenMarketDao;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketErrorRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

@Service

public class OpenMarketErrorFileService extends JsonResultService implements ExcellSheetsConstants {
    
    @Autowired 
	protected OpenMarketDao openMarketDao;

	public Workbook generateErrorFile(OpenMarketErrorRequest openMarketItem) {

		String accts = getRows(openMarketItem);

		Map<String, List<OpenMarketErrorDto>> errorList = jsonToList(accts);
		
		errorList.entrySet().forEach(errorItem -> 
		logger.debug("LISTAS: "+errorItem.getValue()));
		logger.debug("Account rows {}", errorList.get("ACCOUNT").size());
		logger.debug("Sites rows {}", errorList.get("SITE").size());
		logger.debug("Containers rows {}", errorList.get("CONTAINER").size());
		logger.debug("Rates rows {}", errorList.get("RATE").size());
		logger.debug("Routes rows {}", errorList.get("ROUTE").size());

		InputStream fis = null;
		try {
			fis = getClass().getClassLoader().getResourceAsStream("aai-templates/openMarketErrorTemplate.xlsx");
			
			Workbook workbook = PoiUtil.safeOpenXssWorkbook(fis);
			if(workbook == null)
				throw new TemplateNotReadableException("Template is not readable");
			 
			ErrorFileGenerator.writeData(errorList.get("ACCOUNT"), ACCOUNT_SHEET_NAME, workbook, OpenMarketErrorDto.class);
			ErrorFileGenerator.writeData(errorList.get("SITE"), SITE_SHEET_NAME, workbook, OpenMarketErrorDto.class);
			ErrorFileGenerator.writeData(errorList.get("CONTAINER"), CONTAINER_SHEET_NAME, workbook, OpenMarketErrorDto.class);
			ErrorFileGenerator.writeData(errorList.get("RATE"), RATE_SHEET_NAME, workbook, OpenMarketErrorDto.class);
            ErrorFileGenerator.writeData(errorList.get("ROUTE"), ROUTE_SHEET_NAME, workbook, OpenMarketErrorDto.class);
			
			return workbook;
		} catch (TemplateNotReadableException e) {
			logger.error("Failed to open template", e);
		}finally {
			if(fis != null)
				try { fis.close(); } catch (IOException e) {logger.warn("FIS failed to close or was already closed", e);}
		}
		
		return null;
	}
	

	private String getRows(OpenMarketErrorRequest item){
		StoredProcCallResult result = new StoredProcCallResult();

		logger.debug("error INPUt: "+ item.getProjectId());
		String rowsString = this.openMarketDao.getErrorData(item.getProjectId(), result);
		logger.debug("Fetch rows result {}", result.getErrorMessage());
		logger.debug("RESPOND: "+rowsString);
		return rowsString;
	}

	private Map<String, List<OpenMarketErrorDto>> jsonToList(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<OpenMarketErrorDto>> result = new HashMap<>();
		JsonNode arrayNode;
		List<OpenMarketErrorDto> containerErrors = new ArrayList<>();
		try {
			arrayNode = mapper.readTree(json);
		for (JsonNode node : arrayNode) {
			switch (node.get("entity").asText()) {
				case "ACCOUNT":
				result.put("ACCOUNT", createErrorList(node));
				break;
				case "SITE":
				result.put("SITE", createErrorList(node));
				break;
				case "CONTAINER":
				case "SALES DETAILS":
				containerErrors.addAll(createErrorList(node));
				break;
				case "RATE":
				result.put("RATE", createErrorList(node));
				break;
                case "ROUTE":
                result.put("ROUTE", createErrorList(node));
                break;
				default:
				break;
			}
		} 
		containerErrors.sort((error1, error2) -> {
			int index1 = Integer.parseInt(error1.getRowId());
			int index2 = Integer.parseInt(error2.getRowId());
			if (index1 > index2) {
				return 1;
			}
			if (index1 < index2) {
				return -1;
			}
			return 0;
		});
		 result.put("CONTAINER", containerErrors);
		containerErrors.forEach(error -> {
			logger.debug("CONTAINER LOGS: "+ error.getRowId());
			logger.debug("CONTAINER LOGS: "+ error.getErrorDescription());
		}); 
		logger.debug("RESULT FOR: ");
		Stream.of("ACCOUNT", "SITE", "CONTAINER", "RATE", "ROUTE", "SALES DETAILS").forEach(key -> {
			if (Objects.isNull(result.get(key))){
				result.put(key, new ArrayList<>());
			} 
		});
		return result;
	}catch(Exception e) {
		logger.debug("context", e);
		return result;
	  }
	} 

	private List<OpenMarketErrorDto> createErrorList(JsonNode jsonNode){

		List<OpenMarketErrorDto> result = new ArrayList<>();
		Iterator<JsonNode> iterator = jsonNode.get("errors").iterator();
		while (iterator.hasNext()){
			JsonNode currentError = iterator.next();
			List<String> values = Stream.of("identifier", "workbookId", "value", "error").map(key -> {
				if (currentError.path(key).isMissingNode()) {
					return "";
				}
				return currentError.path(key).asText();
			}).collect(Collectors.toList());

			String rowId = "";
			try {
				rowId = String.valueOf(Integer.parseInt(values.get(1)) + 1);
			} catch (NumberFormatException e) {	
							
			} 

			result.add(new OpenMarketErrorDto(
					values.get(0), rowId, values.get(2), values.get(3)));
		}
		return result;
	}


}
