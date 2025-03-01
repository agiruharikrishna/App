package com.studentapp.controller;

import com.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import com.studentapp.model.User;
import org.springframework.ui.Model;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Display the registration form
    }
    
@PostMapping("/register")
public String register(@RequestBody User user, Model model) {
    User savedUser = userService.saveUser(user);  // Save user using userService
    if (savedUser != null) {
        return "redirect:/login"; // Redirect to login after successful registration
    } else {
        model.addAttribute("error", "Registration failed.");
        return "register"; // Return to register page if registration fails
    }
}


}
