package com.finalproject.musicbox.controller;

import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.service.UserService;
import com.finalproject.musicbox.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam String username) {
        User user = userService.findByName(username);  // Use UserService to get the user
        return ResponseEntity.ok(user);  // Return 200 OK with the user
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, @RequestParam boolean isPremium) {
        User createdUser = userService.createUser(user, isPremium);  // Use UserService to create the user
        return ResponseEntity.status(201).body(createdUser);  // Return 201 Created with the created user
    }

    // Other potential endpoints (e.g., GET /users, GET /users/{id})...
}