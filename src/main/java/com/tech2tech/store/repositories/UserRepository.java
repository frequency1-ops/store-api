package com.tech2tech.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech2tech.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {



}
