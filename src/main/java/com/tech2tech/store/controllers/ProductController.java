package com.tech2tech.store.controllers;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tech2tech.store.dtos.ProductDto;
import com.tech2tech.store.entities.Product;
import com.tech2tech.store.mappers.ProductMapper;
import com.tech2tech.store.repositories.CategoryRepository;
import com.tech2tech.store.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
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
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
        @RequestBody ProductDto productDto,
        UriComponentsBuilder uriBuilder
        ){
            var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().build();
            }
            var product = productMapper.toEntity(productDto);
            product.setCategory(category);
            productRepository.save(product);
            productDto.setId(product.getId());
            var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

            return ResponseEntity.created(uri).body(productDto);
        }
        @PutMapping("/{id}")
        public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto productDto

        ){
            var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().build();
            }

            var product = productRepository.findById(id).orElse(null);
            if (product == null){
                return ResponseEntity.notFound().build();
            }
            productMapper.update(productDto, product);
            productRepository.save(product);
            productDto.setId(product.getId());

            return ResponseEntity.ok(productDto);
        }



}
