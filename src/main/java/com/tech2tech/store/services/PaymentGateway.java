package com.tech2tech.store.services;

import com.tech2tech.store.entities.Order;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
    Optional<PaymentResults> parseWebhookRequest(WebhookRequest request);
}
