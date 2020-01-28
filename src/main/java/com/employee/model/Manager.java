package com.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Set;

@Entity
@Table(name = "manager")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(value = "All details about Manager")
public class Manager {



  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mgr_id")
  @ApiModelProperty(value = "Auto generated Id")
  private int id;


  @Column(name = "first_name")
  @ApiModelProperty(value = "First name of Manager", required = false)
  private String firstName;

  @Column(name = "last_name")
  @ApiModelProperty(value = "Last name of manager", required = false)
  private String lastName;

  @OneToMany(mappedBy = "manager")
  @ApiModelProperty(hidden = true)
  private Set<Employee> employees;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public Manager() {
    // TODO Auto-generated constructor stub
  }

  public Manager(int id, String firstName, String lastName, Set<Employee> employees) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.employees = employees;
  }

  public Manager(int id, @Null String firstName, @Null String lastName) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }



}
