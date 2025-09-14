package com.tech2tech.store.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech2tech.store.dtos.ProductDto;
import com.tech2tech.store.entities.Product;
import com.tech2tech.store.mappers.ProductMapper;
import com.tech2tech.store.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @GetMapping
    public List<ProductDto> getAllProducts(
        @RequestParam(required=false, name="categoryId") Byte categoryId
    ){
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        }else{
            products = productRepository.findAllWithCategory();
        }
        return products.stream().map(productMapper::toDto).toList();
        
    }



}
