package org.trucker.musicbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trucker.musicbox.exception.UserNotFoundException;
import org.trucker.musicbox.model.User;
import org.trucker.musicbox.repository.UserRepository;
import javax.validation.Valid;

/**
 * UserService class for managing user-related operations within the application.
 * This class is marked with @Service to denote it as a Spring service class, which is a specialized component
 * for implementing business logic and service-layer functionality. It interacts with the UserRepository to perform
 * data access operations on User entities.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a User entity to the database.
     * This method encapsulates the logic for saving a User entity, including any necessary pre-save business logic.
     * It leverages the UserRepository for the actual database operation. The use of @Valid annotation ensures that
     * the user entity passed to this method meets all validation constraints defined in the User model before proceeding with the save operation.
     *
     * @param user The User entity to be saved, validated against model constraints.
     * @return The saved User entity, now including any auto-generated fields like ID.
     */
    public User saveUser(@Valid User user) {
        // Implement any additional business logic required before saving the user
        return userRepository.save(user);
    }

    /**
     * Finds a User entity based on the username.
     * This method provides the functionality to find a User entity by its username. It uses the UserRepository's
     * findByUsername method, which returns an Optional<User>. If the user is found, it is returned; otherwise,
     * a UserNotFoundException is thrown. This approach ensures that the caller can directly work with the User entity
     * without having to handle Optional types, while still being informed of cases where the user does not exist.
     *
     * @param username The username of the User entity to be found.
     * @return The User entity if found.
     * @throws UserNotFoundException if the user is not found, encapsulating the error scenario in a custom exception.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}