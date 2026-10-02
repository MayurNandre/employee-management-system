package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {
    public List<Leave> findByEmployeeId(Long eid);
}
