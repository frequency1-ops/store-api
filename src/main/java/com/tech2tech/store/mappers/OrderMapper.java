package com.tech2tech.store.mappers;

import com.tech2tech.store.dtos.OrderDto;
import com.tech2tech.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
