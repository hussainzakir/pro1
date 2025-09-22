package com.repsrv.csweb.core.massuploads.service;


import com.repsrv.csweb.core.massuploads.template.MuPoiTemplateBuilder;
import com.repsrv.csweb.core.model.massupload.*;
import com.repsrv.csweb.core.service.aws.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
public class ErrorFileServiceImpl implements ErrorFileService{

    private static final int SUCCESS = 3;
    private static final int FAILURE = 2;
    private static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
    private static final String ERROR_FILE_NAME = "%s.xlsx";

    @Autowired
    private MassUploadService muService;

    @Autowired
    private S3Service s3Service;

    private static final String ERROR_FILE_FOLDER = "massupload-error-files";

    @PostConstruct
    public void init() {

    }

    /**
     * Fetches the Template for this upload, populates the error messages into the template,
     * saves the file to S3 then calls a SP to update the error file generation status
     * @param logId
     */
    @Override
    public void generateErrorFile(String logId, String chgTicket, String system) {
        log.debug("Generating error file for logId={}", logId);
        MuSystem muSystem = MuSystem.getSystem(system);
        if(muSystem == null)
            throw new RuntimeException("System not found: "+system);

        try {
            MuHistory entry = muService.getHistoryEntry(logId, muSystem);
            if(entry == null)
                throw new RuntimeException("Failed to fetch MuHistory (GETQUEUERECORD) record with id="+logId);

            log.debug("MuHistory log entry fetched: {}", entry.toString());

            MuTemplate template = muService.getTemplateForDownload(entry.getTemplateId(), chgTicket, entry.getSubmittedUser());

            SystemTypeSchema systemTypeSchema = SystemTypeSchema
                        .findByName(StringUtils.trim(template.getSystem())+"_"+entry.getEnvironment());
            if(systemTypeSchema == null)
                throw new RuntimeException("Failed to find schema for system="+template.getSystem()+" and environment="+entry.getEnvironment());

            List<ColumnDefinition> columnDefs = muService.getTemplateColumns(template.getId());

            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            MuPoiTemplateBuilder tBuilder = new MuPoiTemplateBuilder(byteOutStream, template);

            XSSFWorkbook workbook = tBuilder.buildTemplate();
            insertErrorRows(entry, template, workbook, columnDefs, systemTypeSchema, muSystem);
            tBuilder.finalizeToStream();

            log.debug("Workbook to write size {} bytes",byteOutStream.size());

            //write to s3
            String filename = getErrorFileName(entry);
            String s3Path = s3Service.writeBytesToS3(byteOutStream.toByteArray(), ERROR_FILE_FOLDER, filename);
            log.debug("Error file for logId {} written successfully to S3 location {}", logId, s3Path);

            muService.updateErrorFileGenerationStatus(logId, SUCCESS, s3Path, muSystem);

        } catch (Exception e) {
            log.error("Failed to write Excel workbook to stream", e);
            muService.updateErrorFileGenerationStatus(logId, FAILURE, "", muSystem);
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getErrorFile(MuHistory record) {
        String fileName = getErrorFileName(record);
        return s3Service.getS3File(ERROR_FILE_FOLDER+"/"+fileName);
    }

    private String getErrorFileName(MuHistory entry){
        return String.format(ERROR_FILE_NAME, entry.getLogId());
    }

    public void insertErrorRows(MuHistory entry, MuTemplate template,
                                Workbook workbook,  List<ColumnDefinition> columnDefs,
                                SystemTypeSchema systemTypeSchema, MuSystem muSystem) {
        log.debug("begin inserting the error rows into the template...");
        String schema = systemTypeSchema.getSchema();

        try {
            List<List<String>> errorData = muService.getErrorData(schema,
                    entry.getTableToUpdate(), muSystem);
            log.debug("inserting {} rows of error data into template", errorData.size());

            int rowNumber = MuPoiTemplateBuilder.TYPE_ROW + 1;
            Sheet sheet = workbook.getSheet("data");

            CreationHelper createHelper = workbook.getCreationHelper();
            Cell newCell = null;
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper
                    .createDataFormat().getFormat(
                            "yyyy-MM-dd"));

            for (List<String> row : errorData) {
                int cellNum = 0;
                Row rowInSheet = sheet.createRow(rowNumber);
                for (String cellValue : row) {
                    Cell cell = rowInSheet.createCell(cellNum);
                    if (cellValue == null || cellValue.trim().isEmpty()){
                        cellValue = "";
                    }

                    try {
                        ColumnDefinition col = columnDefs.get(cellNum);
                        if (ColumnType.DEC == col.getColumnType()
                                || ColumnType.INT == col.getColumnType()) {
                            cell.setCellValue(Double.parseDouble(cellValue));
                        } else if (ColumnType.DATE == col.getColumnType()) {
                            cell.setCellValue(sdf.parse(cellValue));
                            cell.setCellStyle(cellStyle);
                        } else {
                            cell.setCellValue(cellValue);
                        }
                    } catch (Exception e) {
                        cell.setCellValue(cellValue);
                    }
                    cellNum++;
                }
                rowNumber++;
            }

        } catch (Exception e) {
            log.error("Error inserting error rows: ", e);
            throw new RuntimeException("Error inserting error data", e);
        }
        log.debug("Completed adding the error rows");
    }
}
