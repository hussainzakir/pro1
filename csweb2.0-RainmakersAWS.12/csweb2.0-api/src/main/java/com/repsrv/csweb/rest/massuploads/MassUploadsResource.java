package com.repsrv.csweb.rest.massuploads;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.repsrv.csweb.core.massuploads.service.ErrorFileService;
import com.repsrv.csweb.core.model.massupload.MuHistory;
import com.repsrv.csweb.core.service.aws.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.massuploads.service.MassUploadService;
import com.repsrv.csweb.core.massuploads.template.MuPoiTemplateBuilder;
import com.repsrv.csweb.core.model.json.BaseJsonResponse;
import com.repsrv.csweb.core.model.massupload.MuTemplate;
import com.repsrv.csweb.core.support.exception.StoredProcedureException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateValidationException;
import com.repsrv.csweb.rest.BaseResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Component
@Scope("request")
@Path("/massuploads")
public class MassUploadsResource extends BaseResource{

	protected final Logger logger = LoggerFactory.getLogger(MassUploadsResource.class);
	
	@Autowired
	private MassUploadService massuploadService;

	@Autowired
	private ErrorFileService errorFileSvc;

	@Autowired
	private S3Service s3Service;
	
	@GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUploadsHistory(){
       
        return Response.ok().entity(this.serializerFactory
                .getSerializer("get-uploads-history")
                .serialize(this.massuploadService.getHistory()))
                .build();
    }
	
	@GET
    @Path("/templates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplates() {

        return Response.ok().entity(this.serializerFactory
                .getSerializer("get-templates-dto")
                .serialize(this.massuploadService.getTemplates()))
                .build();
    }
	
	@GET
    @Path("/template/download/{templateId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getTemplateForDownload(@PathParam("templateId")String templateId, @QueryParam("changeNumber") String changeNumber) throws Exception{

		MuTemplate template = this.massuploadService.getTemplate(templateId,changeNumber);
      
		logger.info("serving a template");
		MuPoiTemplateBuilder tBuilder = new MuPoiTemplateBuilder(template);
		tBuilder.buildTemplate();
		
		String fileName = template.getDescription().replace(" ","_").replace(",","_")+".xlsx";
		
		return Response.ok(tBuilder.finalizeToNewStream(), MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment;filename="+fileName)
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
    }

	@GET
	@Path("/error-file/generate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response genertateErrorFile(
			@QueryParam("logId")String logId,
			@QueryParam("chgticket")String chgTicket,
			@QueryParam("system")String system) throws Exception{
		Map<String, String> res = new HashMap<>();

		log.debug("Error file generation request received for logId={}, chgticket={}, system={}",logId, chgTicket, system);
		try {
			errorFileSvc.generateErrorFile(logId, chgTicket, system);
			res.put("status", "success");
		}catch(Exception e){
			res.put("status","failed");
			res.put("error",e.getMessage());
			log.error("Unkown error generating error file", e);
		}

		return Response.ok().entity(res)
				.build();
	}

	@GET
    @Path("/error-file/download-orig")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getErrorFileForDownloadOrig(
    		@QueryParam("errorFile")String errorFile,
    		@QueryParam("userFileName")String userFileName) throws Exception{

		byte [] fileData = this.massuploadService.getErrorFile(errorFile);

		String fileName = StringUtils.isBlank(userFileName) 
				? "MassUpload_errors.xlsx" : userFileName.replace(".xlsx", "_errors.xlsx");
		
		return Response.ok(fileData, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment;filename="+fileName)
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();
    }

	@GET
	@Path("/error-file/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getErrorFileForDownload(
			@QueryParam("logId")String logId) throws Exception{

		MuHistory record = massuploadService.getHistoryEntry(logId, null);
		log.debug("Going fetch error file for download: {}", record.toString());
		String fileName = StringUtils.isBlank(record.getSubmittedFile())
			? "MassUpload_errors.xlsx" : record.getSubmittedFile().replace(".xlsx", "_errors.xlsx");

		byte [] fileData = errorFileSvc.getErrorFile(record);//s3Service.getS3File(record.getErrorFilePath());

		return Response.ok(fileData, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment;filename="+fileName)
				.header("Access-Control-Expose-Headers", "Content-Disposition")
				.type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
				.build();
	}
	
	@GET
    @Path("/template/{templateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplate(@PathParam("templateId")String templateId, @QueryParam("changeNumber") String changeNumber){
		return Response.ok().entity(this.serializerFactory
                .getSerializer("get-template")
                .serialize(massuploadService.getTemplate(templateId, changeNumber)))
                .build();
    }
	
	@GET
    @Path("/template/{templateId}/columns")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplateColumns(@PathParam("templateId")String templateId){
		return Response.ok().entity(this.serializerFactory
                .getSerializer("get-template-columns")
                .serialize(massuploadService.getTemplateColumns(templateId)))
                .build();
		
    }
	
	@POST
    @Path("/template/{templateId}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public Response saveTemplateUpload(@PathParam("templateId")String templateId,
    		@FormDataParam("file") InputStream uploadedInputStream,
    	    @FormDataParam("file") FormDataContentDisposition fileDetails,
    	    @FormDataParam("changeNumber")String changeNumber) throws JSONException{

		logger.debug("Uploading file {} and changeNumber {}", fileDetails.getFileName(), changeNumber);
		
    MuTemplate template = this.massuploadService.getTemplate(templateId, changeNumber);
		if(template == null)
			return Response.status(Status.NOT_FOUND).entity("Template not found").build();
		
		try {
			this.massuploadService.processMassUpload(template, uploadedInputStream, changeNumber, fileDetails.getFileName());
		} catch (TemplateNotReadableException | TemplateValidationException | StoredProcedureException e) {
			BaseJsonResponse response = new BaseJsonResponse();
			response.setErrorMessage(e.getMessage());
			response.setErrors(e instanceof TemplateValidationException ? 
					((TemplateValidationException)e).getErrors() : null);
			
			
			return Response.status(Status.BAD_REQUEST).entity(this.serializerFactory
                .getSerializer("upload-template-error")
                .serialize(response))
					.build(); 
		}
		JSONObject res = new JSONObject();
		res.put("success", true);
		
		return Response.ok().entity(res.toString()).build();
		
    }
}
