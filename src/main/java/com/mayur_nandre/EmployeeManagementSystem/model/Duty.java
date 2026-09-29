package com.mayur_nandre.EmployeeManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name="duty_table")
public class Duty {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false,length=3000)
    private String description;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "assingedByManager")
    private Manager assingedByManager;

    @ManyToOne
    @JoinColumn(name = "assignedByAdmin")
    private Admin assingedByAdmin;
}
