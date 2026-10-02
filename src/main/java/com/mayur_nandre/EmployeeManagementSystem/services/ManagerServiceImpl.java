package com.mayur_nandre.EmployeeManagementSystem.services;


import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import com.mayur_nandre.EmployeeManagementSystem.model.Manager;
import com.mayur_nandre.EmployeeManagementSystem.model.ResetToken;
import com.mayur_nandre.EmployeeManagementSystem.repository.EmployeeRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.ManagerRepository;
import com.mayur_nandre.EmployeeManagementSystem.repository.ResetTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService{

    private ManagerRepository managerRepository;
    private EmployeeRepository employeeRepository;
    private ResetTokenRepository resetTokenRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository, EmployeeRepository employeeRepository,
                              ResetTokenRepository resetTokenRepository) {
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
        this.resetTokenRepository = resetTokenRepository;
    }

    @Override
    public Manager checkmanagerlogin(String username, String password) {
        return managerRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Manager findManagerById(Long id) {
        return managerRepository.findById(id).get();
    }

    @Override
    public Manager findManagerByUsername(String username) {
        return managerRepository.findByUsername(username);
    }

    @Override
    public Manager findManagerByEmail(String email) {
        return managerRepository.findByEmail(email);
    }

    @Override
    public List<Manager> viewAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String updateEmployeeAccountStatus(Long employeeid, String status) {

        Optional<Employee> emp = employeeRepository.findById(employeeid);

        if (emp.isPresent()) {
            Employee e = emp.get();

            e.setAccountstatus(status);

            employeeRepository.save(e);

            return "Employee Account Status Updated Successfully";
        } else {
            return "Employee ID Not Found";
        }
    }

    @Override
    public String generateResetToken(String email) {
        Optional<Manager> manager = managerRepository.FindByEmail(email);
        if(manager.isPresent()) {
            String token = UUID.randomUUID().toString();

            ResetToken rt = new ResetToken();
            rt.setToken(token);
            rt.setEmail(email);
            rt.setCreatedAt(LocalDateTime.now());
            rt.setExpiresAt(LocalDateTime.now().plusMinutes(10)); // 5mins

            resetTokenRepository.save(rt);
            return token;
        }
        return null;
    }

    @Override
    public boolean validateResetToken(String token) {
        Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
        return rt.isPresent() && !isTokenExpired(token);
    }

    @Override
    public boolean changePassword(Manager manager, String oldPassword, String newPassword) {
        if(manager.getPassword().equals(oldPassword)) {
            manager.setPassword(newPassword);
            managerRepository.save(manager);
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {

        Optional<ResetToken> resetToken =
                resetTokenRepository.findByToken(token);

        if (resetToken.isPresent() && !isTokenExpired(token)) {
            String email = resetToken.get().getEmail();
            Optional<Manager> manager =
                    managerRepository.FindByEmail(email);

            if (manager.isPresent()) {
                Manager m = manager.get();
                m.setPassword(newPassword);
                managerRepository.save(m);
                deleteResetToken(token);
            }
        }
    }


    @Override
    public void deleteResetToken(String token) {
        resetTokenRepository.deleteByToken(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
        if(rt.isPresent()) {
            return rt.get().getExpiresAt().isBefore(LocalDateTime.now());
        }
        return true;
    }
}
