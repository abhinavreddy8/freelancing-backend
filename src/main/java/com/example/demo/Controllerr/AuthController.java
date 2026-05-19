package com.example.demo.Controllerr;

import com.example.demo.Models.User;
import com.example.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    AuthService service;

    // REGISTER USER
    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ) {

        return service.sendVerification(
                user
        );

    }

    // LOGIN
    @PostMapping("/login")
    public User login(
            @RequestParam String email
    ) {

        return service.login(email);

    }

}