package com.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="employee")
@ApiModel(value = "All details about Customer")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	@ApiModelProperty(value = "Auto generated Id")
	private int id;
	
	@Size(min = 2, max = 30)
	@ApiModelProperty(value = "Employee name should minimum contains 2 char and maximum 30 characters", allowableValues = "string")
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	@ApiModelProperty(value = "Employee last name")
	private String lastName;
	
	
	@Column(name="emp_no")
	@NotNull
	@ApiModelProperty(value = "Employee Number",required=true)
	private int emp_no;
	
	
	@ManyToOne
	@JoinColumn(name="mgr_id", nullable=false)
	@JsonIgnoreProperties("employees")
	private Manager manager;
	
	
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
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

	public Employee(int id, @Size(min = 2, max = 30) String firstName, String lastName, @NotEmpty int emp_no,
			Manager manager) {
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
