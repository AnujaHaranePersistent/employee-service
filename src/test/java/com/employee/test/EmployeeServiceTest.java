package com.employee.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.model.Employee;
import com.employee.model.Manager;
import com.employee.repositary.EmployeeRepositary;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepositary repositary;

  @InjectMocks
  private EmployeeService service = new EmployeeServiceImpl();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllEmployees() {
    Manager manager = new Manager(101, "abc", "abc");
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "xyz", "xyz", 1001, manager));
    employees.add(new Employee(2, "xyz", "xyz", 1003, manager));
    employees.add(new Employee(3, "xyz", "xyz", 1002, manager));
    when(repositary.findAll()).thenReturn(employees);

    List<Employee> result = service.getAllEmployees();
    assertEquals(3, result.size());
  }

  @Test
  public void testGetEmployeeById() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(repositary.findById(1)).thenReturn(emp);
    Employee result = service.getEmployeeById(1);
    assertEquals(1, result.getId());
    assertEquals(1001, result.getEmp_no());

  }

  @Test
  public void testGetEmployeeById_IfNotFound() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(repositary.findById(2)).thenReturn(null);
    Employee result = service.getEmployeeById(2);
    assertEquals(null, result);

  }


  @Test
  public void testSaveEmployee() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(repositary.save(emp)).thenReturn(emp);
    Employee result = service.saveEmployee(emp);
    assertEquals(1, result.getId());
    assertEquals(1001, result.getEmp_no());
    assertEquals("xyz", result.getFirstName());
    assertEquals("xyz", result.getLastName());
    assertEquals(manager, result.getManager());
  }

  @Test
  public void testUpdateEmployee() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    Manager man = new Manager(102, "abc", "abc");
    emp.setFirstName("abc");
    emp.setLastName("abc");
    emp.setEmp_no(1002);
    emp.setManager(man);
    when(repositary.save(emp)).thenReturn(emp);
    Employee result = service.updateEmployee(emp);
    assertEquals(1, result.getId());
    assertEquals(1002, result.getEmp_no());
    assertEquals("abc", result.getFirstName());
    assertEquals("abc", result.getLastName());
    assertEquals(man, result.getManager());
  }

  @Test
  public void testDeleteEmployee() {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    // when(repositary.findById(1)).thenReturn(emp);
    service.deleteEmployee(emp);
    verify(repositary, times(1)).delete(emp);
  }


  @Test
  public void testGetEmployeesByManager() {
    Manager manager = new Manager(101, "abc", "abc");
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "xyz", "xyz", 1001, manager));
    employees.add(new Employee(2, "xyz", "xyz", 1003, manager));
    employees.add(new Employee(3, "xyz", "xyz", 1002, manager));
    when(repositary.findAllByManager_Id(101)).thenReturn(employees);
    List<Employee> result = service.findByManager(101);
    assertEquals(3, result.size());
  }

}
