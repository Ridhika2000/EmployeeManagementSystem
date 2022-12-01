package com.nagarro.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nagarro.employee.entity.Employee;
import com.nagarro.employee.entity.EmployeeRedis;
import com.nagarro.employee.exceptions.ResourceNotFoundException;

@Component
public interface EmployeeRedisService {

	public void create(EmployeeRedis employeeRedis);
	
	public void updateEmp(EmployeeRedis employeeRedis) throws ResourceNotFoundException, Exception;
	
	public void deleteEmp(Integer id) throws ResourceNotFoundException,Exception;

	public String getDept(Integer id) throws ResourceNotFoundException, Exception;

	
}
