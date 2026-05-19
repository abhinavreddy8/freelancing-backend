package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "freelancer_id")
    private Integer freelancerId;

    private String title;

    private String description;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ✅ FIX: auto-set status and createdAt before insert
    @PrePersist
    public void prePersist() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }
}