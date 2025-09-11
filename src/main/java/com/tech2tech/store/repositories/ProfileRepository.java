package com.tech2tech.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tech2tech.store.entities.Profile;

public interface  ProfileRepository extends  CrudRepository<Profile, Long> {

    
}
