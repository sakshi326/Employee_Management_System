package com.sbprogramming.SpringBootMySQLEx.controller;

import com.sbprogramming.SpringBootMySQLEx.model.Employee;
import com.sbprogramming.SpringBootMySQLEx.model.EmployeeDetail;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;
import com.sbprogramming.SpringBootMySQLEx.model.form.EmployeeForm;
import com.sbprogramming.SpringBootMySQLEx.service.DepartmentService;
import com.sbprogramming.SpringBootMySQLEx.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model, String departmentName) {
        EmployeeForm employee = new EmployeeForm(departmentName);
        model.addAttribute("employee", employee);

        List<Department> departmentNames = departmentService.getAllDepartments();
        model.addAttribute("departmentNames", departmentNames);

        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeForm form, BindingResult bindingResult) {
        Employee employee= new Employee();
        employee.setEmail(form.getEmail());
        employee.setName(form.getName());

        EmployeeDetail detail=new EmployeeDetail();
        detail.setAddress(form.getAddress());
        detail.setEmployee(employee);

        employeeService.saveEmployeeDetail(detail);
        return "redirect:/";
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "update_employee";
        }
        Employee employee = employeeService.getEmployeeById(Long.parseLong(employeeForm.getId()));
        employee.setName(employeeForm.getName());
        employee.setEmail(employeeForm.getEmail());

        Department department = departmentService.findDepartmentByName(employeeForm.getDepartmentName());
        if (department != null) {
            employee.setDepartment(department);
        }

        EmployeeDetail employeeDetail = employeeService.findByEmployee(employee);

        if(employeeDetail == null){
            employeeDetail = new EmployeeDetail();
            employeeDetail.setEmployee(employee);
        }

        employeeDetail.setAddress(employeeForm.getAddress());
        employeeService.saveEmployeeDetail(employeeDetail);

        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        EmployeeDetail employeeDetail = employeeService.findByEmployee(employee);
        if(Objects.isNull(employeeDetail)) {
            employeeDetail = new EmployeeDetail();
        }
        model.addAttribute("employeeDetail", employeeDetail);

        List<Department> departmentNames = departmentService.getAllDepartments();
        model.addAttribute("departmentNames", departmentNames);

        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {

        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page< Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}