package com.repsrv.csweb.rest.health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.util.BuildIdService;

@Component
@Scope("request")
@Path("/build-version")
public class BuildIdResource {

	@Autowired
	private BuildIdService buildService;
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public Response getBuildVersion() {
        return Response.ok().entity(buildService.getBuildId()).build();
	}
}
