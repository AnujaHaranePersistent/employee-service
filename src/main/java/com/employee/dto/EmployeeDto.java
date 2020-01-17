package com.employee.dto;

import com.employee.model.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

  private int id;
  private String firstName;
  private String lastName;
  private int emp_no;
  private Manager manager;

}
