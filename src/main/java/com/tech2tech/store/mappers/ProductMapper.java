package com.tech2tech.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tech2tech.store.dtos.ProductDto;
import com.tech2tech.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

}
