package com.tech2tech.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech2tech.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {


}
