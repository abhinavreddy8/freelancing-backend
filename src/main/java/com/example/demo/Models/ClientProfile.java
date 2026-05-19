package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client_profiles")
public class ClientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", unique = true)
    private Integer userId;

    private String companyName;

    private String companyDescription;

    private String profession;

    private String phone;

    private String address;

    private String linkedinUrl;

    private String githubUrl;

    private String website;

    private String otherProfileUrl;

    private Integer totalProjectsPosted;

    private Double totalSpent;

    private String profilePhoto;
}