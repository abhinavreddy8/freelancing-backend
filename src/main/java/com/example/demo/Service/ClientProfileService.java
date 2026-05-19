package com.example.demo.Service;

import com.example.demo.Models.ClientProfile;
import com.example.demo.Repository.ClientProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProfileService {

    @Autowired
    private ClientProfileRepository repository;

    public void createProfile(
            ClientProfile profile
    ) {

        repository.createProfile(profile);

    }

    public ClientProfile getProfile(
            Integer userId
    ) {

        return repository.getProfile(userId);

    }
    public void updateProfile(
            ClientProfile profile
    ){

        repository.updateProfile(profile);

    }

}