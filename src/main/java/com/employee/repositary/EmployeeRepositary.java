package com.employee.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {

public Employee findById(int emp_id);
public List<Employee> findAllByManager_Id(int mgrId);
}
