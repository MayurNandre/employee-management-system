package com.mayur_nandre.EmployeeManagementSystem.services;

import com.mayur_nandre.EmployeeManagementSystem.model.*;
import com.mayur_nandre.EmployeeManagementSystem.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private ManagerRepository managerRepository;
    private EmailRepository emailRepository;
    private EmailService emailService;
    private EmployeeRepository employeeRepository;
    private LeaveRepository leaveRepository;

    public AdminServiceImpl(AdminRepository adminRepository, ManagerRepository managerRepository,
                            EmailRepository emailRepository, EmailService emailService,
                            EmployeeRepository employeeRepository, LeaveRepository leaveRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.emailRepository = emailRepository;
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
    }

    @Override
    public Admin checkadminlogin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Manager addManager(Manager manager) {
        Long manager_id = generateRandomManagerId();
        String randomPassword = generateRandomPassword(8);

        manager.setId(manager_id);
        manager.setPassword(randomPassword);
        Manager savedManager = managerRepository.save(manager);

        Email email = new Email();
        email.setRecipient(manager.getEmail());
        email.setSubject("Welcome Manager to EMS!");
        email.setMessage("Hi " + manager.getName() + ", \n\nYou have been successfully added. \n\nManager ID: " + manager.getId() + "\nUsername: " + manager.getUsername() + "\nPassword: " + manager.getPassword());
        emailRepository.save(email);
        emailService.sendEmail(email.getRecipient(),email.getSubject(),email.getMessage());

        return savedManager;
    }

    @Override
    public List<Manager> viewAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public String deleteManager(Long mid) {
        Optional<Manager> manager = managerRepository.findById(mid);
        if (manager.isPresent()) {
            managerRepository.deleteById(mid);
            return "Manager Deleted Successfully.";
        }
        return "Manager ID not Found";
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String deleteEmployee(Long eid) {
        Optional<Employee> employee = employeeRepository.findById(eid);
        if (employee.isPresent()) {
            employeeRepository.deleteById(eid);
            return "Employeee Deleted Successfully.";
        }
            return "Employee ID not Found !";
    }


    @Override
    public long managercount() {
        return managerRepository.count();
    }

    @Override
    public long employeecount() {
        return employeeRepository.count();
    }

    @Override
    public List<Leave> viewAllLeaveApplications() {
        return leaveRepository.findAll();
    }

    private long generateRandomManagerId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    private String generateRandomPassword(int length) {
        String upper = "ABCDEFHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "~!@#$%^&*";
        String combined = upper + lower + digits + special;

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        sb.append(upper.charAt(random.nextInt(upper.length())));
        sb.append(lower.charAt(random.nextInt(lower.length())));
        sb.append(digits.charAt(random.nextInt(digits.length())));
        sb.append(special.charAt(random.nextInt(special.length())));

        for(int i = 4 ; i < length ; i++) {
            sb.append(combined.charAt(random.nextInt(combined.length())));
        }

        return sb.toString();
    }
}
