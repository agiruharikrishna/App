package com.studentapp.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Example method to save a user (e.g., for registration)
    public void saveUser(String name, String password) {
        // Add logic to save the user (e.g., save to a database)
        System.out.println("User saved: " + name);
    }
}
