package com.tech2tech.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        // Email sending logic
        System.out.println("Sending email notification: " + message);
    }

}
