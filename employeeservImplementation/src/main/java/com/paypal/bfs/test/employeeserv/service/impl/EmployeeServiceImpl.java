package com.paypal.bfs.test.employeeserv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.EmployeeJpaRepository;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeJpaRepository employeeRepo;

	@Override
	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Optional<EmployeeEntity> getEmployee(int id) {
		return employeeRepo.findById(Long.valueOf(id));
	}

	@Override
	public Optional<EmployeeEntity> getEmployee(EmployeeEntity employee) {
		Example<EmployeeEntity> example = Example.of(employee);
		return employeeRepo.findOne(example);
	}
}
