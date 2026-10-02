package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyRepository extends JpaRepository<Duty,Integer> {
}
