package com.repsrv.csweb.core.account.servicerecordings.model;

public class EmployeeDto {
	private String employeeNumber;
	private String employeeType;
	private String firstName;
	private String lastName;
	
	public EmployeeDto() {}
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "EmployeeDto [employeeNumber=" + employeeNumber + ", employeeType=" + employeeType + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
}
