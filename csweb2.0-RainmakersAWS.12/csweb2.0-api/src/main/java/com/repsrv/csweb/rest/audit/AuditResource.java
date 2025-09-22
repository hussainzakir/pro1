package com.repsrv.csweb.rest.audit;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.repsrv.csweb.core.audit.service.AuditService;
import com.repsrv.csweb.core.model.audit.AuditRequest;
import com.repsrv.csweb.core.model.audit.AuditResults;
import com.repsrv.csweb.rest.BaseResource;

@Component
@Scope("request")
@Path("/audit-search")
public class AuditResource extends BaseResource {
	@Autowired
	private AuditService auditService;

	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSearchResults(@RequestBody AuditRequest auditRequest) {
		List<AuditResults> auditResults = auditService.getAuditResults(auditRequest);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("auditResults", auditResults);
		responseMap.put("totalCount", auditRequest.getTotalRows());
		return Response.ok().entity(responseMap).build();

	}

	@POST
	@Path("/export/audit")
	@Produces("text/csv")
	public Response exportAudit(@RequestBody AuditRequest auditRequest) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		auditService.exportAuditResults(auditRequest,outputStream);
		byte[] auditResultsData = outputStream.toByteArray();
		return Response.ok(auditResultsData)
				.header("Content-Disposition", "attachment:filename=audit_results.csv")
				.build();
	}
}
