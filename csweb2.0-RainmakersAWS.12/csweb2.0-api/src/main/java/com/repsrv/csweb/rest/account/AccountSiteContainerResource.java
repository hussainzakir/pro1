package com.repsrv.csweb.rest.account;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.container.model.IndustrialStatServiceHistory;
import com.repsrv.csweb.core.account.container.model.ContainerServiceHistory;
import com.repsrv.csweb.core.account.container.service.ContainerService;
import com.repsrv.csweb.core.model.container.ContainerGroupDetail;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;
import com.repsrv.csweb.core.util.ContainerAccountType;
import com.repsrv.csweb.rest.BaseResource;

import flexjson.JSONSerializer;
import flexjson.transformer.ValueTransformer;

@Component
@Scope("request")
@Path("/account/{company}/{account}/{site}/container")
public class AccountSiteContainerResource extends BaseResource {

	@Autowired
	private ContainerService containerService;
	
	@GET
	@Path("/{container}/detail")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContainerDetail(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site, @PathParam("container") int container) {

		ContainerGroupDetail detail = this.containerService.getContainerDetail(company, account, site, container);
		
		if (detail == null)
			return Response.status(Status.NOT_FOUND).entity("container not found").build();

		return Response.ok().entity(this.serializerFactory
				.getSerializer("container-detail")
				.serialize(detail))
				.build();
	}
	
	@GET
	@Path("/{container}/industrialstatisticsservhistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIndustrialstatisticsServiceHistory(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site, 
			@PathParam("container") int container,
			@QueryParam("DateServed") int DateServed) {
		
		List<IndustrialStatServiceHistory> IndustrialStatisticsservhistory = this.containerService.getIndustrialstatisticsServiceHistory(company, account, site, container, DateServed);
		return Response.ok().entity(IndustrialStatisticsservhistory).build();
	}
	
	
	@GET
	@Path("/{container}/containerservhistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContainerServiceHistory(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site, 
			@PathParam("container") int container,
			@QueryParam("DateServed") int DateServed) {
		
		List<ContainerServiceHistory> containerservhistory = this.containerService.getContainerServiceHistory(company, account, site, container, DateServed);
		return Response.ok().entity(containerservhistory).build();
	}
	
	
}
