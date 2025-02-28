package com.studentapp.controller;

import com.studentapp.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
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
        return "home";
    }
}
