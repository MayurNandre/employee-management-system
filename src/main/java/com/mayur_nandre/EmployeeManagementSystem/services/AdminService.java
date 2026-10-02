package com.mayur_nandre.EmployeeManagementSystem.services;

import com.mayur_nandre.EmployeeManagementSystem.model.Admin;
import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import com.mayur_nandre.EmployeeManagementSystem.model.Leave;
import com.mayur_nandre.EmployeeManagementSystem.model.Manager;

import java.util.List;

public interface AdminService {
    public Admin checkadminlogin(String username, String password);

    public Manager addManager(Manager manager);
    public List<Manager> viewAllManagers();
    public String deleteManager(Long mid);
    public List<Employee> viewAllEmployees();
    public String deleteEmployee(Long eid);
    public long managercount();
    public long employeecount();

    public List<Leave> viewAllLeaveApplications();
}
