package com.repsrv.csweb.rest.employee;

import java.util.List;

import javax.ws.rs.GET;
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

import com.repsrv.csweb.core.employee.service.EmployeeService;
import com.repsrv.csweb.core.model.employee.Employee;
import com.repsrv.csweb.rest.BaseResource;

@Component
@Scope("request")
@Path("/employee")
public class EmployeeResource extends BaseResource{

	
	@Autowired
	private EmployeeService employeeService;
	
	@GET
	@Path("/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees(@PathParam("company") String company) throws JSONException {

		List<Employee> employees = this.employeeService.getEmployeeListByCompany(company);

		if (employees == null)
			return Response.status(Status.NOT_FOUND).entity("Company employees not found").build();

		return Response.ok().entity(this.serializerFactory.getSerializer("employee-list")
				.serialize(employees))
				.build();
	}
}
