package com.tech2tech.store.controllers;


import com.tech2tech.store.dtos.CheckoutRequest;
import com.tech2tech.store.dtos.CheckoutResponse;
import com.tech2tech.store.dtos.ErrorDto;
import com.tech2tech.store.exceptions.CartEmptyException;
import com.tech2tech.store.exceptions.CartNotFoundException;

import com.tech2tech.store.exceptions.PaymentException;
import com.tech2tech.store.repositories.OrderRepository;
import com.tech2tech.store.services.CheckoutService;
import com.tech2tech.store.services.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;



    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
            ){
            checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));

    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(Exception exception){
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentexception(){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session"));
    }
}
