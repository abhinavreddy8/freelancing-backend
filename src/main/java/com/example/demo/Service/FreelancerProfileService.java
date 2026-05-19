package com.example.demo.Service;

import com.example.demo.Models.FreelancerProfile;
import com.example.demo.Repository.FreelancerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreelancerProfileService {

    @Autowired
    private FreelancerProfileRepository repository;

    public void createProfile(
            FreelancerProfile profile
    ) {

        repository.createProfile(profile);

    }

    public FreelancerProfile getProfile(
            Integer userId
    ) {

        return repository.getProfile(userId);

    }
    public void updateProfile(
            FreelancerProfile profile
    ){

        repository.updateProfile(profile);

    }

}