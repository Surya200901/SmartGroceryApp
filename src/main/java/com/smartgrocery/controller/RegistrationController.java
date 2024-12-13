package com.smartgrocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartgrocery.model.User;
import com.smartgrocery.service.UserService;

@RestController
public class RegistrationController {

   @Autowired
   private UserService userService;

   @PostMapping("/register")
   public ResponseEntity<String> registerUser(@RequestBody User user) {
       userService.registerUser(user);
       return ResponseEntity.ok("User registered successfully");
   }
}
