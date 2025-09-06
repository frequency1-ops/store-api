package com.tech2tech.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService {
@Value("${mail.host}")
private String host;
@Value("${mail.port}")
private String port;

    @Override
    public void send(String message, String recipientEmail) {
        // SMS sending logic
        System.out.println("Connecting to SMS server at " + host + ":" + port);
        System.out.println("Sending SMS to: " + recipientEmail);
        System.out.println("Sending SMS notification: " + message);
    }

}
