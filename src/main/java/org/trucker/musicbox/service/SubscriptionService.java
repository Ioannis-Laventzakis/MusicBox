package org.trucker.musicbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trucker.musicbox.exception.UserNotFoundException;
import org.trucker.musicbox.model.Subscription;
import org.trucker.musicbox.model.User;
import org.trucker.musicbox.repository.SubscriptionRepository;
import org.trucker.musicbox.repository.UserRepository;

import java.sql.Timestamp;

/**
 * Provides services related to subscription management within the application.
 * This class is marked with @Service to indicate that it's a Spring service class,
 * which is a specialized component for implementing business logic and service-layer functionality.
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new subscription for a user.
     * This method is transactional, ensuring that operations within it either complete successfully as a whole
     * or are entirely rolled back in case of an error, maintaining data integrity.
     *
     * @param userId  The ID of the user for whom the subscription is created.
     * @param endDate The end date of the subscription, indicating when it will expire.
     * @return The created Subscription object, containing details about the new subscription.
     * @throws UserNotFoundException if no user with the given ID is found, ensuring the method caller is aware of this failure scenario.
     */
    @Transactional
    public Subscription createSubscription(Long userId, Timestamp endDate) {
        // Fetch the user from the database; throw an exception if not found.
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        // Create a new Subscription object with the current time as the start date and the provided end date.
        Subscription subscription = new Subscription(user, new Timestamp(System.currentTimeMillis()).toLocalDateTime(), endDate.toLocalDateTime());
        // Update the user's premium status.
        user.setIsPremium(true);
        // Save the updated user and the new subscription in the database.
        userRepository.save(user);
        return subscriptionRepository.save(subscription);
    }

    /**
     * Checks if a user is a premium user based on their user ID.
     * This method queries the database for the user and evaluates their premium status.
     *
     * @param userId The ID of the user to check.
     * @return true if the user is premium, false otherwise.
     * @throws UserNotFoundException if no user with the given ID is found, ensuring the method caller is aware of this failure scenario.
     */
    public boolean isPremiumUser(Long userId) {
        // Fetch the user from the database; throw an exception if not found.
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        // Return the user's premium status.
        return user.isPremium();
    }
}