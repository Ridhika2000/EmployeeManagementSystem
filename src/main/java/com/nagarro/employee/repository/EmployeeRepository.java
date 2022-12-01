package com.nagarro.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDeptName(String departmentName);

	Employee findByEmpId(int empid);

}
