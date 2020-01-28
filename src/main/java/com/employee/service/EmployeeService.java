package com.employee.service;

import com.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int emp_id);
	public Employee saveEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public List<Employee> findByManager(int mgr_id);
	
	
}
