package com.finalproject.musicbox.controller;

import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.model.Subscription;
import com.finalproject.musicbox.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<Subscription> createSubscription(@PathVariable Long id, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            Subscription subscription = userService.createSubscription(user.get(), startDate, endDate);
            return ResponseEntity.status(201).body(subscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
