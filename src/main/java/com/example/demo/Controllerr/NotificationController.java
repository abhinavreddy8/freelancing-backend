package com.example.demo.Controller;

import com.example.demo.Models.Notification;
import com.example.demo.Repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationRepository repo;

    public NotificationController(NotificationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Integer userId) {
        return repo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @PutMapping("/{id}/read")
    public void markAsRead(@PathVariable Integer id) {
        Notification n = repo.findById(id).orElseThrow();
        n.setRead(true);
        repo.save(n);
    }
}