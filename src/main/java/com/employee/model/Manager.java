package com.employee.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manager")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(value = "All details about Manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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



}
