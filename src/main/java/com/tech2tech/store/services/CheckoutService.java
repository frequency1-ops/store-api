package com.tech2tech.store.services;


import com.tech2tech.store.dtos.CheckoutRequest;
import com.tech2tech.store.dtos.CheckoutResponse;
import com.tech2tech.store.entities.Order;
import com.tech2tech.store.exceptions.CartEmptyException;
import com.tech2tech.store.exceptions.CartNotFoundException;
import com.tech2tech.store.exceptions.PaymentException;
import com.tech2tech.store.repositories.CartRepository;
import com.tech2tech.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;



    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request){
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);
        // create checkout session
      try{
          var session = paymentGateway.createCheckoutSession(order);

          cartService.clearCart(cart.getId());

          return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
      }catch (PaymentException ex){
          orderRepository.delete(order);
          throw ex;
      }
    }
    public void handleWebhookEvent(WebhookRequest request){
        paymentGateway.parseWebhookRequest(request).ifPresent(paymentResults-> {
            var order = orderRepository.findById(paymentResults.getOrderId()).orElseThrow();
            order.setStatus(paymentResults.getPaymentStatus());
            orderRepository.save(order);
        });

    }
}
