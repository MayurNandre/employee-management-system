package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Admin findByUsernameAndPassword(String username, String password);
}
