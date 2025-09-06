package com.tech2tech.store;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    // In-memory storage for users
    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public User findByEmail(String email) {
        return users.getOrDefault(email, null);
    }

}
