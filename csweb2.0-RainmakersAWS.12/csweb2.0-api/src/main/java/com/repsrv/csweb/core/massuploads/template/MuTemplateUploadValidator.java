package com.repsrv.csweb.core.massuploads.template;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repsrv.csweb.core.model.massupload.ColumnDefinition;
import com.repsrv.csweb.core.model.massupload.ColumnType;
import com.repsrv.csweb.core.model.massupload.ColumnValue;
import com.repsrv.csweb.core.model.massupload.MuTemplate;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.util.MessagesUtil;
import com.repsrv.csweb.core.util.TimeUtil;

public class MuTemplateUploadValidator {
	protected final Logger logger = LoggerFactory.getLogger(MuTemplateUploadValidator.class);

	private Workbook workbook;
	private MuTemplate template;
	private Map<String, RowErrorLog> rowErrors;
	private int errorCount = 0;
	private int rowCount = 0;
	private int rowErrorCount = 0;
	private DataFormatter dataFormatter;
	private List<List<ColumnValue>> rowValues = new ArrayList<>();

	public MuTemplateUploadValidator(Workbook workbook, MuTemplate template) {
		this.template = template;
		this.workbook = workbook;
		this.rowErrors = new LinkedHashMap<>();
		dataFormatter = new DataFormatter();
	}

	public int validateWorkbook() {
		logger.debug("validating template {}", this.template.getId());
		boolean hasData = false;
		Sheet sheet = workbook.getSheet("data");
		Sheet metaSheet = workbook.getSheet(MuPoiTemplateBuilder.META_SHEET_NAME);

		if (sheet == null || (metaSheet == null)) {
			RowErrorLog rel = new RowErrorLog();
			rel.setRowNumber(-1);
			rel.addColumnError(MessagesUtil.getMessage("MSGID800"));

			rowErrors.put("-1", rel);

			return 1;
		}
		locateEmptyCell(sheet);
		try {
			String metaID = dataFormatter.formatCellValue(metaSheet.getRow(MuPoiTemplateBuilder.META_TEMPLATE_ID_ROW)
					.getCell(MuPoiTemplateBuilder.META_COLUMN));
			String metaModDate = dataFormatter.formatCellValue(metaSheet
					.getRow(MuPoiTemplateBuilder.META_TEMPLATE_MOD_ROW).getCell(MuPoiTemplateBuilder.META_COLUMN));

			if (!template.getId().equalsIgnoreCase(metaID)) {
				RowErrorLog rel = new RowErrorLog();
				rel.setRowNumber(-1);
				rel.addColumnError(MessagesUtil.getMessage("MSGID801", new String[] { template.getDescription() }));
				rowErrors.put("-1", rel);

				return 1;
			}

		String metaModDateFormatted = metaModDate;
		if (metaModDate.matches("\\d*")){
			 metaModDateFormatted = TimeUtil.formatDateToAs400timeStamp(metaModDate);
		}

			if (!(template.getChangedDate().equalsIgnoreCase(metaModDateFormatted))) {
				RowErrorLog rel = new RowErrorLog();
				rel.setRowNumber(-1);
				rel.addColumnError(MessagesUtil.getMessage("MSGID802"));
				rowErrors.put("-1", rel);

				return 1;
			}
		} catch (Exception e) {
			RowErrorLog rel = new RowErrorLog();
			rel.setRowNumber(-1);
			rel.addColumnError(MessagesUtil.getMessage("MSGID800"));
			rowErrors.put("-1", rel);

			return 1;
		}

		rowCount = sheet.getLastRowNum() + 1;
		int columCount = sheet.getRow(1).getPhysicalNumberOfCells();

		int templateColCount = template.getColumnDefs().size();

		// Simple check - total column count should match template

		if (columCount < templateColCount) {
			RowErrorLog rel = new RowErrorLog();
			rel.setRowNumber(-1);
			rel.addColumnError(MessagesUtil.getMessage("MSGID803",
					new String[] { String.valueOf(templateColCount), String.valueOf(columCount) }));

			rowErrors.put("-1", rel);

			return 1;
		}

		if (rowCount <= 3) {
			logger.debug("Template doesn't have any rows: {}", MessagesUtil.getMessage("MSGID804"));
			RowErrorLog rel = new RowErrorLog();
			rel.setRowNumber(-1);
			rel.addColumnError(MessagesUtil.getMessage("MSGID804"));

			rowErrors.put("-1", rel);

			return 1;
		}

		List<ColumnDefinition> templateFields = template.getColumnDefs();
		// Past prelimiary checks. Lets get to the meat and potatos!
		// loop though the columns and test the datatype against each row
		for (int g = 0; g < templateColCount; g++) {

			ColumnDefinition columnDef = templateFields.get(g);
			Cell[] colCells = PoiUtil.getColumnCells(sheet, g);// sheet.getColumn(g);
			// String dataType = columnDef.getDataType();

			int dataLength = columnDef.getLength();
			boolean required = columnDef.getRequired();

			for (int h = MuPoiTemplateBuilder.TYPE_ROW + 1; h < rowCount; h++) {
				Cell cell = null;
				logger.debug("IS row empty? rowNum={}", h);
				if (emptyRow(h, sheet.getRow(h)))
					break;

				//initRowLog(h);
				hasData = true;

				try {
					cell = colCells[h];
				} catch (ArrayIndexOutOfBoundsException aioe) {
					if (required)
						logError(h, columnDef, columnDef.getDescription() + " is required");

					continue;
				}

				String cellContents = dataFormatter.formatCellValue(cell);
				if (required && (cellContents == null || cellContents.trim().length() < 1)) {
					// error - data required but not provided!
					logError(h, columnDef, columnDef.getDescription() + " is required");
					continue;
				}

				if (cellContents.trim().length() > dataLength && ColumnType.DATE != columnDef.getColumnType()
						&& ColumnType.DEC != columnDef.getColumnType()) {
					// error - data length bad!
					logError(h, columnDef, columnDef.getDescription() + " must be no more than " + columnDef.getLength()
							+ " characters in length");
					continue;
				}

				// add to value array
				int rowIndex = h - (MuPoiTemplateBuilder.TYPE_ROW + 1);
				List<ColumnValue> rowList = rowIndex > (rowValues.size() - 1) ? null : rowValues.get(rowIndex);
				if ((CellType.BLANK == cell.getCellType() || (cellContents == null))
						&& !required) {
					rowList.add(new ColumnValue(columnDef.getTableFieldName(), " "));
					continue;
				}
				if (rowList == null) {
					rowList = new ArrayList<>();
					rowValues.add(rowList);
				}

				if ((CellType.BLANK == cell.getCellType() || (cellContents == null || cellContents.trim().length() < 1))
						&& !required) {
					rowList.add(new ColumnValue(columnDef.getTableFieldName(), ""));
					continue;
				}

				rowList.add(new ColumnValue(columnDef.getTableFieldName(), cellContents));

				if (ColumnType.DATE == columnDef.getColumnType()) {
					try {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						formatter.setLenient(false);
						formatter.parse(cellContents);
					} catch (ParseException pe) {
						logError(h, columnDef, columnDef.getDescription()
								+ " date format incorrect.  Should be yyyy-MM-dd with valid month/day values.");
						continue;
					}
				} else if (ColumnType.DEC == columnDef.getColumnType() || ColumnType.INT == columnDef.getColumnType()) {
					try {
						Double.parseDouble(cellContents);// first make sure its all numeric
					} catch (NumberFormatException nfe) {
						logError(h, columnDef, columnDef.getDescription() + " must be numeric only.");
						continue;
					}

					int sizeOffset = (ColumnType.DEC == columnDef.getColumnType()) ? 1 : 0;

					int dec = cellContents.trim().indexOf(".");
					if (dec < 0 && ColumnType.DEC == columnDef.getColumnType()) {
						// check to make sure whole number length isn't greater than max-2 (as400)
						if ((cellContents.trim().length()) > (columnDef.getLength())) {
							logger.debug("UPLOAD ERROR: sizeOffset={}, colType={}, columnDef.getLength()={}",
									sizeOffset, columnDef.getColumnType(), columnDef.getLength());
							logError(h, columnDef, columnDef.getDescription() + " value exceeds limit.");
							continue;
						}
					} else if (cellContents.trim().length() > (columnDef.getLength() + sizeOffset)) {
						logger.debug("UPLOAD ERROR: sizeOffset={}, colType={}", sizeOffset, columnDef.getColumnType());

						logError(h, columnDef, columnDef.getDescription() + " value exceeds limit.");
						continue;
					}
				}
			}
		}

		if (!hasData) {
			RowErrorLog rel = new RowErrorLog();
			rel.setRowNumber(-1);
			rel.addColumnError(MessagesUtil.getMessage("MSGID804"));
			rowErrors.put("-1", rel);
		}
		return errorCount;
	}

	Map emptyRows = new HashMap();

	private boolean emptyRow(int row, Row sheetRow) {
		if (sheetRow == null)
			return true;
		Iterator<Cell> cells = sheetRow.cellIterator();
		Boolean rowFlag = (Boolean) emptyRows.get(String.valueOf(row));

		if (rowFlag == null) {
			boolean empty = true;
			while (cells.hasNext()) {
				Cell cell = cells.next();
				String cellContents = dataFormatter.formatCellValue(cell);
				if (cellContents == null || cellContents.trim().length() < 1)
					continue;
				else {
					empty = false;
					break;
				}
			}
			emptyRows.put(String.valueOf(row), empty);
			return empty;
		}

		return rowFlag.booleanValue();
	}

	public Sheet getSheet() {
		return workbook.getSheet("data");
	}

	private void initRowLog(int row) {

		RowErrorLog rel = null;
		if (rowErrors.get(String.valueOf(row)) != null)
			return;

		rel = new RowErrorLog();
		rel.setRowNumber(row + 1);
		rowErrors.put(String.valueOf(row), rel);
	}
	
	private RowErrorLog getOrCreateRowLog(int row) {
		RowErrorLog rel = rowErrors.get(String.valueOf(row));
		
		if(rel == null) {
			rel = new RowErrorLog();
			rel.setRowNumber(row + 1);
			rowErrors.put(String.valueOf(row), rel);
		}
		
		return rel;
	}

	private void logError(int row, ColumnDefinition columnDef, String msg) {
		errorCount++;
		RowErrorLog rel = getOrCreateRowLog(row);

		if (rel.getErrorCount() < 1)// first time then mark as row error
			rowErrorCount++;

		rel.incrementErrorCount();
		rel.addColumnError(msg);

		rowErrors.put(String.valueOf(row), rel);
	}

	public Map<String, RowErrorLog> getErrors() {
		return rowErrors;
	}

	public int getRowCount() {
		return ((this.rowCount - 3) < 0) ? 0 : (this.rowCount - 3);
	}

	public int getNumRowErros() {
		return rowErrorCount;
	}

	public List<List<ColumnValue>> getRowValues() {
		return this.rowValues;
	}

	public void locateEmptyCell(Sheet sheet){

		Map<Integer,List<Integer>> rows = new HashMap<>();
	
		Iterator<Row> rowIterator = sheet.rowIterator();
	
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
	
			List<Integer> row1 = new ArrayList<Integer>();
			int rowNum = row.getRowNum();
	
			rows.put(rowNum, row1);
	
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
	
				row1.add(cell.getAddress().getColumn());
			}
		}
	
		rows.entrySet().stream().forEach(entrySet -> {
			IntStream references = IntStream.range(0, template.getColumnDefs().size()); // 0,1,2,3,4
	
			references.forEach(ref -> {
				boolean existColumn = entrySet.getValue().stream().anyMatch( colNumber -> colNumber == ref );
	
				if (!existColumn){
					Row currentRow = sheet.getRow(entrySet.getKey());
					Cell currentCell = currentRow.createCell(ref, CellType.STRING);
					currentCell.setCellValue(" ");
				}
			});
	
		});
	
	}


}