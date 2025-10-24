package com.tech2tech.store.services;

import com.tech2tech.store.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}
