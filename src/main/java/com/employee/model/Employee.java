package com.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Entity
@Table(name = "employee")
@ApiModel(value = "All details about Customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "emp_id")
  @ApiModelProperty(value = "Auto generated Id")
  private int id;

  @Size(min = 2, max = 30)
  @ApiModelProperty(
      value = "Employee name should minimum contains 2 char and maximum 30 characters",
      allowableValues = "string")
  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @ApiModelProperty(value = "Employee last name")
  private String lastName;


  @Column(name = "emp_no")
  @NotNull
  @ApiModelProperty(value = "Employee Number", required = true)
  private int emp_no;


  @ManyToOne
  @JoinColumn(name = "mgr_id", nullable = false)
  @JsonIgnoreProperties("employees")
  private Manager manager;

  // get id of employee
  public int getId() {
    return id;
  }

  // set id of employee
  public void setId(int id) {
    this.id = id;
  }

  // get name of employee
  public String getFirstName() {
    return firstName;
  }

  // set name of employee
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  // get last name of employee
  public String getLastName() {
    return lastName;
  }

  // set last name of employee
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // get employee number
  public int getEmp_no() {
    return emp_no;
  }

  // set employee number
  public void setEmp_no(int emp_no) {
    this.emp_no = emp_no;
  }

  // manager associated with employee
  public Manager getManager() {
    return manager;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }


  public Employee(int id, @Size(min = 2, max = 30) String firstName, String lastName,
                  @NotEmpty int emp_no, Manager manager) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emp_no = emp_no;
    this.manager = manager;
  }

  public Employee() {
    // TODO Auto-generated constructor stub
  }



}
