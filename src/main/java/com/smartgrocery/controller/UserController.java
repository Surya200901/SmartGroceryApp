package com.smartgrocery.controller;

import com.smartgrocery.model.User;
import com.smartgrocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Registration page
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Render register.html view
    }

    // Process registration form
    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);  // Register user
        return "redirect:/login";  // Redirect to login page after registration
    }
    
    @GetMapping("/")
    public String home() {
        return "index"; // Ensures index.html is shown at the root path
    }

    // Login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Render login.html view
    }
}
