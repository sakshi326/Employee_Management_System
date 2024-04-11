package com.sbprogramming.SpringBootMySQLEx.repository;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //List<Employee> findByIsActive(boolean isActive);
}
