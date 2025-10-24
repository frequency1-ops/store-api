package com.tech2tech.store.services;

import com.tech2tech.store.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResults {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
