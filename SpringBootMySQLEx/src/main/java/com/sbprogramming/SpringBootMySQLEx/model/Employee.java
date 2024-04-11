package com.sbprogramming.SpringBootMySQLEx.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sbprogramming.SpringBootMySQLEx.model.form.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "emp_name"
    )
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(
            name = "email"
    )
    @NotBlank(message = "Email is mandatory")
    private String email;
    @JsonManagedReference
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeDetail detail;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}


