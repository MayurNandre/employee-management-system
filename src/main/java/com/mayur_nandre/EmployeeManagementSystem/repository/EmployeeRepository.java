package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import com.mayur_nandre.EmployeeManagementSystem.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public List<Employee> findByNameContainingIgnoreCase(String name);

    public Employee findByUsernameAndPassword(String username, String password);

    public Employee findByUsername(String username);
    public Optional<Employee> findByEmail(String email);

    public Optional<Employee> FindByEmail(String email);
}
