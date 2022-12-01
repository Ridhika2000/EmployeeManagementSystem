package com.nagarro.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nagarro.employee.entity.Employee;
import com.nagarro.employee.exceptions.ResourceNotFoundException;
import com.nagarro.employee.payload.response.MessageResponse;
import com.nagarro.employee.services.EmployeeService;

@EnableWebMvc
@RestController
@RequestMapping("/employee")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	 //creating new employee
	@PostMapping("/add")
	public ResponseEntity<MessageResponse> addEmployee(@Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		System.out.println("hello");
		MessageResponse newEmployee = employeeService.createEmployee(employee);
			return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
		
	}


	@GetMapping("/find/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") Integer empId) throws Exception {
		Employee emp =  employeeService.getASingleEmployee(empId);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	// get all employees from database
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {
		List<Employee> employees = employeeService.getAllEmployee();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// update an employee
	@PutMapping("/update/{empId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("empId") Integer empId, @Valid @RequestBody Employee employee)
			throws Exception {
		Employee emp = employeeService.updateEmployee(empId, employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable("id") Integer id) throws Exception {
		MessageResponse deletedEmployee = employeeService.deleteEmployee(id);

		return new ResponseEntity<>(deletedEmployee, HttpStatus.ACCEPTED);

	}

	@GetMapping("/department/{empId}/{empName}")
	public String getDepartmentByEmployeeId(@PathVariable("empId") Integer empId,
			@PathVariable("empName") String empName) throws Exception {
		return employeeService.getDepartmentByEmployeeId(empId, empName);
		
	}

	@GetMapping("/{depName}")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("depName") String depName) throws Exception {
		List<Employee> employees = employeeService.getEmployeesOfDepartment(depName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

}

//Content-Type:application/json
