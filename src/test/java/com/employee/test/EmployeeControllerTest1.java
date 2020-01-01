package com.employee.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.employee.controller.EmployeeController;
import com.employee.model.Employee;
import com.employee.model.Manager;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest1 {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeService service;;

  @Test
  public void testCreateEmployee() throws Exception {

    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    when(service.saveEmployee(emp)).thenReturn(emp);


    String inputInJson = this.mapToJson(emp);

    String URI = "/api/employee";

    Mockito.when(service.saveEmployee(Mockito.any(Employee.class))).thenReturn(emp);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertThat(outputInJson).isEqualTo(inputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);

    Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(emp);

    String URI = "/api/employee/1";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String expectedJson = this.mapToJson(emp);
    String outputInJson = result.getResponse().getContentAsString();
    assertThat(outputInJson).isEqualTo(expectedJson);
  }

  @Test
  public void testGetAllEmployees() throws Exception {

    Manager manager = new Manager(101, "abc", "abc");
    List<Employee> employees = new ArrayList<Employee>();
    employees.add(new Employee(1, "xyz", "xyz", 1001, manager));
    employees.add(new Employee(2, "xyz", "xyz", 1003, manager));
    employees.add(new Employee(3, "xyz", "xyz", 1002, manager));

    Mockito.when(service.getAllEmployees()).thenReturn(employees);

    String URI = "/api/employee/";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    String expectedJson = this.mapToJson(employees);
    String outputInJson = result.getResponse().getContentAsString();
    assertThat(outputInJson).isEqualTo(expectedJson);
  }

  @Test
  public void testUpdateEmployee() throws Exception {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);

    String expectedJson = this.mapToJson(emp);

    Mockito.when(service.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);

    String inputInJson = this.mapToJson(emp);
    String URI = "/api/employee/1";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String outputInJson = result.getResponse().getContentAsString();
    assertThat(outputInJson).isEqualTo(expectedJson);

  }

  @Test
  public void testDeleteEmployeeById() throws Exception {
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);

    doNothing().when(service).deleteEmployee(emp);


    String URI = "/api/employee/1";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();


    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  /**
   * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
   */
  private String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }
}
