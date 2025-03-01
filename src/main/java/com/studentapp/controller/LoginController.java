package com.studentapp.controller;

import com.studentapp.service.StudentService;
import com.studentapp.security.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private final StudentService studentService;
    private final UserDetailsServiceImpl userDetailsService;

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
    if (authentication != null && authentication.isAuthenticated()) {
        System.out.println("User is authenticated: " + authentication.getName());
        model.addAttribute("name", authentication.getName());
        return "home";
    }
    System.out.println("User is not authenticated");
    return "redirect:/login";  // Redirect to login if not authenticated
}
    
    // Logout page or functionality
    @RequestMapping("/logout")
    public String logoutPage() {
        return "redirect:/login"; // Redirect to login after logout
    }
}
