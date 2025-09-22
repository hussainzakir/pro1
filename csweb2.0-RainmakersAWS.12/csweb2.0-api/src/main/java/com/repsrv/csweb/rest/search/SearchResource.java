package com.repsrv.csweb.rest.search;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.search.service.SearchService;
import com.repsrv.csweb.rest.BaseResource;

@Component
@Scope("request")
@Path("/cs-search")
public class SearchResource extends BaseResource{

	protected final Logger logger = LoggerFactory.getLogger(SearchResource.class);
	
	@Autowired
	private SearchService searchService;
	
	
	@GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchResults(@QueryParam("division") String division,
			@QueryParam("locationId") String locationId, @QueryParam("acctNum") String acctNum,
			@QueryParam("siteNumber") String site, @QueryParam("acctSiteName") String acctSiteName,
			@QueryParam("streetAddress") String streetAddress, @QueryParam("city") String city, 
			// @QueryParam("purchaseOrderNum") String purchaseOrderNum,
			// @QueryParam("accountEmail") String accountEmail,
			// @QueryParam("openObligationBalance") String openObligationBalance,
			@QueryParam("state") String state, @QueryParam("zipCode") String zipCode,
			@QueryParam("phone") String phone, @QueryParam("includeClosedSites") String includeClosedSites,
			@QueryParam("nationalAccountsOnly") String nationalAccountsOnly, @QueryParam("orderBy") String orderBy,
			@QueryParam("rows") int rows, @QueryParam("userId") String userId){
       
        return Response.ok().entity(this.serializerFactory
                .getSerializer("get-cs-search")
                .serialize(this.searchService.getSearchResults(division, locationId, acctNum, site, acctSiteName, streetAddress, 
        				city, state, zipCode,
						//  purchaseOrderNum, 
						//  accountEmail,
						//   openObligationBalance, 
						  phone, includeClosedSites, nationalAccountsOnly, orderBy, "", rows, userId)))
                .build();
    }
	
}
