package com.sbprogramming.SpringBootMySQLEx.controller;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;
import com.sbprogramming.SpringBootMySQLEx.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> findAll(){
        return departmentService.findAll();
    }
    @GetMapping("/departments/{id}")
    public Optional<Department> findById(@PathVariable("id") Long id){
        return departmentService.findById(id);
    }
    @GetMapping("/departments/{id/employee}")
    public List<Employee> getEmployeesByDepartment(@PathVariable("id") Long id){
        return departmentService.getEmployeeByDepartment(id);
    }

}
