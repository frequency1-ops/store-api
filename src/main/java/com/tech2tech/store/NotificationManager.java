package com.tech2tech.store;

import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    public void sendNotification(String message, String recipientEmail) {
        notificationService.send(message, recipientEmail);
    }

}
