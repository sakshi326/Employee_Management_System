package com.sbprogramming.SpringBootMySQLEx.service;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> findAll();

    Optional<Department> findById(Long id);

    List<Employee> getEmployeeByDepartment(Long id);

    Department createDepartment(Department department);

    Department findDepartmentByName(String name);

    List<Department> getAllDepartments();
}


