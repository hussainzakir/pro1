package com.repsrv.csweb.rest.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.rest.BaseResource;

@Component
@Scope("request")
@Path("/user")
public class UserResource extends BaseResource{

	
	/**
     * Returns the {@link User} that is currently logged into the system.
     *
     * @return the logged in user or 404 is no user is logged in.
     */
    @GET
    @Path("/authenticated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthenticatedUser(){
        Authentication auth = SecurityUtils.getLoggedInUserAuthentication();
        if(auth == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok().entity(this.serializerFactory
                .getSerializer("get-authenticated-user")
                .serialize(auth))
                .build();
    }
    
    
}
