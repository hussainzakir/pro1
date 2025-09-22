package com.repsrv.csweb.rest.obligations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.repsrv.csweb.core.model.obligationsSearch.ObligationRegion;
import com.repsrv.csweb.core.model.obligationsSearch.Obligation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.obligations.service.ObligationsSearchServiceImpl;
import com.repsrv.csweb.rest.BaseResource;

import java.util.List;


@Component
@Scope("request")
@Path("/obligations")
public class ObligationsSearchResource extends BaseResource {

    protected final Logger logger = LoggerFactory.getLogger(ObligationsSearchResource.class);


    @Autowired
    private ObligationsSearchServiceImpl obligationsSearchService;

    @GET
    @Path("/regions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObligationRegionsResults()
    {
        List<ObligationRegion> obligationRegions = obligationsSearchService.getObligationRegionsList();
        return Response.ok().entity(new GenericEntity<List<ObligationRegion>>(obligationRegions){}).build();
    }

    @GET
    //@Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObligationsSearchResults(
                @QueryParam("regionCode") String regionCode,
                @QueryParam("obligationId") String obligationId,
                @QueryParam("accountingPeriodFrom") String accountingPeriodFrom,
                @QueryParam("accountingPeriodTo") String accountingPeriodTo,
                @QueryParam("amountRangeFrom") String amountRangeFrom,
                @QueryParam("amountRangeTo") String amountRangeTo)
    {
            List<Obligation> searchForObligations = obligationsSearchService.getObligationsSearchResults(regionCode,obligationId, accountingPeriodFrom,
                    accountingPeriodTo,amountRangeFrom,amountRangeTo);
        return Response.ok().entity(new GenericEntity<List<Obligation>>(searchForObligations){}).build();
    }

}


