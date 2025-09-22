package com.repsrv.csweb.rest.servicerecordings;

import java.util.List;

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

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.servicerecordings.model.AddRecordingDto;
import com.repsrv.csweb.core.account.servicerecordings.service.ServiceRecordingsService;
import com.repsrv.csweb.core.codes.service.StandardizedCodesService;
import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.account.MaintainRecording;
import com.repsrv.csweb.core.model.account.ServiceRecording;
import com.repsrv.csweb.core.model.codes.StandardizedCode;
import com.repsrv.csweb.core.model.codes.StandardizedCodeType;
import com.repsrv.csweb.core.model.search.AccountDetail;
import com.repsrv.csweb.core.support.exception.recordings.AddRecordingException;
import com.repsrv.csweb.core.support.exception.recordings.ServiceInterruptionException;
import com.repsrv.csweb.rest.BaseResource;

@Component
@Scope("request")
@Path("/svc-recordings")
public class ServiceRecordingsResource extends BaseResource {

	@Autowired
	private ServiceRecordingsService serviceRecService;
	
	@GET
    @Path("/{company}/{account}/{site}/open")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getOpenServiceRecordings(@PathParam("company")String company, 
    		@PathParam("account")String account, @PathParam("site")String site) {

		List<ServiceRecording> recordings = this.serviceRecService.getOpenAccountServiceRecordings(company, account, site);
		
		return Response.ok().entity(this.serializerFactory
                .getSerializer("service-recordings-get")
                .serialize(recordings))
					.build(); 
	}
	
	@GET
    @Path("/{company}/{account}/{site}/closed")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getClosedServiceRecordings(@PathParam("company")String company, 
    		@PathParam("account")String account, @PathParam("site")String site){
		
		List<ServiceRecording> recordings = this.serviceRecService.getClosedAccountServiceRecordings(company, account, site);
		
		return Response.ok().entity(this.serializerFactory
                .getSerializer("service-recordings-get")
                .serialize(recordings))
					.build();
	}
	
	@GET
    @Path("/{company}/{account}/{site}/all")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllServiceRecordings(@PathParam("company")String company, 
    		@PathParam("account")String account, @PathParam("site")String site){
		
		List<ServiceRecording> recordings = this.serviceRecService.getAllAccountServiceRecordings(company, account, site);
		
		return Response.ok().entity(this.serializerFactory
                .getSerializer("service-recordings-get")
                .serialize(recordings))
					.build();
	}
	
	@GET
	@Path("/{company}/{account}/{site}/{serviceRecording}/{date}/maintain")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaintainServiceRecording(@PathParam("company") String company,
			@PathParam("account") String account, @PathParam("site") String site,
			@PathParam("serviceRecording") String serviceRecording, @PathParam("date") String date) throws JSONException {

	    MaintainRecording recording = this.serviceRecService.getServiceRecording(company, account, site, serviceRecording, date);
		
		if (recording == null)
			return Response.status(Status.NOT_FOUND).entity("Service Recording not found").build();
		
		return Response.ok().entity(this.serializerFactory.getSerializer("maintain-service-recordings").serialize(recording))
				.build();
	}
	
	@GET
    @Path("/table-codes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecordingTypes(@QueryParam("company")String company, 
    		@PathParam("code")String code) {
		
		StandardizedCodeType codeType = StandardizedCodeType.findByText(code);
		
		List<StandardizedCode> codes = this.serviceRecService.getRecordingTypes(company, codeType);
		
		return Response.ok().entity(this.serializerFactory
                .getSerializer("standardized-code")
                .serialize(codes))
					.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRecording(AddRecordingDto recording) throws JSONException {
		logger.debug("add recroding! "+ recording);
		JSONObject response = new JSONObject();
		
		try {
			String recordingId = this.serviceRecService.createRecording(recording);
			response.put("success", true)
				.put("id",recordingId);
			return Response.ok().entity(response).build();
			
		} catch (AddRecordingException e) {
			response.put("success", false)
			.put("msg",e.getMessage());
			return Response.status(Status.PRECONDITION_FAILED).entity(response).build();
		} catch (ServiceInterruptionException e) {
			response.put("success", false)
			.put("msg","Service Interruption active - failed to create service recording");
			return Response.status(Status.PRECONDITION_FAILED).entity(response).build();
		}
	}

	
}
