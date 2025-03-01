package com.studentapp.controller;

import com.studentapp.service.UserService; // Replace with actual serviceRegistration Controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String password) {
        userService.saveUser(name, password);  // Save the user after encrypting the password
        return "redirect:/login";  // Redirect to login after successful registration
    }
}
