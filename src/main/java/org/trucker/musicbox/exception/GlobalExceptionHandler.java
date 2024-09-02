package org.trucker.musicbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The {@code GlobalExceptionHandler} class provides a centralized exception handling mechanism across the entire application.
 * It intercepts exceptions thrown by controllers and returns a suitable {@link ResponseEntity} to the client, ensuring a consistent
 * error response structure. This class is annotated with {@code @ControllerAdvice}, which enables it to catch exceptions from all
 * controllers and not just a specific one.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link UserNotFoundException}. This method is invoked when a {@code UserNotFoundException}
     * is thrown from any part of the application. It constructs a {@link ResponseEntity} with the exception's message and
     * an HTTP status code of 404 (Not Found), indicating that the requested resource (user) does not exist.
     *
     * @param ex The caught {@code UserNotFoundException}.
     * @return A {@link ResponseEntity} containing the exception message and HTTP status 404.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Existing exception handlers...

    /**
     * Provides a generic exception handler that catches all exceptions not specifically handled by other methods in this class.
     * This method is a catch-all mechanism for exceptions, ensuring that the application can gracefully handle unexpected errors.
     * It logs the exception details (for internal debugging purposes) and returns a {@link ResponseEntity} with a generic error
     * message and an HTTP status code of 500 (Internal Server Error), indicating a problem with the server.
     *
     * @param ex The caught {@code Exception}.
     * @return A {@link ResponseEntity} with a generic error message and HTTP status 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception details for debugging purposes
        // Return a generic error message to the client
        return new ResponseEntity<>("An error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}