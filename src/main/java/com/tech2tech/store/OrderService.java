package com.tech2tech.store;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private PaymentService paymentService;

    public OrderService(@Qualifier("stripe")PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void placeOrder() {
        paymentService.processPayment(100.0);
    }
}
