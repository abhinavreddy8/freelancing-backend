package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("clientId")
    @Column(name = "client_id")
    private Integer clientId;

    private String title;

    private String description;

    private Double budget;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private String status;

    @JsonProperty("selectedFreelancer")
    @Column(name = "selected_freelancer")
    private Integer selectedFreelancer;

    @ElementCollection
    private List<String> skills;
}