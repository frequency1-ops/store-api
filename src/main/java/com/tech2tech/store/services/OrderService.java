package com.tech2tech.store.services;

import com.tech2tech.store.dtos.OrderDto;
import com.tech2tech.store.exceptions.OrderNotFoundException;
import com.tech2tech.store.mappers.OrderMapper;
import com.tech2tech.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AuthService authService;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);

        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId)
                .orElseThrow(OrderNotFoundException::new);
        var user = authService.getCurrentUser();
        if (order.isPlacedBy(user)) {
            throw new AccessDeniedException("Access denied for this order");
        }
        return orderMapper.toDto(order);
    }
}
