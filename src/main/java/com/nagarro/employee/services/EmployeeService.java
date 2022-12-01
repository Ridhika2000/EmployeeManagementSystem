package com.nagarro.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;


import com.nagarro.employee.entity.Employee;
import com.nagarro.employee.exceptions.ResourceNotFoundException;
import com.nagarro.employee.payload.response.MessageResponse;



@Component
public interface EmployeeService {
	MessageResponse createEmployee(Employee employee) throws ResourceNotFoundException;
    Employee getASingleEmployee(Integer employeeId) throws ResourceNotFoundException, Exception;
    List<Employee> getAllEmployee() throws Exception;
    Employee updateEmployee(Integer employeeId, Employee employee) throws ResourceNotFoundException, Exception;
    MessageResponse deleteEmployee(Integer employeeId) throws ResourceNotFoundException, Exception;
    String getDepartmentByEmployeeId(Integer employeeId,String employeeName) throws ResourceNotFoundException, Exception;
    List<Employee> getEmployeesOfDepartment(String departmentName) throws Exception;
    
}