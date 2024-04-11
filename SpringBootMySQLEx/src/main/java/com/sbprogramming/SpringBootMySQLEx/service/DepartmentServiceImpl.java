package com.sbprogramming.SpringBootMySQLEx.service;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;
import com.sbprogramming.SpringBootMySQLEx.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll(){
        return (List<Department>) departmentRepository.findAll();
    }
    public Optional<Department> findById(Long id){
        return departmentRepository.findById(id);
    }
    public List<Employee> getEmployeeByDepartment(Long id){
        return departmentRepository.getEmployeesByDepartment(id);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByName(departmentName);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}


