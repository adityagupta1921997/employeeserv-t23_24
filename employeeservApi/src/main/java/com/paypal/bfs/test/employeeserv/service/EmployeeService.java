package com.paypal.bfs.test.employeeserv.service;

import java.util.Optional;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

public interface EmployeeService {
	
	public EmployeeEntity createEmployee(EmployeeEntity employee);
	
	public Optional<EmployeeEntity> getEmployee(int id);
	
	public Optional<EmployeeEntity> getEmployee(EmployeeEntity employee);


}
