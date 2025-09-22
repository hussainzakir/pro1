package com.repsrv.csweb.rest.support.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.model.error.ErrorDto;
import com.repsrv.csweb.core.support.exception.StoredProcedureException;

@Component
@Provider
public class StoredProcedureErrorMapper implements ExceptionMapper<StoredProcedureException>{

	@Override
	public Response toResponse(StoredProcedureException e) {
		
		ErrorDto errorDto = ErrorDto.builder()
				.errorMsg(e.getMessage())
				.build();
		
		return Response
        .status(Response.Status.PRECONDITION_FAILED)
        .type(MediaType.APPLICATION_JSON)
        .entity(errorDto)
        .build();
	}

}
