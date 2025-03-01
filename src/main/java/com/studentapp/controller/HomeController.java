package com.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Remove or modify the /home mapping
    // Use a different URL if needed
    @GetMapping("/dashboard")  // Updated path
    public String homePage() {
        return "home";  // This will render home.html in the templates folder
    }
}
