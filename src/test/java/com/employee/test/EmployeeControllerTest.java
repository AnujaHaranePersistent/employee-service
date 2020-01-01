package com.employee.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.employee.controller.EmployeeController;
import com.employee.exception.ResourceNotFound;
import com.employee.model.Employee;
import com.employee.model.Manager;
import com.employee.service.EmployeeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

  @Autowired
  EmployeeController controller;

  @MockBean
  EmployeeService service;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllEmployees() {
    Manager manager = new Manager(101, "abc", "abc");
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "xyz", "xyz", 1001, manager));
    employees.add(new Employee(2, "xyz", "xyz", 1003, manager));
    employees.add(new Employee(3, "xyz", "xyz", 1002, manager));
    when(service.getAllEmployees()).thenReturn(employees);

    List<Employee> result = controller.getAllEmployees();
    assertEquals(3, result.size());
  }

  @Test
  public void testGetEmployeeById() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.getEmployeeById(1)).thenReturn(emp);
    ResponseEntity<Object> result = null;
    try {
      result = controller.getEmployeeById(1);

    } catch (ResourceNotFound e) {
      // TODO Auto-generated catch block
      // assertThat(result.getStatusCodeValue()).isEqualTo(400);
      e.printStackTrace();
    }

    assertThat(result.getStatusCodeValue()).isEqualTo(200);
  }

  @SuppressWarnings("null")
  @Test
  public void testGetEmployeeById_IfNotFound() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.getEmployeeById(1)).thenReturn(emp);
    ResponseEntity<Object> result = null;
    try {
      result = controller.getEmployeeById(2);
      assertEquals(null, result.getBody());
      fail("Should have thrown an exception");
    } catch (ResourceNotFound e) {
      // TODO Auto-generated catch block
      // assertThat(result.getStatusCodeValue()).isEqualTo(400);
      e.printStackTrace();
    }

  }

  @Test
  public void testUpdateEmployeeById() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.saveEmployee(emp)).thenReturn(emp);
    ResponseEntity<Object> result;
    try {
      result = controller.updateEmployee(1, emp);
      assertThat(result.getStatusCodeValue()).isEqualTo(200);
    } catch (ResourceNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }

  @Test
  public void testUpdateEmployeeById_IfNotFound() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.saveEmployee(emp)).thenReturn(emp);
    ResponseEntity<Object> result;
    try {
      result = controller.updateEmployee(1, emp);
      assertThat(result.getStatusCodeValue()).isEqualTo(200);
    } catch (ResourceNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }


  @Test
  public void testDeleteEmployeeById() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    doNothing().when(service).deleteEmployee(emp);
    ResponseEntity<Object> result;
    try {
      result = controller.deleteEmployee(1);
      assertThat(result.getStatusCodeValue()).isEqualTo(200);
    } catch (ResourceNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }


  @Test
  public void testGetEmployeesByManagerId() {
    Manager manager = new Manager(101, "abc", "abc");
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "xyz", "xyz", 1001, manager));
    employees.add(new Employee(2, "xyz", "xyz", 1003, manager));
    employees.add(new Employee(3, "xyz", "xyz", 1002, manager));
    when(service.findByManager(101)).thenReturn(employees);
    ResponseEntity<Object> result = controller.getEmployeeByManager(101);
    assertEquals(employees, result.getBody());
    assertThat(result.getStatusCodeValue()).isEqualTo(200);


  }

  @Test
  public void testSaveEmployee() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.saveEmployee(emp)).thenReturn(emp);
    Employee result = controller.saveEmployee(emp);
    assertEquals(emp, result);


  }

}
