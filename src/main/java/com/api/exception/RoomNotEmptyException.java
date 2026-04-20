package com.api.exception;

// Thrown when attempting to delete a room that still has linked sensors
public class RoomNotEmptyException extends RuntimeException {

    // Creates the exception with a default error message
    public RoomNotEmptyException() {
        super("Room has active sensors and cannot be deleted");
    }
}