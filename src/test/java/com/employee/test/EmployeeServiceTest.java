package com.employee.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.exception.ResourceNotFound;
import com.employee.model.Employee;
import com.employee.model.Manager;
import com.employee.repositary.EmployeeRepositary;
import com.employee.service.EmployeeService;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {
	
	@MockBean
	private EmployeeRepositary repositary;
	
	@Autowired
	private EmployeeService service;
	
	
	
	@Test
	public void testGetAllEmployees(){
		Manager manager=new Manager(101, "abc", "abc");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1,"xyz","xyz",1001, manager));
		employees.add(new Employee(2,"xyz","xyz",1003, manager));
		employees.add(new Employee(3,"xyz","xyz",1002, manager));
		when(repositary.findAll()).thenReturn(employees);
		
		List<Employee> result = service.getAllEmployees();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetEmployeeById(){
		Manager manager=new Manager(101, "abc", "abc");
		Employee emp=new Employee(1,"xyz","xyz",1001, manager);
		when(repositary.findById(1)).thenReturn(emp);
		Employee result = service.getEmployeeById(1);
		assertEquals(1, result.getId());
		assertEquals(1001, result.getEmp_no());
		
	}
	@Test
	public void testGetEmployeeById_IfNotFound(){
		Manager manager=new Manager(101, "abc", "abc");
		Employee emp=new Employee(1,"xyz","xyz",1001, manager);
		when(repositary.findById(2)).thenReturn(null);
		Employee result = service.getEmployeeById(2);
		assertEquals(null, result);
	
	}
	
	
	@Test
	public void testSaveEmployee(){
		Manager manager=new Manager(101, "abc", "abc");
		Employee emp=new Employee(1,"xyz","xyz",1001, manager);
		when(repositary.save(emp)).thenReturn(emp);
		Employee result = service.saveEmployee(emp);
		assertEquals(1, result.getId());
		assertEquals(1001, result.getEmp_no());
		assertEquals("xyz", result.getFirstName());
		assertEquals("xyz", result.getLastName());
		assertEquals(manager, result.getManager());
	}
	
	@Test
	public void testDeleteEmployee(){
		Manager manager=new Manager(101, "abc", "abc");
		Employee emp=new Employee(1,"xyz","xyz",1001, manager);
		when(repositary.findById(1)).thenReturn(emp);
		service.deleteEmployee(emp);
        verify(repositary, times(1)).delete(emp);
	}
	
	
	@Test
	public void testGetEmployeesByManager(){
		Manager manager=new Manager(101, "abc", "abc");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1,"xyz","xyz",1001, manager));
		employees.add(new Employee(2,"xyz","xyz",1003, manager));
		employees.add(new Employee(3,"xyz","xyz",1002, manager));
		when(repositary.findAllByManager_Id(101)).thenReturn(employees);
		List<Employee> result = service.findByManager(101);
		assertEquals(3, result.size());
		}
	
}
