# MusicBox Application Demo

This demo represents the functionality of the MusicBox application, including user management, subscription management, and CRUD operations through RESTful APIs.

## Project Setup

### Step 1: Create the Spring Boot Project

You can start by creating a Spring Boot project using Spring Initializr (via your IDE or directly at [start.spring.io](https://start.spring.io/)).

#### Dependencies:
- **Spring Web**: For building the web layer.
- **Spring Data JPA**: For database interaction.
- **Spring Security**: For security configurations.
- **H2 Database**: For a quick in-memory database.
- **Validation**: For request validation.

### Step 2: Define Entities

#### User Entity (`User.java`)

```java
package com.finalproject.musicbox.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions;

    // Getters and setters
}
```
Subscription Entity (Subscription.java)
```java
package com.finalproject.musicbox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}
```
Step 3: Define Repositories
User Repository (UserRepository.java)
```java
package com.finalproject.musicbox.repository;

import com.finalproject.musicbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
```
Subscription Repository (SubscriptionRepository.java)
```java
package com.finalproject.musicbox.repository;

import com.finalproject.musicbox.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByEndDateAfter(LocalDateTime date);
}
```
Step 4: Service Layer
User Service (UserService.java)
```java
package com.finalproject.musicbox.service;

import com.finalproject.musicbox.model.Subscription;
import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.repository.SubscriptionRepository;
import com.finalproject.musicbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public User createUser(User user) {
        user.setStatus("ACTIVE");
        return userRepository.save(user);
    }

    public Subscription createSubscription(User user, LocalDateTime startDate, LocalDateTime endDate) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        return subscriptionRepository.save(subscription);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```
Step 5: Define Controller
User Controller (UserController.java)
```java
package com.finalproject.musicbox.controller;

import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.model.Subscription;
import com.finalproject.musicbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
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
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
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
```
Step 6: Database Configuration
Use an in-memory H2 database for simplicity:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```
Step 7: Run the Application
Run the Application: Start your Spring Boot application through your IDE or using the command line:
```shell
mvn spring-boot:run
```
```shell
Summary
This demo covers the core functionality of your MusicBox application, including user creation, subscription management,
 and basic CRUD operations using RESTful APIs. 
 You can extend this further with additional features or integrate it with a front-end if needed.
```
