package com.example.demo.Controllerr;

import com.example.demo.Models.FreelancerProfile;
import com.example.demo.Service.FreelancerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer-profile")
@CrossOrigin("*")
public class FreelancerProfileController {

    @Autowired
    private FreelancerProfileService service;

    @PostMapping
    public String createProfile(
            @RequestBody FreelancerProfile profile
    ) {

        service.createProfile(profile);

        return "Freelancer Profile Created";

    }

    @GetMapping("/{userId}")
    public FreelancerProfile getProfile(
            @PathVariable Integer userId
    ) {

        return service.getProfile(userId);

    }
    @PutMapping
    public String updateProfile(
            @RequestBody FreelancerProfile profile
    ){

        service.updateProfile(profile);

        return "Profile Updated";
    }

}