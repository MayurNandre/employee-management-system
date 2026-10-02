package com.mayur_nandre.EmployeeManagementSystem.services;

import com.mayur_nandre.EmployeeManagementSystem.model.Email;
import com.mayur_nandre.EmployeeManagementSystem.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        Email email = new Email();

        email.setRecipient(to);
        email.setSubject(subject);
        email.setMessage(body);
        email.setSentAt(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            email.setStatus("SUCCESS");
        }
        catch(Exception ex) {
            email.setStatus("FAILURE");
            System.out.println(ex.getMessage());
        }
        emailRepository.save(email);
    }

    public void sendResetLink(String toEmail, String resetLink) {
        String subject = "Password Reset Request";
        String body = "Hello,\n\n"
                + "We received a request to reset your password.\n\n"
                + "Click the link below to reset your password:\n"
                + resetLink
                + "\n\n"
                + "If you did not request a password reset, please ignore this email.";

        sendEmail(toEmail, subject, body);
    }
}
