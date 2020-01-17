package com.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@ApiModel(value = "All details about Employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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



}
