package com.studentapp.service;

import com.studentapp.model.User;
import com.studentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
       return userRepository.save(user);  // Save the user to the database
    }
}
