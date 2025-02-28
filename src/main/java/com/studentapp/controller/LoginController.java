package com.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to login.html in templates
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Redirected to after login
    }
}
