package com.repsrv.csweb.rest.aae;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.aae.model.AaeSearchResult;
import com.repsrv.csweb.core.aae.service.AutomatedAccountEntryService;
import com.repsrv.csweb.core.authorization.model.SuperUserPermissions;
import com.repsrv.csweb.core.model.aae.SearchParams;
import com.repsrv.csweb.core.support.exception.SecurityUtils;

@Component
@Scope("request")
@Path("/aae/search")
public class AaeSearchController {

	@Autowired
	private AutomatedAccountEntryService aaeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountDetail(@QueryParam("oracleDivision")String oracleDivision,
			@QueryParam("quoteID")String quoteID, @QueryParam("salesRep")String salesRep,
			@QueryParam("statusCode")String statusCode, @QueryParam("effectiveFrom")String effectiveFrom,
			@QueryParam("effectiveTo")String effectiveTo,@QueryParam("includeOnHold")String includeOnHold,
			@QueryParam("includeDeleted")String includeDeleted, @QueryParam("quoteType")String quoteType,
			@QueryParam("customerAccount")String customerAcct,
			@QueryParam("company")String company, @QueryParam("consolidatedAae")String consolidatedAae,
			@QueryParam("createDate")String createDate, @QueryParam("assignee")String assignee,
			@QueryParam("formatType")String formatType,
			@QueryParam("pageNumber")int pageNum, @QueryParam("pageSize")int pageSize) {
		
		String divPadded = StringUtils.isBlank(company) ? "": StringUtils.leftPad(company, 5);
		String user = SecurityUtils.getLoggedInUser();
		SearchParams searchForm = SearchParams.builder()
				.oracleDivision(oracleDivision)
				.quoteID(quoteID)
				.salesRep(salesRep)
				.statusCode(statusCode)
				.effectiveFrom(effectiveFrom)
				.effectiveTo(effectiveTo)
				.includeDeleted(includeDeleted)
				.includeHeld(includeOnHold)
				.companyNumber(divPadded) //"  800"
				.customerAccount(customerAcct)
				.quoteType(quoteType)
				.consolidatedAae(consolidatedAae)
				.createDate(createDate)
				.assignee(assignee)
				.formatType(formatType)
				.requestUser(user)
				.build();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AaeSearchResult results = null;
		if (authentication != null && authentication.getDetails() instanceof SuperUserPermissions) {
			SuperUserPermissions superPerms = (SuperUserPermissions) authentication.getDetails();
			String consolidatedAAE = superPerms.getConsolidatedAAE();

			if ("Y".equalsIgnoreCase(consolidatedAAE)) {
				results = aaeService.searchAccounts(searchForm, divPadded , pageNum, pageSize);

			} else {
				results = aaeService.searchDivisionalAccounts(searchForm, divPadded , pageNum, pageSize);
		}
	}
		
		return Response.ok().entity(results).build();
	}	
}
