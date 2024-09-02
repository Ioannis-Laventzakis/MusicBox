package org.trucker.musicbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trucker.musicbox.model.User;
import org.trucker.musicbox.service.UserService;

@RestController // This annotation indicates that this class is a Spring MVC controller.
@RequestMapping("/api/users") // This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class UserController {

    @Autowired // This annotation is used for automatic dependency injection.
    private UserService userService; // Service class that contains the business logic for user operations.

    @PostMapping("/register") // This annotation maps HTTP POST requests onto specific handler methods.
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Method to register a new user in the system.
        // @RequestBody annotation indicates that this method parameter should be bound to the body of the web request.
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser); // The created user is returned in the HTTP response.
    }

    @GetMapping("/{username}") // This annotation maps HTTP GET requests onto specific handler methods.
    public ResponseEntity<User> getUser(@PathVariable String username) {
        // Method to retrieve user details by username.
        // @PathVariable annotation indicates that a method parameter should be bound to a URI template variable.
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user); // The user details are returned in the HTTP response.
    }
}
