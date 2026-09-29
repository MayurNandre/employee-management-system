package com.mayur_nandre.EmployeeManagementSystem.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="manager_table")
public class Manager {
    @Id
    @Column(name = "manager_id")
    private Long id;

    @Column(name="manager_name",nullable = false)
    private String name;

    @Column(name="manager_username",nullable = false,unique=true)
    private String username;

    @Column(name="manager_email",nullable = false,unique = true)
    private String email;

    @Column(name="manager_password",nullable = false)
    private String password;

    @Column(name="manager_dept",nullable = false)
    private String department;

    @Column(name="manager_contact",nullable = false,unique=true)
    private String contact;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "assingedByManager",cascade = CascadeType.ALL)
    private List<Duty> dutiesAssinged;

}
