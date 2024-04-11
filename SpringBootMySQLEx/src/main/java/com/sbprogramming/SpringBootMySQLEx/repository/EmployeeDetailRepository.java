package com.sbprogramming.SpringBootMySQLEx.repository;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailRepository extends JpaRepository <EmployeeDetail,Long>{
    EmployeeDetail findByEmployee(Employee employee);
}
