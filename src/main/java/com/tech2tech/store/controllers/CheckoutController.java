package com.tech2tech.store.controllers;


import com.stripe.exception.SignatureVerificationException;
import com.stripe.net.Webhook;
import com.tech2tech.store.dtos.CheckoutRequest;
import com.tech2tech.store.dtos.CheckoutResponse;
import com.tech2tech.store.dtos.ErrorDto;
import com.tech2tech.store.exceptions.CartEmptyException;
import com.tech2tech.store.exceptions.CartNotFoundException;

import com.tech2tech.store.exceptions.PaymentException;
import com.tech2tech.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;


    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(
            @RequestHeader("Stripe-Signature") String signature,
            @RequestBody String payload
            ){
        try {
            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);
            var stripeObject = event.getDataObjectDeserializer().getObject().orElse(null);
            switch (event.getType()) {
                case "payment_intent.succeeded" -> {
                    //update order status (PAID)
                }
                case "payment_intent.failed" -> {
                    //update order status (FAILED)
                }
            }
            return ResponseEntity.ok().build();

        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().build();


        }

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
