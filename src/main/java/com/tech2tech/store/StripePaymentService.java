package com.tech2tech.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {

    @Value("${stripe.api.url}")
    private String apiUrl;

    @Value("${stripe.timeout}")
    private int timeout;

    @Value("${stripe.enabled}")
    private boolean enabled;

    @Value("${stripe.supportedCurrencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        // Payment processing logic
        System.out.println("Processing payment of $" + amount + " via Stripe.");
    }
}
