package com.studentapp.controller;

import com.studentapp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        if (studentService.authenticate(name, password)) {
            return "redirect:/home";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String password, Model model) {
        studentService.registerStudent(name, password);
        return "redirect:/login";
    }
}
