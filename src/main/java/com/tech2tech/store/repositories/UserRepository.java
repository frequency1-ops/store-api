package com.tech2tech.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech2tech.store.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
