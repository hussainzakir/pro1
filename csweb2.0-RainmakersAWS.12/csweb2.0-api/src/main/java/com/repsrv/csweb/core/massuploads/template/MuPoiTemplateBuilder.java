package com.repsrv.csweb.core.massuploads.template;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repsrv.csweb.core.model.massupload.ColumnDefinition;
import com.repsrv.csweb.core.model.massupload.MuTemplate;
import com.repsrv.csweb.core.poi.PoiExcelBuilder;

public class MuPoiTemplateBuilder extends PoiExcelBuilder {
	protected final Logger logger = LoggerFactory.getLogger(MuPoiTemplateBuilder.class);

	
	private XSSFWorkbook workbook;
	private MuTemplate template;
	private OutputStream outputStream;

	public static final int INSTRUCTION_ROW = 0;
	public static final int HEADER_ROW = 1;
	public static final int TYPE_ROW = 2;
	public static final String META_SHEET_NAME = "md";
	public static final int META_COLUMN = 0;
	public static final int META_TEMPLATE_ID_ROW = 0;
	public static final int META_TEMPLATE_MOD_ROW = 1;

	private MuPoiTemplateBuilder() {}

	public MuPoiTemplateBuilder(OutputStream outputStream, MuTemplate template) {
		this.workbook = new XSSFWorkbook();
		this.template = template;
		this.outputStream = outputStream;
		initWorkbook();
	}
	
	public MuPoiTemplateBuilder(MuTemplate template) {
		this.workbook = new XSSFWorkbook();
		this.template = template;
		initWorkbook();
	}

	private void initWorkbook() {
		workbook.createSheet("data");
		XSSFSheet sheet = workbook.createSheet(META_SHEET_NAME);

		Row row_id = sheet.createRow(META_TEMPLATE_ID_ROW);
		row_id.createCell(META_COLUMN).setCellValue(template.getId());

		Row row_mod_date = sheet.createRow(META_TEMPLATE_MOD_ROW);
		row_mod_date.createCell(META_COLUMN).setCellValue(template.getChangedDate());

		// hide this sheet
		workbook.setSheetHidden(1, true);
	}

	@Override
	protected XSSFWorkbook getWorkbook() {

		return workbook;
	}

	@Override
	public XSSFWorkbook buildTemplate() {

		Sheet dataSheet = workbook.getSheetAt(0);
		CreationHelper createHelper = workbook.getCreationHelper();

		List<ColumnDefinition> columns = template.getColumnDefs();
		Row headerRow = dataSheet.createRow(HEADER_ROW);
		Row typeRow = dataSheet.createRow(TYPE_ROW);

		int size = columns.size();
		for (int g = 0; g < size; g++) {
			try {
				ColumnDefinition col = columns.get(g);
				short cellFormat = getColumnFormat(col);

				Font wf = workbook.createFont();
				wf.setFontHeightInPoints((short) 10);
				wf.setBold(true);
				wf.setColor(IndexedColors.BLUE.getIndex());

				CellStyle wcf1 = workbook.createCellStyle();
				wcf1.setWrapText(false);
				wcf1.setAlignment(HorizontalAlignment.CENTER);
				wcf1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
				wcf1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				wcf1.setBorderTop(BorderStyle.THIN);
				wcf1.setFont(wf);

				// Label label = new Label(g,HEADER_ROW ," "+ col.getDescription()+" ",wcf2);

				Cell cell = headerRow.createCell(g);
				cell.setCellValue("  " + col.getDescription() + "  ");
				cell.setCellStyle(wcf1);
				if (g == 0) {
					dataSheet.setColumnWidth(g, 256 * (col.getDescription().trim().length() + 4));
				}

				Font wf2 = workbook.createFont();
				wf2.setFontHeightInPoints((short) 8);
				wf2.setFontName("Tahoma");
				wf2.setBold(false);
				wf2.setColor(IndexedColors.BLACK.getIndex());
				wf2.setItalic(true);

				CellStyle wcf2 = workbook.createCellStyle();
				wcf2.setWrapText(false);
				wcf2.setAlignment(HorizontalAlignment.CENTER);
				wcf2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
				wcf2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				wcf2.setFont(wf2);
				wcf2.setBorderBottom(BorderStyle.THIN);
				if (cellFormat != (short) -1) {
					wcf2.setDataFormat(cellFormat);
				}

				Cell cell2 = typeRow.createCell(g);
				cell2.setCellValue(colDesc(col));
				cell2.setCellStyle(wcf2);
//					if(cellFormat == (short)-1){
//						cell2.setCellType(Cell.CELL_TYPE_STRING );
//					}
				typeRow.setHeight((short) (24 * 9));

				if (g > 0) {
					dataSheet.autoSizeColumn(g);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {

			String inst_desc = "INSTRUCTIONS\012This template is for " + template.getDescription().toUpperCase()
					+ "\012The Header rows below (including this one) are required and assumed to be present when your file is uploaded."
					+ "\012*The app will only process the worksheet entitled 'data'.  Any additional sheets in this workbook will be ignored."
					+ "\012*Do not exceed the MAX column lengths" + "\012*Do not add columns to this template"
					+ "\012*Do not change any cell formats\012" + template.getDetailDesc().replace("$BR", "\012");

			Font wf3 = workbook.createFont();
			wf3.setFontHeightInPoints((short) 10);
			wf3.setFontName("Tahoma");
			wf3.setBold(false);
			wf3.setColor(IndexedColors.BLACK.getIndex());

			CellStyle wcf2 = workbook.createCellStyle();
			wcf2.setWrapText(true);
			wcf2.setAlignment(HorizontalAlignment.LEFT);
			wcf2.setVerticalAlignment(VerticalAlignment.TOP);
			wcf2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
			wcf2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			wcf2.setFont(wf3);
			// wcf2.setBorderBottom(CellStyle.BORDER_THIN);

			Row row_inst = dataSheet.createRow(INSTRUCTION_ROW);
			Cell instCell = row_inst.createCell(0);
			instCell.setCellValue(inst_desc);

			dataSheet.addMergedRegion(new CellRangeAddress(INSTRUCTION_ROW, // first row (0-based)
					INSTRUCTION_ROW, // last row (0-based)
					0, // first column (0-based)
					size - 1 // last column (0-based)
			));
			instCell.setCellStyle(wcf2);
			// instruction row height
			row_inst.setHeight((short) ((12 + template.getDetailDesc().split("\\$BR").length) * (23 * 15)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.workbook;
	}

	private short getColumnFormat(ColumnDefinition col) throws Exception {
		// new NumberFormat("#####.###");
		DataFormat displayFormat = this.workbook.createDataFormat();
		short format;
		// CHAR,DEC,DATE
		switch (col.getColumnType()) {
		case DATE:
			format = displayFormat.getFormat("yyyy-MM-dd");
			break;
		case DEC:
			format = displayFormat.getFormat(getDecFormat(col.getLength(), col.getDecimalPlaces()));
			break;
		default:
			format = (short) -1;
			break;
		}

		return format;
	}

	/**
	 * @param pre - length (whole number lentgh)
	 * @param dp  - number of decimal places
	 * @return
	 */
	public String getDecFormat(int pre, int dp) {

		String fmt = "#";
		String dec = "";

		while (fmt.length() < pre)
			fmt = "#" + fmt;

		while (dec.length() < dp)
			dec = dec + "0";

		return (dec.length() > 0) ? (fmt + "." + dec) : fmt;

	}

	private String colDesc(ColumnDefinition col) {

		String desc = (col.getRequired() ? "* " : "");

		switch (col.getColumnType()) {
		case DATE:
			desc = desc + "Date YYYY-MM-DD";
			break;
		case DEC:
		case INT:
			desc = desc + "max " + col.getLength() + " numeric chars "
					+ ((col.getDecimalPlaces() > 0) ? 
							(col.getDecimalPlaces() + " decimal places") : (""));
			break;
		default:
			desc = desc + " max " + col.getLength() + " chars";
			break;
		}

		return desc;
	}

	@Override
	protected OutputStream getOutputStream() {
		return this.outputStream;
	}

}
