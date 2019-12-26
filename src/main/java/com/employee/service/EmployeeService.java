package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int emp_id);
	public Employee saveEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public List<Employee> findByManager(int mgr_id);
	
	
}
