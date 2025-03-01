package com.studentapp.controller;

import com.studentapp.service.StudentService;
import com.studentapp.security.UserDetailsServiceImpl;  // Ensure this is the correct import
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final StudentService studentService;
    private final UserDetailsServiceImpl userDetailsService;  // Remove if not needed

    // Combine both services in one constructor
    public LoginController(StudentService studentService, UserDetailsServiceImpl userDetailsService) {
        this.studentService = studentService;
        this.userDetailsService = userDetailsService;
    }

    // Login page (handled by Spring Security)
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Home page after successful login
    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "home";  // Only return the home view
    }
}
