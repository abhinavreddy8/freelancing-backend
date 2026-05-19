package com.example.demo.Controllerr;

import com.example.demo.Models.ClientProfile;
import com.example.demo.Service.ClientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotationAutowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-profile")
@CrossOrigin("*")
public class ClientProfileController {

    @Autowired
    private ClientProfileService service;

    @PostMapping
    public String createProfile(
            @RequestBody ClientProfile profile
    ) {

        service.createProfile(profile);

        return "Client Profile Created";

    }

    @GetMapping("/{userId}")
    public ClientProfile getProfile(
            @PathVariable Integer userId
    ) {

        return service.getProfile(userId);

    }
    @PutMapping
    public String updateProfile(
            @RequestBody ClientProfile profile
    ){

        service.updateProfile(profile);

        return "Profile Updated";
    }

}