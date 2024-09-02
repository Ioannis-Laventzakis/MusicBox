package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for handling CRUD operations on User entities within the music box application.
 * Extends JpaRepository to leverage Spring Data JPA's built-in functionalities such as basic CRUD operations.
 * Additionally, it provides custom query methods for finding users by specific attributes beyond the standard ones.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User entity based on the username.
     * This method uses Spring Data JPA's query derivation mechanism to automatically generate the query from the method name.
     * It's a convenient way to perform simple queries without needing to explicitly define them.
     *
     * @param username The username of the User entity to be found.
     * @return An Optional containing the User entity if found, otherwise empty. Using Optional helps in handling cases where the user might not exist.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a User entity based on the email.
     * Similar to findByUsername, this method also relies on query derivation for its implementation.
     * It's useful for operations where the email is used as a unique identifier or login credential.
     *
     * @param email The email of the User entity to be found.
     * @return An Optional containing the User entity if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Custom query to find users by their status.
     * Unlike the previous methods, this one uses the @Query annotation to define a JPQL query directly.
     * This approach is used for more complex queries that cannot be easily covered by query derivation.
     *
     * @param status The status of the User entities to be found.
     * @return A List of User entities matching the given status. If no users are found, returns an empty list.
     * @Param("status") binds the method parameter 'status' to the named parameter ':status' in the query.
     */
    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findUsersByStatus(@Param("status") String status);
}