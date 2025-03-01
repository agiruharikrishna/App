package com.studentapp.controller;

import com.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Display the registration form
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String password, Model model) {
    if (studentService.registerStudent(name, password) != null) {
        return "redirect:/login";
    } else {
        model.addAttribute("error", "Registration failed.");
        return "register";
    }
}

}
