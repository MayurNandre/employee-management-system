package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.Duty;
import com.mayur_nandre.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyRepository extends JpaRepository<Duty,Integer> {
    public List<Duty> findByEmployee(Employee employee);
    public List<Duty> findByEmployeeId(Long id);
    public List<Duty> findByAssingedByManager(Long managerid);
    public List<Duty> findByAssingedByAdmin(int adminid);
}
