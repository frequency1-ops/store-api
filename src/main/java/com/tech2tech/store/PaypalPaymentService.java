package com.tech2tech.store;

import org.springframework.stereotype.Service;

@Service("paypal")

public class PaypalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        // Payment processing logic
        System.out.println("Processing payment of $" + amount + " via PayPal.");
    }

}
