package com.smartgrocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       loginRequest.getUsername(), loginRequest.getPassword())
       );
       SecurityContextHolder.getContext().setAuthentication(authentication);
       return ResponseEntity.ok("Login successful");
   }
}
