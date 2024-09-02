package org.trucker.musicbox.exception;

/**
 * Custom exception class representing the scenario where a user is not found within the application.
 * This exception extends the RuntimeException, allowing it to be thrown during the normal operation
 * of the application without being required to be caught or declared thrown. It is specifically designed
 * to be used within the context of user management operations, such as retrieving user details or verifying
 * user existence, where the absence of a user record in the database is an expected but exceptional condition.
 *
 * The class provides a constructor that accepts a detail message, which can be used to convey more information
 * about the exception context or cause. This message is accessible through the standard Throwable.getMessage()
 * method, facilitating debugging and logging activities.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message the detail message. The detail message is saved for later retrieval
     *                by the Throwable.getMessage() method. This message can provide additional
     *                information about the circumstances under which the exception was thrown,
     *                aiding in understanding and resolving the underlying issue.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}