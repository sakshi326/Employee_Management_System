package com.sbprogramming.SpringBootMySQLEx.service;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.EmployeeDetail;
import com.sbprogramming.SpringBootMySQLEx.model.form.EmployeeForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    void saveEmployeeDetail(EmployeeDetail employee);

    Employee saveEmployee(EmployeeForm form);

    Employee updateEmployee(EmployeeForm employeeForm);

    Employee getEmployeeById(Long id);

    void saveEmployee(EmployeeDetail employee);


    void deleteEmployeeById(Long id);
    Page< Employee > findPaginated(int pageNo, String sortField, String sortDirection, int pageSize);

    Page< Employee > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    EmployeeDetail findByEmployee(Employee employee);



}
