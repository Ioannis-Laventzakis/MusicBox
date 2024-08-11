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

/**
 * REST controller for managing users and their subscriptions.
 * Provides endpoints for creating, updating, retrieving, and deleting users,
 * as well as creating subscriptions for users.
 */
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    /**
     * Service for handling user-related operations.
     */
    private final UserService userService;

    /**
     * Constructs a new UserController with the specified UserService.
     *
     * @param userService the UserService to use for user-related operations
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    /**
     * Creates a new subscription for the specified user.
     *
     * @param id the ID of the user
     * @param startDate the start date of the subscription
     * @param endDate the end date of the subscription
     * @return the created subscription
     */
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

    /**
     * Retrieves the user with the specified ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates the user with the specified ID.
     *
     * @param id the ID of the user to update
     * @param user the updated user data
     * @return the updated user, or 404 if not found
     */
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

    /**
     * Deletes the user with the specified ID.
     *
     * @param id the ID of the user to delete
     * @return 204 No Content if the user was deleted, or 404 if not found
     */
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