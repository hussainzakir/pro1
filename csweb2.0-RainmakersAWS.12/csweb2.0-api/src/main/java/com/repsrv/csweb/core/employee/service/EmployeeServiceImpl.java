package com.repsrv.csweb.core.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.employee.dao.EmployeeDao;
import com.repsrv.csweb.core.model.employee.Employee;
import com.repsrv.csweb.core.model.employee.EmployeeDetailWrapper;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("employeeService")
public class EmployeeServiceImpl extends JsonResultService implements EmployeeService{

	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> getEmployeeListByCompany(String company) {
		StoredProcCallResult result = new StoredProcCallResult();

			String jsonResponse = this.employeeDao
					.getCompanyEmployees(company, result);
			
			logger.debug("JSON string result: {}", jsonResponse);
			EmployeeDetailWrapper wrapper = getJsonDataObject(jsonResponse, EmployeeDetailWrapper.class);
			return wrapper.employees;
	}

}
