package com.mayur_nandre.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "otp_details")
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique=true)
    private String token;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Override
    public String toString() {
        return "ResetToken [id=" + id + ", token=" + token + ", email=" + email + ", createdAt=" + createdAt
                + ", expiresAt=" + expiresAt + "]";
    }
}
