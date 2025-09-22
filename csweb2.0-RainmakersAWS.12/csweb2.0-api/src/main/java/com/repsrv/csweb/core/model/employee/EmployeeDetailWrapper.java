package com.repsrv.csweb.core.model.employee;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmployeeDetailWrapper{
	@JsonProperty("Details")
	public List<Employee> employees;
	public EmployeeDetailWrapper() {}

}
