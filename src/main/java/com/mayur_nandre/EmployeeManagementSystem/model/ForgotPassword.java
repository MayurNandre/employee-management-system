package com.mayur_nandre.EmployeeManagementSystem.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPassword {
    private String email;
    private String newPassword;

    @Override
    public String toString() {
        return "ForgotPassword [email=" + email + ", newPassword=" + newPassword + "]";
    }
}
