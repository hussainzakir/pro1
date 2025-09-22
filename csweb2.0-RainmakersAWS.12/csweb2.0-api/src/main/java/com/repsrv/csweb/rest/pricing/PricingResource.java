package com.repsrv.csweb.rest.pricing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.repsrv.csweb.core.account.pricing.model.PriceIncreaseRow;
import com.repsrv.csweb.core.account.pricing.model.PriceIncreaseUploadType;
import com.repsrv.csweb.core.account.pricing.service.PriceIncreaseService;
import com.repsrv.csweb.core.support.exception.priceincrease.PriceIncreaseException;
import com.repsrv.csweb.rest.BaseResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Scope("request")
@Path("/pricing")
public class PricingResource extends BaseResource {

	
	@Autowired
	private PriceIncreaseService priceIncreaseService;
	
	@POST
    @Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public Response saveTemplateUpload(
    		@FormDataParam("file") InputStream uploadedInputStream,
    	    @FormDataParam("file") FormDataContentDisposition fileDetails,
    	    @FormDataParam("uploadType")String type) throws JSONException, IOException {

		System.out.println("Filename is "+fileDetails.getName());
		System.out.println("Upload Type " + type);
		
		 PriceIncreaseUploadType uploadTypeEnum = PriceIncreaseUploadType.valueOfSafe(type);
		    if (uploadTypeEnum == null) {
		       Map<String, String> response = new HashMap<>();
		       response.put("msg", "Invalid upload type");
		       return Response.status(Response.Status.BAD_REQUEST)
			            .entity(response).build();
		    }

		    String fileContent = new BufferedReader(new InputStreamReader(uploadedInputStream, StandardCharsets.UTF_8))
		            .lines()
		            .collect(Collectors.joining("\n"));

		    String headerLine = fileContent.split("\n", 2)[0];
		    List<String> actualHeaders = Arrays.stream(headerLine.split(","))
		        .map(String::trim)
		        .collect(Collectors.toList());

		    if (!this.priceIncreaseService.validateTemplateHeaders(actualHeaders, uploadTypeEnum)) {
		        return Response.status(Response.Status.BAD_REQUEST)
		            .entity("{\"msg\": \"Invalid template please select the correct one.\"}")
		            .build();
		    }

		    StringReader csvStringReader = new StringReader(fileContent);

		    HeaderColumnNameMappingStrategy<PriceIncreaseRow> strategy = new HeaderColumnNameMappingStrategy<>();
		    strategy.setType(PriceIncreaseRow.class);

		    CsvToBean<PriceIncreaseRow> csvToBean = new CsvToBeanBuilder<PriceIncreaseRow>(csvStringReader)
		            .withMappingStrategy(strategy)
		            .withIgnoreLeadingWhiteSpace(true)
		            .build();

		    List<PriceIncreaseRow> rows = csvToBean.parse();

		    logger.info("Successfully parsed {} rows", rows.size());

		    this.priceIncreaseService.importPriceIncreases(rows, type);

		    return Response.ok().build();		
    }
	
	@GET
    @Path("/template/{type}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadTemplate(@PathParam("type") String type) {
        PriceIncreaseUploadType typeO = PriceIncreaseUploadType.valueOfSafe(type);
        if (typeO == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid upload type").build();
        }
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(typeO.getTemplateName());
        if (inputStream == null) {
            return Response.status(Status.NOT_FOUND).entity("Template not found").build();
        }
        return Response.ok(inputStream)
                .header("Content-Disposition", "attachment; filename=\"" + typeO.getTemplateName() + "\"")
                .build();
    }

}
