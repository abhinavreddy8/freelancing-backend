package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "freelancer_profiles")
public class FreelancerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", unique = true)
    private Integer userId;

    private String profession;

    private String bio;

    private Integer experience;

    private String phone;

    private String address;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "other_profile_url")
    private String otherProfileUrl;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Column(name = "profile_photo")
    private String profilePhoto;
}