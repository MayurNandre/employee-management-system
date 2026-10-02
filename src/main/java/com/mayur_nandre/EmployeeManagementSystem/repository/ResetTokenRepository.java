package com.mayur_nandre.EmployeeManagementSystem.repository;

import com.mayur_nandre.EmployeeManagementSystem.model.ResetToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResetTokenRepository extends CrudRepository<ResetToken, Long> {

    @Query("select rt from ResetToken rt where rt.token=?1")
    public ResetToken FindByToken(String token);

    public Optional<ResetToken> findByToken(String token);
    public Optional<ResetToken> findByEmail(String email);
    public void deleteByToken(String token);
}
