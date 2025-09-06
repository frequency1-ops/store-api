package com.tech2tech.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message, String recipientEmail) {
        // Email sending logic
        System.out.println("Connecting to email server at " + host + ":" + port);
        System.out.println("Sending email to: " + recipientEmail);
        System.out.println("Sending email notification: " + message);
        
    }
    
    
    

}
