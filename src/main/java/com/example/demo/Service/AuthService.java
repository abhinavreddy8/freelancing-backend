package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthRepository repository;

    @Autowired
    MailService mailService;

    // SEND VERIFICATION MAIL
    public String sendVerification(
            User user
    ) {

        Integer exists =
                repository.checkEmailExists(
                        user.getEmail()
                );

        // EMAIL ALREADY EXISTS
        if (exists > 0) {

            return "Email already exists";
        }

        // NGROK VERIFICATION LINK
        String verificationLink =

                "https://temp.onrender.com"
                        + "name=" + user.getName()
                        + "&email=" + user.getEmail()
                        + "&role=" + user.getRole();

        // SEND MAIL
        mailService.sendVerificationMail(

                user.getEmail(),

                verificationLink

        );

        return "Verification Mail Sent";

    }

    // VERIFY USER AND REGISTER
    public User verifyAndRegister(

            String name,

            String email,

            String role

    ) {

        User user = new User();

        user.setName(name);

        user.setEmail(email);

        user.setRole(role);

        return repository.register(user);

    }

    // LOGIN
    public User login(
            String email
    ) {

        return repository.login(email);

    }

}