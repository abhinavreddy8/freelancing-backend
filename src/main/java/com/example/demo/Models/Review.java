package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "reviewer_id")
    private Integer reviewerId;

    @Column(name = "reviewee_id")
    private Integer revieweeId;

    private Integer rating;

    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}