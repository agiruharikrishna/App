package com.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/homePage")  // Changed to avoid conflict
    public String homePage() {
        return "home";  // This will render home.html in the templates folder
    }
}
