package com.nagarro.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.employee.entity.Employee;
import com.nagarro.employee.entity.EmployeeRedis;
import com.nagarro.employee.exceptions.ResourceNotFoundException;
import com.nagarro.employee.payload.response.MessageResponse;
import com.nagarro.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeRedisService employeeRedisService;

	private static final String KEY = "EMPLOYEE";

	@Override
	public MessageResponse createEmployee(Employee employee) throws ResourceNotFoundException {

		Employee emp = employeeRepository.findByEmpId(employee.getEmpId());
		if (emp != null) {

			throw new ResourceNotFoundException("Employee already exists with id :: " + employee.getEmpId());

		}
		// save employee to database
		else {
			Employee emp1 = employeeRepository.save(employee);
			EmployeeRedis employeeRedis = new EmployeeRedis();
			employeeRedis.setEmp_id(emp1.getEmpId());
			employeeRedis.setEmp_dept(emp1.getDeptName());

			// save employee id and department in redis
			employeeRedisService.create(employeeRedis);
		}

		return new MessageResponse("New Employee created successfully", "CREATED");

	}

	@Override
	public Employee getASingleEmployee(Integer employeeId) throws Exception {
		String message = "Error in getting employee by id";
		Employee emp = employeeRepository.findByEmpId(employeeId);
		try {
			if (emp == null) {
				message = "Employee not found for this id :: " + employeeId;
				throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(message);
		}
	}

	@Override
	public List<Employee> getAllEmployee() throws Exception {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in getting all employees.Try again..");
		}

	}

	@Override
	public Employee updateEmployee(Integer employeeId, Employee employee) throws Exception {

		Employee emp = employeeRepository.findByEmpId(employeeId);

		if (employee.getEmpId() == null || employeeId == employee.getEmpId()) {
			if (emp != null) {
				emp.setEmpName(employee.getEmpName());
				emp.setDeptName(employee.getDeptName());

				// updating in employee database
				Employee emp1 = employeeRepository.save(emp);

				// updating in redis
				EmployeeRedis employeeRedis = new EmployeeRedis();
				employeeRedis.setEmp_id(emp1.getEmpId());
				employeeRedis.setEmp_dept(emp1.getDeptName());

				employeeRedisService.updateEmp(employeeRedis);
			}
			return emp;
		}

		else {
			throw new Exception("Error in updating employee.Check once");
		}

	}

	@Override
	public MessageResponse deleteEmployee(Integer employeeId) throws Exception {

		Employee emp = employeeRepository.findByEmpId(employeeId);
		if (emp == null) {

			throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
		}

		// delete employee from database
		employeeRepository.deleteById(employeeId);

		// delete employee from redis
		employeeRedisService.deleteEmp(employeeId);

		return new MessageResponse("Employee deleted successfully", "DELETED");

	}

	@Override
	public String getDepartmentByEmployeeId(Integer employeeId, String employeeName) throws Exception {

		String depart = employeeRedisService.getDept(employeeId);
		String dep = "Department for employeeId " + employeeId + " is ";
		if (depart == null) {
			dep += "default_dept";
		} else {
			dep += depart;
		}
		return dep;
	}

	// finding all employees by department name
	@Override
	public List<Employee> getEmployeesOfDepartment(String departmentName) throws Exception {
		try {
			List<Employee> employees = employeeRepository.findByDeptName(departmentName);
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error finding employees by departmentId.Try again..");
		}

	}

}
