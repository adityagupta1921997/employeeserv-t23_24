package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.InvalidEmployeeException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.util.EmployeeUtils;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	EmployeeService employeeService;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) {
		Employee employee = null;
		EmployeeEntity empEntity = employeeService.getEmployee(Integer.parseInt(id.trim())).get();
		employee = EmployeeUtils.entityToModel(empEntity);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<Employee> createEmployee(Employee newEmployee) {
		List<String> errors = EmployeeUtils.isValid(newEmployee);
		if (errors.size() == 0) {
			EmployeeEntity employeeEntity = EmployeeUtils.modelToEntity(newEmployee);
			try {
				employeeEntity = employeeService.getEmployee(employeeEntity).get();
			} catch(NoSuchElementException e) {
				employeeEntity = employeeService.createEmployee(employeeEntity);
			}
			return new ResponseEntity<>(EmployeeUtils.entityToModel(employeeEntity), HttpStatus.CREATED);
		} else {
			throw new InvalidEmployeeException(errors, "Invalid Employee details");
		}
	}
}
