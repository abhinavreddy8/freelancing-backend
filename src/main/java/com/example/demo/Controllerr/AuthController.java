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

    // SEND MAIL
    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ) {

        return service.sendVerification(
                user
        );

    }

    // CLICK MAIL LINK
    @GetMapping("/verify")
    public String verify(

            @RequestParam String name,

            @RequestParam String email,

            @RequestParam String role

    ) {

        service.verifyAndRegister(
                name,
                email,
                role
        );

        return "Account Verified Successfully";

    }

    // LOGIN
    @PostMapping("/login")
    public User login(
            @RequestParam String email
    ) {

        return service.login(email);

    }

}