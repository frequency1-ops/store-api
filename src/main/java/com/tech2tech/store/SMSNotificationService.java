package com.tech2tech.store;

import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        // SMS sending logic
        System.out.println("Sending SMS notification: " + message);
    }

}
