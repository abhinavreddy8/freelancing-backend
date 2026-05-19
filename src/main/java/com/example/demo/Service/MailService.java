package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendVerificationMail(
            String toEmail,
            String link
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Verify TalentNest Account"
        );

        message.setText(
                "Click below link to verify your account:\n\n"
                        + link
        );

        mailSender.send(message);

    }

}