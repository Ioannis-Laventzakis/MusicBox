package org.trucker.musicbox.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trucker.musicbox.model.Subscription;
import org.trucker.musicbox.service.SubscriptionService;

import java.sql.Timestamp;

/**
 * The {@code SubscriptionController} class is responsible for handling HTTP requests related to subscription management.
 * It acts as a part of the controller layer in the MVC (Model-View-Controller) architecture, directly interacting with the
 * {@link SubscriptionService} to perform operations such as creating new subscriptions and checking if a user is a premium member.
 * This class is annotated with {@code @RestController}, indicating it's a special type of controller which returns a domain object
 * instead of a view. It is designed to be used in RESTful web services.
 */
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService; // Injects an instance of SubscriptionService to handle business logic.

    /**
     * Endpoint to create a new subscription for a user. It accepts user ID and subscription end date as request parameters.
     * This method uses HTTP POST request method indicating that it creates a new resource (subscription) on the server.
     *
     * @param userId The ID of the user for whom the subscription is being created. It must not be null.
     * @param endDate The expiration date of the subscription. It must not be null.
     * @return A {@link ResponseEntity} containing the created {@link Subscription} object. The HTTP status code is 200 OK if the
     *         subscription is successfully created.
     */
    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(@RequestParam @NotNull Long userId, @RequestParam @NotNull Timestamp endDate) {
        Subscription subscription = subscriptionService.createSubscription(userId, endDate);
        return ResponseEntity.ok(subscription); // Wraps the created subscription in ResponseEntity and returns it.
    }

    /**
     * Endpoint to check if a user has a premium status. It uses HTTP GET method, suitable for retrieving data.
     * The user ID is passed as a part of the URL path, making it a path variable.
     *
     * @param userId The ID of the user whose premium status is being checked. It must not be null.
     * @return A {@link ResponseEntity} containing a {@link Boolean} indicating the user's premium status. The HTTP status code is 200 OK if the
     *         operation is successful.
     */
    @GetMapping("/premium-status/{userId}")
    public ResponseEntity<Boolean> isPremiumUser(@PathVariable @NotNull Long userId) {
        boolean isPremium = subscriptionService.isPremiumUser(userId);
        return ResponseEntity.ok(isPremium); // Wraps the premium status in ResponseEntity and returns it.
    }
}