package com.tech2tech.store.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech2tech.store.entities.User;
import com.tech2tech.store.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;


    public void ShowEntityStates(){
        var user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        userRepository.save(user);
    }
}
