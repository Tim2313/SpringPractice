package com.example.horses.controllers;

import com.example.horses.models.User;
import com.example.horses.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.addUsers(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}

