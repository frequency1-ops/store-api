package com.tech2tech.store.services;

import com.tech2tech.store.dtos.CheckoutRequest;
import com.tech2tech.store.dtos.CheckoutResponse;
import com.tech2tech.store.dtos.ErrorDto;
import com.tech2tech.store.entities.Order;
import com.tech2tech.store.exceptions.CartEmptyException;
import com.tech2tech.store.exceptions.CartNotFoundException;
import com.tech2tech.store.repositories.CartRepository;
import com.tech2tech.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final CartService cartService;

    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
