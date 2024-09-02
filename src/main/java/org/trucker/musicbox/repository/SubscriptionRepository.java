package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.Subscription;

import java.util.List;

/**
 * SubscriptionRepository interface for handling CRUD operations on Subscription entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * This interface provides methods for querying Subscription entities based on various criteria, such as user ID and subscription status.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Finds subscriptions by user ID.
     * This method leverages Spring Data JPA's method query derivation mechanism to generate the necessary query
     * based on the method name. It automatically searches for subscriptions where the user ID matches the provided argument.
     *
     * @param userId The ID of the user whose subscriptions are to be searched.
     * @return A list of Subscription entities associated with the specified user ID. If no subscriptions are found, returns an empty list.
     */
    List<Subscription> findByUserId(Long userId);

    /**
     * Custom query to find active subscriptions.
     * This method uses the @Query annotation to define a JPQL query that searches for subscriptions that are currently active,
     * based on the current timestamp being before the subscription's end date.
     *
     * @return A list of active Subscription entities. If no active subscriptions are found, returns an empty list.
     */
    @Query("SELECT s FROM Subscription s WHERE s.endDate > CURRENT_TIMESTAMP")
    List<Subscription> findActiveSubscriptions();
}