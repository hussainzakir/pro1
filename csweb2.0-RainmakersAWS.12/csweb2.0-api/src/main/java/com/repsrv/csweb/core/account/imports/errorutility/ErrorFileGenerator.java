package com.repsrv.csweb.core.account.imports.errorutility;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poiji.option.PoijiOptions;
import com.poiji.save.MappedFields;

public class ErrorFileGenerator {
 
    private ErrorFileGenerator (){}
    
 protected static final Logger logger = LoggerFactory.getLogger(ErrorFileGenerator.class);

    public static <T> void writeData(List<T> data, String sheetName, Workbook workbook, Class<?> clazz) {
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

    
	public static <T> void setValuesFromKnownFields(final org.apache.poi.ss.usermodel.Row row, 
			final T instance, MappedFields mappedFields) throws IllegalAccessException {
         for (Map.Entry<Field, Integer> orders : mappedFields.getOrders().entrySet()) {
            final Cell cell = row.createCell(orders.getValue());
            final Field field = orders.getKey();
            
            cell.setCellValue(String.valueOf(field.get(instance)));
        }
    }


}
