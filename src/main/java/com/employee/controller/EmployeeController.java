package com.employee.controller;

import com.employee.exception.ResourceNotFound;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.validators.Identification;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@Validated
@Api(value = "Employee Data Service",
    description = "Operations pertaining to Employee in employee Data Service")
public class EmployeeController {

  @Autowired
  EmployeeService service;

  /**
   * get the list of all available employees from database
   * 
   * @return List of Employees
   */
  @GetMapping("/employee")
  @ApiOperation(value = "Get list of Employees", response = List.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  public List<Employee> getAllEmployees() {
    log.debug("Executed EmployeeController.getAllEmployees() to retrive all employees data");
    return service.getAllEmployees();
  }

  /**
   * to save employee data into database
   * 
   * @param emp Employee Object that is going to persist into database
   * @return emp return employee Object
   * 
   * 
   */
  @PostMapping("/employee")
  @ApiOperation(value = "Save Employee object into the database", response = Employee.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  public Employee saveEmployee(
      @ApiParam(value = "Employee object", required = true) @RequestBody Employee emp) {
    log.debug("Executed EmployeeController.saveEmployees(emp) to save employee object");
    return service.saveEmployee(emp);
  }


  /**
   * @param id identifier of the employee
   * @return emp return employee object with given identifier
   * @throws ResourceNotFound If system not find any object associated with given identifier then it
   *         will throw ResourceNotFound exception
   */
  @ApiOperation(value = "Get specific Employee by id", response = Employee.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  @GetMapping("/employee/{id}")
  public ResponseEntity<Object> getEmployeeById(
      @ApiParam(value = "Employee id", required = true) @PathVariable("id") @Identification int id)
          throws ResourceNotFound {

    Employee emp = service.getEmployeeById(id);
    System.out.println(emp);
    log.debug(
        "Executed EmployeeController.getEmployeeById(id) to get employee object with id " + id);
    if (emp == null)
      throw new ResourceNotFound("Resource Not Found");
    return new ResponseEntity<>(emp, HttpStatus.OK);
  }

  /**
   * Delete employee with given id, it will first check whether employee with given identifier is
   * exits into database or not
   * 
   * @param id identifier of the employee you want to delete
   * 
   * @throws ResourceNotFound If system doesn't find any employee with given identifier then it will
   *         throw ResourceNotFound Exception
   */
  @ApiOperation(value = "Delete Employee by id")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  @DeleteMapping("/employee/{id}")
  public ResponseEntity<Object> deleteEmployee(
      @ApiParam(value = "Employee id", required = true) @PathVariable("id") @Identification int id)
          throws ResourceNotFound {
    Employee emp = service.getEmployeeById(id);
    log.debug(
        "Executed EmployeeController.deleteEmployee(id) to delete employee object with id " + id);
    if (emp == null)
      throw new ResourceNotFound("Resource Not Found");
    else {
      service.deleteEmployee(emp);
      return new ResponseEntity<Object>(HttpStatus.OK);
    }

  }

  /**
   * update the existing employee into the database, it will first check whether employee with given
   * identifier is exits into database or not
   * 
   * @param id identifier of the employee you want to update
   * @param employee employee object with the data you want to update
   * @return updated return updated employee object
   * @throws ResourceNotFound If Employee with the specified id is not exits then it will throw
   *         ResorceNotFound Exception
   */
  @ApiOperation(value = "Update Employee by id")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  @PutMapping("/employee/{id}")
  public ResponseEntity<Object> updateEmployee(
      @ApiParam(value = "Employee id", required = true) @PathVariable("id") @Identification int id,
      @ApiParam(value = "Employee object", required = true) @RequestBody Employee employee)
          throws ResourceNotFound {
    Employee emp = service.getEmployeeById(id);
    log.debug("Executed EmployeeController.updateEmployee(id,emp) to update employee object with id"
        + id);
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
   * @param id identifier of the manager
   * @return List of Employees who reports to manager with given identifier
   * 
   */
  @ApiOperation(value = "Get All Employees who reports to same manager", response = List.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!"),
      @ApiResponse(code = 404, message = "not found!!!")})
  @GetMapping("/manager/{id}")
  public ResponseEntity<Object> getEmployeeByManager(
      @ApiParam(value = "Manager id", required = true) @PathVariable("id") @Identification int id) {
    List<Employee> emp = service.findByManager(id);
    log.debug(
        "Executed EmployeeController.getEmployeeByManager(id) to get list of employees who's manager with id"
            + id);

    return new ResponseEntity<>(emp, HttpStatus.OK);
  }

}
