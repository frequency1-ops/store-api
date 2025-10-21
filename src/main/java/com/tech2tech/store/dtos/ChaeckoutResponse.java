package com.tech2tech.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ChaeckoutResponse {

    private Long orderId;

    public ChaeckoutResponse(Long orderId) {
        this.orderId = orderId;
    }
}
