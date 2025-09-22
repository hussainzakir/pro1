package com.repsrv.csweb.core.employee.service;

import java.util.List;

import com.repsrv.csweb.core.model.employee.Employee;

public interface EmployeeService {

	List<Employee> getEmployeeListByCompany(String company);
}
