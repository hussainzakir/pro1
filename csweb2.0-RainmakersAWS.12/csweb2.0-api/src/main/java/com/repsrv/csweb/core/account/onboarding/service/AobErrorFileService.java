package com.repsrv.csweb.core.account.onboarding.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.option.PoijiOptions;
import com.poiji.save.MappedFields;
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.onboarding.dao.AccountOnBoardingDao;
import com.repsrv.csweb.core.model.account.onboarding.AobGenerateErrorRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

@Service
public class AobErrorFileService extends JsonResultService implements ExcellSheetsConstants{

	@Autowired 
	protected AccountOnBoardingDao aobDao;

	public Workbook generateErrorFile(AobGenerateErrorRequest aobItem) {

		String accts = getRows(aobItem);

		Map<String, List<AobErrorDto>> errorList = jsonToList(accts);
		
		errorList.entrySet().forEach(errorItem -> 
		logger.info("LISTAS: "+errorItem.getValue()));
		logger.debug("Account rows {}", errorList.get("ACCOUNT").size());
		logger.debug("Sites rows {}", errorList.get("SITE").size());
		logger.debug("Containers rows {}", errorList.get("CONTAINER").size());
		logger.debug("Rates rows {}", errorList.get("RATE").size());
		
		InputStream fis = null;
		try {
			fis = getClass().getClassLoader().getResourceAsStream("aai-templates/errorTemplate.xlsx");
			
			Workbook workbook = PoiUtil.safeOpenXssWorkbook(fis);
			if(workbook == null)
				throw new TemplateNotReadableException("Template is not readable");
			 
			writeData(errorList.get("ACCOUNT"), ACCOUNT_SHEET_NAME, workbook, AobErrorDto.class);
			writeData(errorList.get("SITE"), SITE_SHEET_NAME, workbook, AobErrorDto.class);
			writeData(errorList.get("CONTAINER"), CONTAINER_SHEET_NAME, workbook, AobErrorDto.class);
			writeData(errorList.get("RATE"), RATE_SHEET_NAME, workbook, AobErrorDto.class);
			
			return workbook;
		} catch (TemplateNotReadableException e) {
			logger.error("Failed to open template", e);
		}finally {
			if(fis != null)
				try { fis.close(); } catch (IOException e) {logger.warn("FIS failed to close or was already closed", e);}
		}
		
		return null;
	}
	
	
	private <T> void writeData(List<T> data, String sheetName, Workbook workbook, Class<?> clazz) {
		Sheet sheet = workbook.getSheet(sheetName);
		PoijiOptions opts = PoijiOptions.PoijiOptionsBuilder
				.settings().disableXLSXNumberCellFormat()
				.headerCount(1)
				.headerStart(0)
				.sheetName(sheetName)
				.build();

		
		final MappedFields mappedFields = new MappedFields(clazz, opts).parseEntity();
		int rowIndex = 1;
		
        for (final T instance : data) {
        	final org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowIndex++);
            try {
				setValuesFromKnownFields(row, instance, mappedFields);
			} catch (Exception e) {
				logger.error("Failed to set cell values for sheet " + sheetName, e);
			}
        }
		
		logger.info("Class for writing sheet {} is {} wrote {} rows", sheetName, clazz.getName(), (rowIndex - 6));

	}


	private <T> void setValuesFromKnownFields(final org.apache.poi.ss.usermodel.Row row, 
			final T instance, MappedFields mappedFields) throws IllegalAccessException {
         for (Map.Entry<Field, Integer> orders : mappedFields.getOrders().entrySet()) {
            final Cell cell = row.createCell(orders.getValue());
            final Field field = orders.getKey();
            
            cell.setCellValue(String.valueOf(field.get(instance)));
        }
    }


	private String getRows(AobGenerateErrorRequest item){
		StoredProcCallResult result = new StoredProcCallResult();

		logger.info("error INPUt: "+ item.getProjectId());
		String rowsString = this.aobDao.getErrorData(item.getProjectId(), result);
		logger.info("Fetch rows result {}", result.getErrorMessage());
		logger.info("RESPOND: "+rowsString);
		return rowsString;
	}

	private Map<String, List<AobErrorDto>> jsonToList(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<AobErrorDto>> result = new HashMap<>();
		JsonNode arrayNode;
		List<AobErrorDto> containerErrors = new ArrayList<>();
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
				case "ROUTE":
				case "SALES DETAILS":
				containerErrors.addAll(createErrorList(node));
				break;
				case "RATE":
				result.put("RATE", createErrorList(node));
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
			logger.info("CONTAINER LOGS: "+ error.getRowId());
			logger.info("CONTAINER LOGS: "+ error.getErrorDescription());
		}); 
		logger.info("RESULT FOR: ");
		Stream.of("ACCOUNT", "SITE", "CONTAINER", "RATE", "ROUTE", "SALES DETAILS").forEach(key -> {
			if (Objects.isNull(result.get(key))){
				result.put(key, new ArrayList<>());
			} 
		});
		return result;
	}catch(Exception e) {
		logger.info("context", e);
		return result;
	  }
	} 

	private List<AobErrorDto> createErrorList(JsonNode jsonNode){
		List<AobErrorDto> result = new ArrayList<>();
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
				rowId = "";
			}
	
			result.add(new AobErrorDto(
					values.get(0), rowId, values.get(2), values.get(3)));
		}
		return result;
	}




}
