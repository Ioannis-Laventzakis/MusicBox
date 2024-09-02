package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a subscription in the music box application.
 * A subscription is associated with a user and has a start and end date, defining the period during which the user has access to premium features.
 */
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the subscription, automatically generated.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user associated with this subscription. This is a many-to-one relationship, indicating that a user can have multiple subscriptions over time.

    private LocalDateTime startDate; // The start date of the subscription. Indicates when the subscription becomes active.

    private LocalDateTime endDate; // The end date of the subscription. Indicates when the subscription expires.

    /**
     * Constructs a new Subscription with specified user, start date, and end date.
     *
     * @param user The user associated with this subscription.
     * @param startDate The start date of the subscription.
     * @param endDate The end date of the subscription.
     */
    public Subscription(User user, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Default constructor for JPA.
     */
    public Subscription() {
    }

    // Getters and setters

    /**
     * Gets the ID of the subscription.
     * @return The ID of the subscription.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the subscription.
     * @param id The new ID of the subscription.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user associated with the subscription.
     * @return The user associated with the subscription.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the subscription.
     * @param user The new user associated with the subscription.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the start date of the subscription.
     * @return The start date of the subscription.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the subscription.
     * @param startDate The new start date of the subscription.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the subscription.
     * @return The end date of the subscription.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the subscription.
     * @param endDate The new end date of the subscription.
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}