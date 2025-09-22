package com.repsrv.csweb.rest.aae;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.repsrv.csweb.core.model.aae.*;
import com.repsrv.csweb.core.model.listData.ListDataResponse;
import com.repsrv.csweb.core.support.exception.SecurityUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.aae.model.ListDataEntityType;
import com.repsrv.csweb.core.aae.model.QuoteAssigneeRequest;
import com.repsrv.csweb.core.aae.model.QuoteMetadata;
import com.repsrv.csweb.core.aae.service.AutomatedAccountEntryService;
import com.repsrv.csweb.core.aae.service.AutomatedAccountEntryServiceImpl;
import com.repsrv.csweb.rest.BaseResource;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Scope("request")
@Path("/aae/quote")
public class AaeEntryController extends BaseResource {
	
	@Autowired
	private AutomatedAccountEntryService aaeService;
	
	@Autowired
	private AutomatedAccountEntryServiceImpl aaeServiceImpl;
	
	@GET
	@Path("/{quoteid}/div/{division}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountDetail(@PathParam("division") String division, @PathParam("quoteid") String quoteId){
		
		Account accountDetails = this.aaeService.getAccountDetail(StringUtils.leftPad(division, 5), quoteId);
		
		logger.debug("Details for this account {}",
			this.serializerFactory.getSerializer("aae-detail").serialize(accountDetails));
		if(accountDetails == null)
			return Response.status(Status.NOT_FOUND).entity("Account not found").build();
		
		return Response.ok().entity(this.serializerFactory.getSerializer("aae-detail").serialize(accountDetails)).build();
	}
	
	@GET
	@Path("/{quoteid}/errors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrorResponse(@PathParam("quoteid") String quoteId) {
		
		List<ErrorResponse> errorDetails = this.aaeService.getErrorResponse(quoteId);
		
		logger.debug("Errors for this account {}",
			this.serializerFactory.getSerializer("error-response").serialize(errorDetails));
//		if(errorDetails == null)
//			return Response.status(Status.NOT_FOUND).entity("Errors not found").build();
		
		return Response.ok().entity(this.serializerFactory.getSerializer("error-response").serialize(errorDetails)).build();
	}
	
	@POST
	@Path("/accountdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountInfoUpdate(
			AccountEdit accountEdit) {
		
		this.aaeServiceImpl.updateAccountInformation(accountEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/sitedetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response siteInfoUpdate(
			SiteEdit siteEdit) {
	
		this.aaeServiceImpl.updateSiteInformation(siteEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/containerdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response containerInfoUpdate(
			ContainerEdit containerEdit) {
			
		this.aaeServiceImpl.updateContainerInformation(containerEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/ratedetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rateInfoUpdate(
			RateEdit rateEdit) {
			
		this.aaeServiceImpl.updateRateInformation(rateEdit);
		
		return Response.ok().build();
	}

	@POST
	@Path("/finalization")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FinalizationSubmit(FinalizationParams finalizationParams) {

	this.aaeServiceImpl.submitFinalizationInformation(finalizationParams);

	return Response.ok().build();
	}

	@GET
	@Path("/finalization/getaccount/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finalAccountNumber(@PathParam("projectId") String projectId) {
		 
		Map<String,String> response = new HashMap<>();

		String finalAccountNumber = this.aaeServiceImpl.getFinalAccountNumber(projectId);
		response.put("finalAccountNumber", finalAccountNumber);
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/finalization/resetaccount/{quoteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetAccountNumber(@PathParam("quoteId") String quoteId) {

		this.aaeServiceImpl.resetFinalAccountNumber(quoteId);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assignee")
	public Response accountInfoUpdate(@FormDataParam("quoteId") String quoteId, @FormDataParam("action")String action) {
		String user = SecurityUtils.getLoggedInUser();
		logger.info("Updating assignee {} {}", quoteId, action);
		if(QuoteAssigneeRequest.ActionType.ASSIGN.name().equalsIgnoreCase(action)) {
			Map<String, String> response = this.aaeServiceImpl.assignQuote(user, user, quoteId);
			return Response.ok(response).build();
		} else if(QuoteAssigneeRequest.ActionType.UNASSIGN.name().equalsIgnoreCase(action)) {
			this.aaeServiceImpl.unassignQuote(user, user, quoteId);
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updatestatus")
	public Response statusCodeUpdate(@FormDataParam("quoteId") String quoteId, @FormDataParam("statusCode")String statusCode, @FormDataParam("note") String note) {
		
		logger.info("Updating statusCode {} {}", quoteId, statusCode, note);
		Map<String, String> response = new HashMap<>();
		String previousStatusCode = this.aaeServiceImpl.updateStatusCode(quoteId, statusCode, note);
		response.put("previousStatusCode", previousStatusCode);
		return Response.ok().entity(response).build();

	}

	@GET
	@Path("/{quoteid}/metadata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuoteMetadata(@PathParam("quoteid") String quoteId){
		
		QuoteMetadata metadata = this.aaeServiceImpl.getQuoteMetadata(quoteId);
		
		return Response.ok().entity(metadata).build();
	}

	@GET
	@Path("/listdata/{division}/{selected}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOptions(@PathParam("division") String division, @PathParam("selected") String selected){
	
		ListDataEntityType selection = ListDataEntityType.fromName(selected);
		ListDataResponse listDataResponse = this.aaeServiceImpl.getListDataOptions(division, selection);
		
		return Response.ok().entity(listDataResponse).build();
}

	@POST
	@Path("/ratedelete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rateInfoDelete(
			RateEdit rateEdit) {
			
		this.aaeServiceImpl.deleteRateInformation(rateEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/ratecreate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rateInfoCreate(
			RateCreate rateCreate) {
			
		this.aaeServiceImpl.createRateInformation(rateCreate);
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/{quoteid}/contractinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContractResponse(@PathParam("quoteid") String quoteId) {
		ContractResponse cInfo = this.aaeService.getContractInfo(quoteId);
		
		logger.debug("Info for this contract {}",
			this.serializerFactory.getSerializer("contract-info").serialize(cInfo));
		
		return Response.ok().entity(this.serializerFactory.getSerializer("contract-info").serialize(cInfo)).build();
	}
	
	@POST
	@Path("/containerdelete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response containerInfoDelete(
			ContainerEdit containerEdit) {
			
		this.aaeServiceImpl.deleteContainerInformation(containerEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/fsrdelete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fsrInfoDelete(
			ContainerEdit containerEdit) {
			
		this.aaeServiceImpl.deleteFsrInformation(containerEdit);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/errorReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport(
			AaeErrorReport errorReport) {
			
		this.aaeServiceImpl.getAaeErrorReport(errorReport);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/exceptionreport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExceptionReport(
			AaeExceptionReport errorReport) {
			
		this.aaeServiceImpl.getAaeExceptionReport(errorReport);
		
		return Response.ok().build();
	}

	@GET
	@Path("/viewquotenotes/{quoteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotes(@PathParam("quoteId") String quoteId) {
			
		List<QuoteNotes> viewNotes = this.aaeServiceImpl.getQuoteNotes(quoteId);
		logger.info(viewNotes.get(0).getNoteLines());
		return Response.ok().entity(viewNotes).build();
	}
}
