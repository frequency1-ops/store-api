package com.tech2tech.store.repositories;

import com.tech2tech.store.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}