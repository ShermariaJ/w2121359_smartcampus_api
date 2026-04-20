package com.api.exception;

// Thrown when a sensor is unavailable (e.g., under maintenance)
public class SensorUnavailableException extends RuntimeException {

    // Creates the exception with a default error message
    public SensorUnavailableException() {
        super("Sensor is in maintenance and cannot accept readings");
    }
}