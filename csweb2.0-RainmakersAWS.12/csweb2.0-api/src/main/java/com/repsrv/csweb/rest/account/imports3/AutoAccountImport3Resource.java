package com.repsrv.csweb.rest.account.imports3;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.aob3.service.Aob3Service;
import com.repsrv.csweb.core.account.imports3.factory.AccountImportProcessor3;
import com.repsrv.csweb.core.account.imports3.service.AccountImport3Service;
import com.repsrv.csweb.core.model.account.imports.AccountImportType;
import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.onboarding.AobAccountInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketAccountInformation;
import com.repsrv.csweb.core.model.account.aob3.Aob3AccountInfo;
import com.repsrv.csweb.core.model.account.aob3.Aob3ProjectDetails;
import com.repsrv.csweb.core.model.imports3.AccountImport3Type;
import com.repsrv.csweb.core.poi.PoiUtil;
import com.repsrv.csweb.core.poi.write.RepSrvWorkbookStreamer;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.rest.BaseResource;
import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.core.header.FormDataContentDisposition;


@Component
@Scope("request")
@Path("/imports")
public class AutoAccountImport3Resource extends BaseResource {
	@Autowired
	private AccountImportProcessor3 importProcessor;

	@Autowired
	private Aob3Service aobService;

  @Autowired
	private AutoAccountImport3Factory importServiceFactory;

	@Autowired
	private RepSrvWorkbookStreamer workbookStreamer;


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
	@Path("/template/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTemplate(@PathParam("type")String type) {
		AccountImport3Type typeO = AccountImport3Type.valueOfSafe(type);
		
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
    	AccountImport3Type uType = AccountImport3Type.valueOfSafe(uploadType);

    	try {
        	AccountImport3Service<?> service = importServiceFactory.getUploadService(uType);
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
        	if ("AOB_ALL".equals(uploadType)) {
            	String response = aobService.uploadAllHistory(uploadType);
            	return Response.ok().entity(response).build();
        	} else {
            	throw new RuntimeException("Upload type invalid: " + uploadType);
        	}
    	} catch (Exception e) {
        	logger.error("Error retrieving all history for uploadType: {}", uploadType, e);
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
	}

	@GET
    @Path("/projectdetails/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectDetails(@PathParam("projectId") String projectId) {
        Aob3ProjectDetails details = aobService.getAllDetails(projectId);
        if (details == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(details).build();
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
    	logger.debug("Request to process accounts3");
    	logger.debug("Project ID: {}", projectId);

    	AccountImport3Type uType = AccountImport3Type.valueOfSafe(uploadType);
		AccountImport3Service<?> service = importServiceFactory.getUploadService(uType);
		List<?> acctsList = null;

    	try {
        	ObjectMapper mapper = new ObjectMapper();

        	switch (uType) {

            	case AOB:
					acctsList = mapper.readValue(accts, new TypeReference<List<Aob3AccountInfo>>() {});
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
}