package com.mayur_nandre.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employee_table")
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "emp_id")
    private Long id;

    @Column(name="emp_name",nullable = false)
    private String name;

    @Column(name="emp_gender",nullable = false)
    private String gender;

    @Column(name="emp_age",nullable = false)
    private int age;

    @Column(name="emp_designation",nullable = false)
    private String designation;

    @Column(name="emp_dept",nullable = false)
    private String department;

    @Column(name="emp_salary",nullable = false)
    private double salary;

    @Column(name="emp_username",nullable = false,unique = true)
    private String username;

    @Column(name="emp_email",nullable = false,unique=true)
    private String email;

    @Column(name="emp_password",nullable = false)
    private String password;

    @Column(name="emp_contact",nullable = false,unique = true)
    private String contact;

    @Column(name="acc_status", nullable = false)
    private String accountstatus;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false, name="emp_photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Leave> leave;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Duty> duty;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
