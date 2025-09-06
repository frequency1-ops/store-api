package com.tech2tech.store;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);

}
