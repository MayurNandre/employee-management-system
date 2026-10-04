package com.mayur_nandre.EmployeeManagementSystem.services;

import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import com.mayur_nandre.EmployeeManagementSystem.model.Leave;
import com.mayur_nandre.EmployeeManagementSystem.model.Manager;
import com.mayur_nandre.EmployeeManagementSystem.repository.EmployeeRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.LeaveRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    private EmployeeRepository employeeRepository;
    private LeaveRepository leaveRepository;
    private ManagerRepository managerRepository;


    public LeaveServiceImpl(EmployeeRepository employeeRepository,
                            LeaveRepository leaveRepository,
                            ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public Leave applyLeaveByEmployee(Leave leave, Long empid) {
        Employee emp = employeeRepository.findById(empid).orElse(null);
        if(emp != null) {
            leave.setEmployee(emp);
            leave.setStatus("PENDING");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeavesByEmployee(Long empid) {
        return leaveRepository.findByEmployeeId(empid);
    }

    @Override
    public List<Leave> viewAllPendingLeaves() {
        return leaveRepository.findByStatus("PENDING");
    }

    @Override
    public Leave applyLeaveByManager(Leave leave, Long managerid) {
        Manager manager = managerRepository.findById(managerid).orElse(null);
        if(manager != null) {
            leave.setManager(manager);
            leave.setStatus("PENDING");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeavesByManager(Long managerid) {
        return leaveRepository.findByManagerId(managerid);
    }

    @Override
    public String updateLeaveStatus(int leaveid, String status) {
        Leave leave = leaveRepository.findById(leaveid).orElse(null);
        if (leave != null) {
            leave.setStatus(status.toUpperCase());
            return "Leave Status Updated to: " + status;
        }
        return "Leave ID not found.";
    }
}