package com.repsrv.csweb.core.poi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoiUtil {

	protected static final Logger logger = LoggerFactory.getLogger(PoiUtil.class);

	
	public static Workbook safeOpenUploadFile(InputStream inputStream) {

		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			try {
				workbook = new HSSFWorkbook(inputStream);
			} catch (Exception ee) {
				logger.error("Failed to create Workdbook", ee);
			}
		}

		return workbook;
	}
	
	public static Workbook safeOpenXssWorkbook(InputStream inputStream) {

		SXSSFWorkbook workbook = null;
		try {
			workbook = new SXSSFWorkbook(new XSSFWorkbook(inputStream));
		} catch (Exception e) {
			logger.error("Failed to create Workdbook", e);	
		}

		return workbook;
	}
	
	public static Cell[] getColumnCells(Sheet sheet, int columnNumber) {
		Iterator<Row> rows = sheet.iterator();
		List<Cell> cells = new ArrayList<Cell>();
		int rowNum = 0;
		while(rows.hasNext()){
			Row row = rows.next();
			Cell cell = row.getCell(columnNumber);
			if(cell instanceof Cell)
				cells.add(cell);
		}
		return (Cell[])cells.toArray(new Cell[cells.size()]);
	}

	public static Cell[] getRowCells(Row row, int size) {
		List<Cell> cellsList = new ArrayList<Cell>();
		
		int lastColumn = Math.max(row.getLastCellNum(), size);
		   for (int cn = 0; cn < lastColumn; cn++) {
		      Cell cell = row.getCell(cn, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		      if (cell == null) {
		    	  cellsList.add(null);
		      } else {
		    	  cellsList.add(cell);
		      }
		   }
		
		
		return (Cell[])cellsList.toArray(new Cell[cellsList.size()]);
	}
}
