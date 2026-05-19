package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "freelancer_id")
    private Integer freelancerId;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "reference_link")
    private String referenceLink;

    private String status;

    @Column(name = "client_feedback")
    private String clientFeedback;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}