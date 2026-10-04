package com.mayur_nandre.EmployeeManagementSystem.services;

import com.mayur_nandre.EmployeeManagementSystem.model.Admin;
import com.mayur_nandre.EmployeeManagementSystem.model.Duty;
import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import com.mayur_nandre.EmployeeManagementSystem.model.Manager;
import com.mayur_nandre.EmployeeManagementSystem.repository.AdminRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.DutyRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.EmployeeRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {

    private EmployeeRepository employeeRepository;
    private AdminRepository adminRepository;
    private DutyRepository dutyRepository;
    private ManagerRepository managerRepository;

    public DutyServiceImpl(EmployeeRepository employeeRepository, AdminRepository adminRepository,
                           DutyRepository dutyRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public Duty assignDutyByAdminToEmployee(Duty duty, Long empid, int adminid) {
        Employee emp = employeeRepository.findById(empid).orElse(null);
        Admin admin = adminRepository.findById(adminid).orElse(null);
        if (emp != null && admin != null) {
            duty.setEmployee(emp);
            duty.setAssingedByAdmin(admin);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByAdminToManager(Duty duty, Long managerid, int adminid) {
        Manager manager = managerRepository.findById(managerid).orElse(null);
        Admin admin = adminRepository.findById(adminid).orElse(null);
        if (manager != null && admin != null) {
            duty.setEmployee(null);
            duty.setManager(manager);
            duty.setAssingedByAdmin(admin);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByManagerToEmployee(Duty duty, Long empid, Long managerid) {
        Employee emp = employeeRepository.findById(empid).orElse(null);
        Manager manager = managerRepository.findById(managerid).orElse(null);
        if (emp != null && manager != null) {
            duty.setEmployee(emp);
            duty.setAssingedByManager(manager);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public List<Duty> viewAllDutiesofEmployee(Long eid) {
        return dutyRepository.findByEmployeeId(eid);
    }

    @Override
    public List<Duty> viewDutiesAssignedByManager(Long managerid) {
        return dutyRepository.findByAssingedByManager(managerid);
    }

    @Override
    public List<Duty> viewDutiesAssignedByAdmin(int adminid) {
        return dutyRepository.findByAssingedByAdmin(adminid);
    }
}
