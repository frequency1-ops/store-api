package com.tech2tech.store.controllers;

import com.tech2tech.store.dtos.CheckoutResponse;
import com.tech2tech.store.dtos.CheckoutRequest;
import com.tech2tech.store.dtos.ErrorDto;
import com.tech2tech.store.exceptions.CartEmptyException;
import com.tech2tech.store.exceptions.CartNotFoundException;

import com.tech2tech.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;


    @PostMapping
    public CheckoutResponse checkout( @Valid @RequestBody CheckoutRequest request){
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(Exception exception){
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }
}
