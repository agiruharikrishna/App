package com.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Corresponds to login.html in templates
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Corresponds to home.html in templates
    }
}
