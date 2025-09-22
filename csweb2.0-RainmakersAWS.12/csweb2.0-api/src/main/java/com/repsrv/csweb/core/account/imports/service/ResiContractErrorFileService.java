package com.repsrv.csweb.core.account.imports.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poiji.option.PoijiOptions;
import com.poiji.save.MappedFields;
import com.repsrv.csweb.core.account.imports.dao.AaiErrorFileDao;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.ContainerInformation;
import com.repsrv.csweb.core.model.account.imports.ImportHistory;
import com.repsrv.csweb.core.model.account.imports.ImportMetaData;
import com.repsrv.csweb.core.model.account.imports.RateInformation;
import com.repsrv.csweb.core.model.account.imports.RouteInformation;
import com.repsrv.csweb.core.model.account.imports.SiteInformation;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;

@Service
public class ResiContractErrorFileService extends JsonResultService implements ErrorFileService, ExcellSheetsConstants{

	@Autowired 
	protected AaiErrorFileDao errorFileDao;
	
	@Override
	public Workbook generateErrorFile(ImportHistory item) {

		List<AccountInformation> accts = getRows(item, item.getAccountTab(), new TypeToken<ArrayList<AccountInformation>>(){});
		List<SiteInformation> sites = getRows(item, item.getSiteTab(), new TypeToken<ArrayList<SiteInformation>>(){});
		List<ContainerInformation> containers = getRows(item, item.getContainerTab(), new TypeToken<ArrayList<ContainerInformation>>(){});
		List<RateInformation> rates = getRows(item, item.getRateTab(), new TypeToken<ArrayList<RateInformation>>(){});
		List<RouteInformation> routes = getRows(item, item.getRouteTab(), new TypeToken<ArrayList<RouteInformation>>(){});

		logger.debug("Account rows {}", accts.size());
		logger.debug("Sites rows {}", sites.size());
		logger.debug("Containers rows {}", containers.size());
		logger.debug("Rates rows {}", rates.size());
		logger.debug("Routes rows {}", routes.size());
		
		InputStream fis = null;
		try {
			fis = getClass().getClassLoader().getResourceAsStream("aai-templates/"+AccountImportType.RCNTRT.getTemplateName());
			
			Workbook workbook = PoiUtil.safeOpenXssWorkbook(fis);
			if(workbook == null)
				throw new TemplateNotReadableException("Template is not readable");
			 
			writeData(accts, ACCOUNT_SHEET_NAME, workbook, AccountInformation.class);
			writeData(sites, SITE_SHEET_NAME, workbook, SiteInformation.class);
			writeData(containers, CONTAINER_SHEET_NAME, workbook, ContainerInformation.class);
			writeData(rates, RATE_SHEET_NAME, workbook, RateInformation.class);
			writeData(routes, ROUTE_SHEET_NAME, workbook, RouteInformation.class);
			
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
				.headerCount(5)
				.headerStart(0)
				.sheetName(sheetName)
				.build();

		
		final MappedFields mappedFields = new MappedFields(clazz, opts).parseEntity();
		int rowIndex = 5;
		
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


	private <T> List<T> getRows(ImportHistory item, String table, TypeToken<ArrayList<T>> typeReference){
		StoredProcCallResult result = new StoredProcCallResult();
		ImportMetaData metadata = ImportMetaData.builder()
		.excel(item.getExcelName())
		.library(item.getLibrary())
		.userid(item.getUserId())
		.table(table)
		.build();
		
		String json = getJsonString(metadata);
		String rowsString = this.errorFileDao.getTableRows(json, result);
		logger.info("Fetch rows result {}", result.getErrorMessage());
		if (rowsString == null){
			return new ArrayList<>();
		} else {
		return new Gson().fromJson(rowsString, typeReference.getType());
		}
	}
}
