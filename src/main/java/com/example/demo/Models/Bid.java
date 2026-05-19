package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor   // ✅ THIS FIXES YOUR ERROR
@NoArgsConstructor
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "freelancer_id")
    private Integer freelancerId;

    @Column(name = "bid_amount")
    private Double bidAmount;

    private String proposal;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}