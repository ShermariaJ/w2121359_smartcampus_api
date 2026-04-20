package com.api.exception;

// Thrown when a referenced resource (e.g., room) does not exist
public class LinkedResourceNotFoundException extends RuntimeException {

    // Creates the exception with a default error message
    public LinkedResourceNotFoundException() {
        super("Referenced room does not exist");
    }
}