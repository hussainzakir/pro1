package com.repsrv.csweb.core.massuploads.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.repsrv.csweb.core.model.massupload.*;
import com.repsrv.csweb.core.mybatis.type.custom.handlers.MassUploadErrorDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.massuploads.dao.MassUploadsCbsDao;
import com.repsrv.csweb.core.massuploads.dao.MassUploadsDao;
import com.repsrv.csweb.core.massuploads.template.MuTemplateUploadValidator;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.core.support.exception.StoredProcedureException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateValidationException;
import com.repsrv.csweb.core.util.StringTrimModule;

@Slf4j
@Service("massuploadService")
public class MassUploadServiceImpl extends JsonResultService implements MassUploadService{
	private final String publicErrorDirPath;

	private static final String DOWNLOAD_TEMPLATE = "DT";

	public MassUploadServiceImpl (){
		this.publicErrorDirPath = errorPath();
	}

	protected final Logger logger = LoggerFactory.getLogger(MassUploadServiceImpl.class);
	
	@Autowired
	private MassUploadsDao massuploadDao;

	@Autowired
	private MassUploadsCbsDao massUploadsCbsDao;

	
	@Override
	public GetTemplatesDto getTemplates() {
		String username = SecurityUtils.getLoggedInUser();
		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.massuploadDao.getTemplates(username, getSystem(), result);

		TypeReference<List<MuTemplate>> tref = new TypeReference<List<MuTemplate>>() {};
		List<MuTemplate> results = toObject(jsonString, tref);

		GetTemplatesDto templatesDto = new GetTemplatesDto();
		templatesDto.setTemplates(results);
		templatesDto.setChangeReq(result.getChangeRequired());

		return templatesDto;
	}


	@Override
	public List<MuHistory> getHistory() {
		StoredProcCallResult result = new StoredProcCallResult();
		String username = SecurityUtils.getLoggedInUser();
		
		List<MuHistory> infoProResults = this.massuploadDao.getHistory(username, result);
		List<MuHistory> cbsProResults = this.massUploadsCbsDao.getHistory(username, result);
		
		int size = infoProResults == null ? 0 : infoProResults.size()
			+ (cbsProResults == null ? 0 : cbsProResults.size());
		
		List<MuHistory> results = new ArrayList<>(size);
		if(infoProResults != null)
			results.addAll(infoProResults);
		
		if(cbsProResults != null)
			results.addAll(cbsProResults);
		
		Collections.sort(results);
		Collections.reverse(results);
		return results;
	}

	@Override
	public List<ColumnDefinition> getTemplateColumns(String templateId) {
		StoredProcCallResult result = new StoredProcCallResult();
		
		List<ColumnDefinition> results = this.massuploadDao.getTemplateColumns(templateId, result);
		
		return results;
	}

	@Override
	public MuTemplate getTemplate(String templateId, String changeNumber) {
		String username = SecurityUtils.getLoggedInUser();

		return getTemplateForProcessing(templateId, changeNumber, " ", username);
	}

	private MuTemplate getTemplateForProcessing(String templateId, String changeNumber, String downloadTemplate, String username){
		StoredProcCallResult result = new StoredProcCallResult();

		String effChangeNumber = (changeNumber == null ) ? "" : changeNumber;	
 		String jsonString = this.massuploadDao.getTemplate(templateId, effChangeNumber, username, downloadTemplate, result);
		
 		if(StringUtils.isNotBlank(result.getErrorMessage())) {
 			logger.error("store proc error : " + result.getErrorMessage());
 			throw new StoredProcedureException(result.getErrorMessage());
 		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new StringTrimModule());
		try {
			return mapper.readValue(jsonString, MuTemplate.class);
		} catch (IOException e) {
			log.error("Failed to read JSON response from SP", e);
			return null;
		}
		//return getJsonDataObject(results, MuTemplate.class);
	}

	@Override
	public MuTemplate getTemplateForDownload(String templateId, String changeNumber, String username) {
		return getTemplateForProcessing(templateId, changeNumber, DOWNLOAD_TEMPLATE, username);
	}


//	@Override
//	public MuTemplate getTemplateRS(String templateId) {
//		StoredProcCallResult result = new StoredProcCallResult();
//		MuTemplate template = this.massuploadDao.getTemplateRS(templateId, result);
//		
//		template.setColumnDefs(this.getTemplateColumns(templateId));
//		
//		return template;
//	}

	@Override
	public boolean processMassUpload(MuTemplate template, 
			InputStream uploadInputStream, 
			String changeNumber, String userFileName) throws TemplateNotReadableException, TemplateValidationException, StoredProcedureException {
		
		Workbook workbook = PoiUtil.safeOpenUploadFile(uploadInputStream);
		if(workbook == null)
			throw new TemplateNotReadableException("Template is not readable");

		MuSystem system = MuSystem.getSystem(template.getSystem());
		if(system == null)
			throw new StoredProcedureException("Template system value ["+template.getSystem()+"] not valid");
			
		MuTemplateUploadValidator validator = new MuTemplateUploadValidator(workbook, template);
		int errorCount = validator.validateWorkbook();
		
		if(errorCount > 0)
			throw new TemplateValidationException("Template validation failed.",validator.getErrors().values(), template);
		
		List<List<ColumnValue>> rowValues = validator.getRowValues();
		JSONObject object = new JSONObject();
		try {
			object.put("submittedUser", SecurityUtils.getLoggedInUser())
			.put("userFileName", userFileName)
			.put("templateId", template.getId())
			.put("changeNumber", StringUtils.isBlank(changeNumber) ? "" : changeNumber)
			.put("importValues", convertToJsonObjList(rowValues));
			logger.info("JSON for save: {}", object.toString(2) );
		} catch (JSONException e) {
			logger.error("Failed to generate JSON",e);
		}

		StoredProcCallResult result = new StoredProcCallResult();
		try {
			String jsonPayload = object.toString();
			if(!system.isCbs())
				this.massuploadDao.saveUpload(Integer.parseInt(template.getId()), jsonPayload, result);
			else
				this.massUploadsCbsDao.saveUpload(Integer.parseInt(template.getId()), jsonPayload, result);
		} catch(Exception e) {
			throw new StoredProcedureException(StringUtils.substring(e.getMessage(), 0, 200));
		}
		
		if(StringUtils.isNotBlank(result.getSqlState()))
			throw new StoredProcedureException(result.getErrorMessage());
		
		return true;
	}

	/**
	 * LEGACY - to be removed
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@Override
	public byte[] getErrorFile(String path) throws IOException {
		if (!path.contains(publicErrorDirPath)){
			throw new IOException("Entry is outside of the target directory");
		}
		Path path_ = Paths.get(path);

	      byte[] data = Files.readAllBytes(path_);
		return data;
	}

	@Override
	public MuHistory getHistoryEntry(String logId, MuSystem system) {
		logger.info("Getting MuHistory for logId:{} and system:{}", logId, system==null?"null":system.name());

		if(system == null)
			return getHistoryEntry(logId);

		StoredProcCallResult result = new StoredProcCallResult();
		if(system.isCbs()) {
			logger.info("MuSystem is CBS");
			return this.massUploadsCbsDao.getHistoryEntry(logId, result);
		}
		else {
			logger.info("MuSystem is InfoPro");
			return this.massuploadDao.getHistoryEntry(logId, result);//infopro
		}
	}

	private MuHistory getHistoryEntry(String logId){
		StoredProcCallResult result = new StoredProcCallResult();
		MuHistory item = this.massuploadDao.getHistoryEntry(logId, result);

		if(item == null)
			return this.massUploadsCbsDao.getHistoryEntry(logId, result);
		else
			return item;
	}

	@Override
	public List<List<String>> getErrorData(String schema, String table, MuSystem muSystem) {
		log.debug("Fetching error List<List<String>>...");
		MassUploadErrorDataHandler handler = new MassUploadErrorDataHandler();

		if(muSystem.isCbs()){
			massUploadsCbsDao.getErrorData(schema, table, handler);
		} else{
			massuploadDao.getErrorData(schema, table, handler);
		}

		List<List<String>> rows = handler.getResultList();
		log.debug("resultset ingested {} rows of errors", rows.size());

		return rows;

	}

	@Override
	public void updateErrorFileGenerationStatus(String logId, int status, String s3Path, MuSystem muSystem) {
		StoredProcCallResult result = new StoredProcCallResult();

		if(muSystem.isCbs()){
			massUploadsCbsDao.updateQueueRecord(logId, status, s3Path, result);
		} else{
			massuploadDao.updateQueueRecord(logId, status, s3Path, result);
		}

		log.debug("Updated log status - {} to musystem: {}",  StringUtils.isBlank(result.getErrorMessage()), muSystem.name());
	}

	private List<JSONArray> convertToJsonObjList(List<List<ColumnValue>> rowValues) throws JSONException {
		if(rowValues != null) {
			List<JSONArray> result = new ArrayList<>();
			for(List<ColumnValue> row : rowValues) {
				JSONArray array = new JSONArray();
				for(ColumnValue val : row) {
					array.put(val.value.trim());
				}
				result.add(array);
			}
			return result;
		}
		return null;
	}
	private String errorPath(){
		try {
			Properties props = new Properties ();
			InputStream stream = loadProperties();
			props.load(stream);
			String path = props.getProperty("PATH");
			if (path == null){
				path = "";
			}
			 return path;
		} catch(IOException e) {
			logger.warn(e.getMessage());
			return "";
		}
	}

	private InputStream loadProperties () {
		return  MassUploadServiceImpl.class.getClassLoader().getResourceAsStream("errorfile.properties");
	}
}
