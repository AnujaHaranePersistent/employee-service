package com.employee.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.employee.exception.ResourceNotFound;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Employee Data Service", description = "Operations pertaining to Employee in employee Data Service")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService service;

	/**
	 * to get the list of all available employees into database
	 * 
	 * @return List of Employees
	 */
	@GetMapping
	@ApiOperation(value = "Get list of Employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public List<Employee> getAllEmployees() {
		logger.debug("getting all employees");
		return service.getAllEmployees();
	}

	/**
	 * to save employee data into database
	 * 
	 * @param emp
	 *           	    Employee Object that is going to persist into database
	 * @return emp 		Employee Object
	 * @exception Internal server Error if object violets any constraints
	 *     
	 */
	@PostMapping
	@ApiOperation(value = "Save Employee object into the database", response = Employee.class)
	public Employee saveEmployee(@ApiParam(value = "Employee object", required = true) @RequestBody Employee emp) {
		return service.saveEmployee(emp);
	}

	/**
	 * @param id   id of the employee
	 * @return emp Employee object with specified id
	 * @throws ResourceNotFound
	 *             If system not find any object associated with specified id
	 *             then it will throw ResourceNotFound exception
	 */
	@ApiOperation(value = "Get specific Employee by id", response = Employee.class)
	@GetMapping("/employee/{id}")
	public ResponseEntity<Object> getEmployee(
			@ApiParam(value = "Employee id", required = true) @PathVariable("id") int id) throws ResourceNotFound {
		Employee emp = service.getEmployeeById(id);
		System.out.println(emp);
		logger.info("into get emploee");
		if (emp == null)
			throw new ResourceNotFound("Resource Not Found");
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	/**
	 * to delete employee with specified id, it will first check whether
	 * employee with specified is exits into database or not
	 * 
	 * @param id
	 *            id of the employee you want to delete
	 * 
	 * @throws ResourceNotFound
	 *             If system doesn't find any employee with specified id then it
	 *             will throw ResourceNotFound Exception
	 */
	@ApiOperation(value = "Delete Employee by id")
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Object> deleteEmployee(
			@ApiParam(value = "Employee id", required = true) @PathVariable("id") int id) throws ResourceNotFound {
		Employee emp = service.getEmployeeById(id);
		if (emp == null)
			throw new ResourceNotFound("Resource Not Found");
		else {
			service.deleteEmployee(emp);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}

	}

	/**
	 * update the existing employee into the database, it will first check
	 * whether employee with specified is exits into database or not
	 * 
	 * @param id
	 *            id of the employee you want to update
	 * @param employee
	 *            employee object
	 * @return updated employee object
	 * @throws ResourceNotFound
	 *             If Employee with the specified id is not exits then it will
	 *             throw ResorceNotFound Exception
	 */
	@ApiOperation(value = "Update Employee by id")
	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> updateEmployee(
			@ApiParam(value = "Employee id", required = true) @PathVariable("id") int id,
			@ApiParam(value = "Employee object", required = true) @RequestBody Employee employee)
					throws ResourceNotFound {
		Employee emp = service.getEmployeeById(id);
		System.out.println(emp);
		if (emp == null)
			throw new ResourceNotFound("Resource Not Found");
		else {
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			service.updateEmployee(emp);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}

	}

	/**
	 * @param id
	 *            id of the manager
	 * @return List of Employee to reports to manager with specified id
	 * @throws ResourceNotFound
	 */
	@ApiOperation(value = "Get All Employees who reports to same manager", response = List.class)
	@GetMapping("/manager/{id}")
	public ResponseEntity<Object> getEmployeeByManager(
			@ApiParam(value = "Manager id", required = true) @PathVariable("id") int id) throws ResourceNotFound {
		List<Employee> emp = service.findByManager(id);
		System.out.println(emp);
		if (emp == null)
			throw new ResourceNotFound("Resource Not Found");
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

}
