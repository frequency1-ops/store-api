package com.tech2tech.store.mappers;

import com.tech2tech.store.dtos.CartDto;
import com.tech2tech.store.dtos.CartItemDto;
import com.tech2tech.store.entities.Cart;
import com.tech2tech.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
