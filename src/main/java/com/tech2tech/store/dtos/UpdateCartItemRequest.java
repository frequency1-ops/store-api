package com.tech2tech.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quantity must be provided")
    private Integer quantity;
}
