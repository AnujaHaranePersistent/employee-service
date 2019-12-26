package com.employee.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.controller.EmployeeController;
import com.employee.model.Employee;
import com.employee.repositary.EmployeeRepositary;

/**
 * Employee service that is responsible to talk to repository
 * 
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	@Autowired
	EmployeeRepositary repositary;

	/*
	 * to get all the list of employees
	 * 
	 * @see com.employee.service.EmployeeService#getAllEmployees()
	 * 
	 * @return list of employees
	 */
	@Override
	public List<Employee> getAllEmployees() {
		
		logger.debug("EmplyeeService.getAllEmployee()  return list of employees");
		return repositary.findAll();
	}

	/*
	 * get a employee by id 
	 * 
	 * @see com.employee.service.EmployeeService#getEmployeeById(int)
	 * 
	 * @param emp_id the id of employee
	 */
	@Override
	public Employee getEmployeeById(int emp_id) {
		logger.debug("EmplyeeService.getEmployeeById(int emp_id)  return Employee object with id" + emp_id);
		return repositary.findById(emp_id);
	}

	/*
	 * save employee object into database
	 * 
	 * @see
	 * com.employee.service.EmployeeService#saveEmployee(com.employee.model.
	 * Employee)
	 * 
	 * @param emp Employee object to persist into database
	 * 
	 * @return Employee object
	 */
	@Override
	public Employee saveEmployee(Employee emp) {
		logger.debug("EmplyeeService.saveEmployee(Employee emp)  save Employee object");
		return repositary.save(emp);
	}

	/*
	 * delete the employee from database that id is specified
	 * 
	 * @see com.employee.service.EmployeeService#deleteEmployeeById(int)
	 * 
	 * @param emp_id id of the Employee
	 * 
	 * @return void
	 */
	@Override
	public void deleteEmployee(Employee emp) {
		logger.debug("EmplyeeService.deleteEmployee(Employee emp)  delete Employee object whos id is" + emp.getId());

		repositary.delete(emp);

	}

	/*
	 * update the specified employee into the database
	 * 
	 * @see com.employee.service.EmployeeService#updateEmployeeById(int,
	 * com.employee.model.Employee)
	 * 
	 * @param emp_id id of the Employee to be updated
	 * 
	 * @param emp Employee object
	 * 
	 * @return Updated Employee object
	 */
	@Override
	public Employee updateEmployee(Employee emp) {
		logger.debug("EmplyeeService.updateEmployee(Employee emp)  update Employee object whos id is" + emp.getId());
		return repositary.save(emp);
	}

	@Override
	public List<Employee> findByManager(int mgr_id) {
		logger.debug("EmplyeeService.findByManager(int mgr_id)  find list of Employees who report to manager whos id is" + mgr_id);
		//System.out.println( repositary.findAllByManager_Id(mgr_id));
		return repositary.findAllByManager_Id(mgr_id);
	}

}
