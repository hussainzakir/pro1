package com.repsrv.csweb.rest.support.providers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);
    private static final String PROD_ENV = "prod";

    @Value("#{systemEnvironment['csweb-env']}")
    private String environment;

    @Context 
    private HttpServletRequest request;

    @Context 
    private HttpServletResponse response;

    @Override
    public Response toResponse(Throwable exception){
        logger.error("Web Service Error: ", exception);
        String method = this.request.getMethod();
        String pathInfo = this.request.getPathInfo();
        String stacktrace = ExceptionUtils.getStackTrace(exception);

        if(StringUtils.isEmpty(this.environment) || !this.environment.equalsIgnoreCase(PROD_ENV))
            return Response.serverError().entity(stacktrace).build();
        else
            return Response.status(500).entity("Something bad happened. Please try again !!").type("text/plain").build();
    }
}
