package com.tech2tech.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech2tech.store.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
