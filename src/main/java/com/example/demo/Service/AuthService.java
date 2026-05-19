package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthRepository repository;

    // REGISTER USER
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

        // SAVE USER IN DATABASE
        repository.register(user);

        return "Account Created";

    }

    // LOGIN
    public User login(
            String email
    ) {

        return repository.login(email);

    }

}