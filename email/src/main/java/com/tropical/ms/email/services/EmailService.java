package com.tropical.ms.email.services;

import com.tropical.ms.email.enums.StatusEmail;
import com.tropical.ms.email.models.Email;
import com.tropical.ms.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender emailSender;
    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email){
        try {
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message=new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.Sent);
        }catch (MailException e){
            email.setStatusEmail(StatusEmail.Error);
        }finally {
            return emailRepository.save(email);
        }
    }

}
