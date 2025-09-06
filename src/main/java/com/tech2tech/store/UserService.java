package com.tech2tech.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public UserService(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }
    public void registerUser(User user) {
        // Registration logic
        if (userRepository.findByEmail(user.getEmail()) != null) 
            
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists.");

        userRepository.save(user);
        notificationService.send("you registered succesfully " + user.getName() + "!", user.getEmail());
       
    }

}
