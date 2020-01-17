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
import org.mockito.Mockito;
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
    // Manager manager = Mockito.mock(Manager.class);
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    when(service.getAllEmployees()).thenReturn(employees);

    List<Employee> result = controller.getAllEmployees();
    assertEquals(3, result.size());
  }

  @Test
  public void testGetEmployeeById() {
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
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


  @Test
  public void testGetEmployeeById_IfNotFound() {
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
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
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
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
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
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
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
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
    // Manager manager = Mockito.mock(Manager.class);
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    when(service.findByManager(101)).thenReturn(employees);
    ResponseEntity<Object> result = controller.getEmployeeByManager(101);
    assertEquals(employees, result.getBody());
    assertThat(result.getStatusCodeValue()).isEqualTo(200);


  }

  @Test
  public void testSaveEmployee() {
    // Manager manager = Mockito.mock(Manager.class);
    Employee emp = Mockito.mock(Employee.class);
    when(service.saveEmployee(emp)).thenReturn(emp);
    Employee result = controller.saveEmployee(emp);
    assertEquals(emp, result);


  }

}
