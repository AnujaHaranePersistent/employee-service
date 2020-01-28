package com.employee.repository;

import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {
    public Employee findById(int emp_id);
    public List<Employee> findAllByManager_Id(int mgrId);

}
