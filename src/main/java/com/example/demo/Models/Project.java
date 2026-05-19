package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonFormat(pattern = "yyyy-MM-dd")  // ← ADD THIS
    private LocalDate deadline;

    private String status;

    @Column(name = "selected_freelancer")
    private Integer selectedFreelancer;

    private List<String> skills;
}