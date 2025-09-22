package com.repsrv.csweb.rest.account.imports;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.fileupload.FileUpload;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jackson.type.TypeReference;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.imports.factory.AccountImportProcessor;
import com.repsrv.csweb.core.account.imports.service.AccountImportHistoryService;
import com.repsrv.csweb.core.account.imports.service.AccountImportService;
import com.repsrv.csweb.core.account.imports.service.ResiContractImportService;
import com.repsrv.csweb.core.account.onboarding.service.AccountOnBoardingService;
import com.repsrv.csweb.core.account.onboarding.service.AobErrorFileService;
import com.repsrv.csweb.core.account.openmarket.dao.ChangeScheduleReq;
import com.repsrv.csweb.core.account.openmarket.service.OpenMarketErrorFileService;
import com.repsrv.csweb.core.account.openmarket.service.OpenMarketService;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.ImportHistory;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.onboarding.AobAccountInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobGenerateErrorRequest;
import com.repsrv.csweb.core.model.account.openmarket.ChangeUpload;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketAccountInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketErrorRequest;
import com.repsrv.csweb.core.model.account.openmarket.ScheduleUpload;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.poi.write.RepSrvWorkbookStreamer;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.rest.BaseResource;
import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Scope("request")
@Path("/account/imports")
public class AutoAccountImportResource extends BaseResource {
	@Autowired
	private AccountImportProcessor importProcessor;
	
	@Autowired
	private AccountImportHistoryService historyService;

	@Autowired
	private AutoAccountImportFactory importServiceFactory;
	
	@Autowired
	private RepSrvWorkbookStreamer workbookStreamer;

	@Autowired
	private AobErrorFileService aobErrorFileService;

	@Autowired
	private OpenMarketErrorFileService openMarketErrorFileService;

	@Autowired
	private OpenMarketService openMarketService;

	@Autowired
	private ResiContractImportService resiService;
	
	@Autowired
	private AccountOnBoardingService aobService;
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUpload(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails,
			@FormDataParam("uploadType") String uploadtype, @FormDataParam("aquiName") String aquiName) {

		logger.debug("Received call to process Auto Account Import template id {}", uploadtype);

		AccountImportType template = AccountImportType.valueOfSafe(uploadtype);
		if (template == null)
			return Response.status(Status.NOT_FOUND).entity("Template not found").build();

		try {
			ImportValidationResult results = importProcessor.doValidation(template, uploadedInputStream);

			return Response.ok().entity(results).build();
		} catch (TemplateNotReadableException e) {
			logger.error("Failed to process upload", e);
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/templates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTemplates() throws JSONException {

		List<Map<String, String>> list = Stream.of(AccountImportType.values()).map(temp -> {
			Map<String, String> obj = new HashMap<>();
			obj.put("id", temp.name());
			obj.put("name", temp.getDescription());
			return obj;
		}).collect(Collectors.toList());

		return Response.ok().entity(list).build();

	}
	
	@GET
	@Path("/template/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTemplate(@PathParam("type")String type) {
		AccountImportType typeO = AccountImportType.valueOfSafe(type);
		
		InputStream fis = null;
		try {
			fis = getClass()
				.getClassLoader()
				.getResourceAsStream("aai-templates/"+typeO.getTemplateName());
		
		Workbook workbook = PoiUtil.safeOpenXssWorkbook(fis);
		
		return Response.ok(workbookStreamer.writeToStream(workbook), MediaType.APPLICATION_OCTET_STREAM)
                .header(FileUpload.CONTENT_DISPOSITION, "attachment;filename="+typeO.getTemplateName())
                .header("Access-Control-Expose-Headers", FileUpload.CONTENT_DISPOSITION)
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
		
		} catch(Exception e){logger.error("Failed to download import template", e);}
		finally {
			if(fis != null)
				try { fis.close(); } catch (IOException e) {}
		}
		
		return Response.status(Status.BAD_REQUEST).entity("Invalid upload type").build();

	}
	
	@GET
	@Path("/history/{uploadType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserHistory(@PathParam("uploadType") String uploadType) {
    	AccountImportType uType = AccountImportType.valueOfSafe(uploadType);

    	try {
        	AccountImportService<?> service = importServiceFactory.getUploadService(uType);
        	String response = service.uploadHistory();

        	return Response.ok().entity(response).build();
    	} catch (Exception e) {
        	logger.error("Error retrieving user history for uploadType: {}", uploadType, e);
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
	}

	@GET
	@Path("/allhistory/{uploadType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllHistory(@PathParam("uploadType") String uploadType) {
    	try {
        	if ("AOM_ALL".equals(uploadType)) {
            	String response = openMarketService.uploadAllHistory(uploadType);
            	return Response.ok().entity(response).build();
        	} else {
            	throw new RuntimeException("Upload type invalid: " + uploadType);
        	}
    	} catch (Exception e) {
        	logger.error("Error retrieving all history for uploadType: {}", uploadType, e);
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
	}
	
	@POST
	@Path("/history/errorFile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrorFile(ImportHistory item) {
		logger.info("Call to fetch error file: {}", item);

		Workbook workbook = this.historyService.fetchErrorFile(item);
		
		String fileName = item.getExcelName()+ "_errors.xlsx";
		
		return Response.ok(workbookStreamer.writeToStream(workbook), MediaType.APPLICATION_OCTET_STREAM)
                .header(FileUpload.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .header("Access-Control-Expose-Headers", FileUpload.CONTENT_DISPOSITION)
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
	}

	@POST
	@Path("/history/aoberrorfile/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAobErrorFile(AobGenerateErrorRequest aobItem) {
		logger.info("Call to fetch error file: {}", aobItem);

		Workbook workbook = this.aobErrorFileService.generateErrorFile(aobItem);
		
		String fileName = aobItem.getExcelName()+ "_errors.xlsx";
		
		return Response.ok(workbookStreamer.writeToStream(workbook), MediaType.APPLICATION_OCTET_STREAM)
                .header(FileUpload.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .header("Access-Control-Expose-Headers", FileUpload.CONTENT_DISPOSITION)
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
	}

	@POST
	@Path("/history/openmarketerrorfile/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpenMarketErrorFile(OpenMarketErrorRequest openMarketItem) {
		logger.info("Call to fetch error file: {}", openMarketItem);

		Workbook workbook = this.openMarketErrorFileService.generateErrorFile(openMarketItem);
		
		String fileName = openMarketItem.getExcelName()+ "_errors.xlsx";
		
		return Response.ok(workbookStreamer.writeToStream(workbook), MediaType.APPLICATION_OCTET_STREAM)
                .header(FileUpload.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .header("Access-Control-Expose-Headers", FileUpload.CONTENT_DISPOSITION)
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
	}

	@POST
	@Path("/process")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processAccounts(@FormParam("changeNumber") String changeNumber,
                                 @FormParam("accts") String accts,
                                 @FormParam("aquiName") String aquiName,
                                 @FormParam("fileName") String fileName,
                                 @FormParam("uploadType") String uploadType,
                                 @FormParam("projectId") String projectId) 
								 throws AccountImportSetupException, JsonParseException, JsonMappingException, IOException {
    	logger.debug("Request to process accounts");
    	logger.debug("Project ID: {}", projectId);

    	AccountImportType uType = AccountImportType.valueOfSafe(uploadType);
		AccountImportService<?> service = importServiceFactory.getUploadService(uType);
		List<?> acctsList = null;

    	try {
        	ObjectMapper mapper = new ObjectMapper();

        	switch (uType) {
            	case RCNTRT:
					acctsList = mapper.readValue(accts, new TypeReference<List<AccountInformation>>() {});
                	break;

            	case AOB:
					acctsList = mapper.readValue(accts, new TypeReference<List<AobAccountInformation>>() {});
                	break;

            	case OPENMARKET:
					acctsList = mapper.readValue(accts, new TypeReference<List<OpenMarketAccountInformation>>() {});
                	break;

            	default:
                	throw new RuntimeException("Upload type invalid: " + uType.name());
        	}

			service.persistData(acctsList, fileName, changeNumber, uploadType, aquiName, projectId);

        	return Response.ok().build();
    	} catch (Exception e) {
        	logger.error("Error processing accounts:", e);
        	return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
    	}
	} 
	
	@POST
	@Path("/history/scheduleupload")
	@Produces(MediaType.APPLICATION_JSON)
	public Response scheduleUpload(ScheduleUpload uploadData) {
    	logger.debug("Schedule Upload");
    	logger.debug("UPLOAD TYPE: {}", uploadData.getUploadType());
    	logger.debug("PROJECT ID: {}", uploadData.getProjectId());
    	logger.debug("TIME: {}", uploadData.getScheduleTime());
    	logger.debug("DATE : {}", uploadData.getScheduleDate());

        	AccountImportType uType = AccountImportType.valueOfSafe(uploadData.getUploadType());
        	AccountImportService<?> service = importServiceFactory.getUploadService(uType);
        	service.scheduleUpload(uploadData.getProjectId(), uploadData.getScheduleDate(), uploadData.getScheduleTime());

        	return Response.ok().build();
	}

	@POST
	@Path("/history/aomchangescheduleupload")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeScheduleUpload(ChangeUpload uploadData) {
    	AccountImportType uType = AccountImportType.valueOfSafe(uploadData.getUploadType());
    	logger.debug("Utype: {}", uType);

        	AccountImportService<?> service = importServiceFactory.getUploadService(uType);

        	ChangeScheduleReq req = (ChangeScheduleReq) service.changeScheduleUpload(
            	uploadData.getProjectId(),
            	uploadData.getScheduleDate(),
            	uploadData.getScheduleTime(),
            	uploadData.getAction()
        	);

        	return Response.ok(req).build();
	}
	
}

