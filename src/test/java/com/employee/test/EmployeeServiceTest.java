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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepositary;
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
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    when(repositary.findAll()).thenReturn(employees);

    List<Employee> result = service.getAllEmployees();
    assertEquals(3, result.size());
  }

  @Test
  public void testGetEmployeeById() {
    Employee emp = Mockito.mock(Employee.class);
    when(repositary.findById(1)).thenReturn(emp);
    Employee result = service.getEmployeeById(1);
    assertEquals(result, emp);


  }

  @Test
  public void testGetEmployeeById_IfNotFound() {
    Employee emp = Mockito.mock(Employee.class);
    when(repositary.findById(2)).thenReturn(null);
    Employee result = service.getEmployeeById(2);
    assertEquals(null, result);

  }


  @Test
  public void testSaveEmployee() {
    // Manager manager = new Manager(101, "abc", "abc");
    Employee emp = Mockito.mock(Employee.class);
    when(repositary.save(emp)).thenReturn(emp);
    Employee result = service.saveEmployee(emp);
    assertEquals(result, emp);
  }

  @Test
  public void testUpdateEmployee() {
    Employee emp = Mockito.mock(Employee.class);
    when(repositary.save(emp)).thenReturn(emp);
    Employee result = service.updateEmployee(emp);
    assertEquals(result, emp);

  }

  @Test
  public void testDeleteEmployee() {
    Employee emp = Mockito.mock(Employee.class);
    // when(repositary.findById(1)).thenReturn(emp);
    service.deleteEmployee(emp);
    verify(repositary, times(1)).delete(emp);
  }


  @Test
  public void testGetEmployeesByManager() {
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    employees.add(Mockito.mock(Employee.class));
    when(repositary.findAllByManager_Id(101)).thenReturn(employees);
    List<Employee> result = service.findByManager(101);
    assertEquals(3, result.size());
  }

}
