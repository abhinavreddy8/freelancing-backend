package com.example.demo.Service;

import com.example.demo.Models.Notification;
import com.example.demo.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public void createNotification(Integer userId, String title, String message, String type, Integer refId) {

        Notification n = new Notification();
        n.setUserId(userId);
        n.setTitle(title);
        n.setMessage(message);
        n.setType(type);
        n.setReferenceId(refId);

        repo.save(n);
    }
}