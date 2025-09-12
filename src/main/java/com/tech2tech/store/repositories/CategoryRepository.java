package com.tech2tech.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech2tech.store.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Byte> {

}
