package com.sbprogramming.SpringBootMySQLEx.repository;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT e FROM Employee e WHERE e.id = :departmentId")
    List<Employee> getEmployeesByDepartment(@Param("departmentId") Long departmentId);
    //List<Employee> getEmployeesByDepartment(Long id);
    Department findDepartmentByName(String name);

}

