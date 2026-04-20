package com.api.mapper;

import com.api.exception.SensorUnavailableException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

import java.util.Map;

// Handles SensorUnavailableException and converts it into an HTTP response
@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {

    // Maps the exception to a 403 Forbidden response
    @Override
    public Response toResponse(SensorUnavailableException e) {

        return Response.status(Response.Status.FORBIDDEN) // 403 Forbidden
                .entity(Map.of(
                        "error", "Sensor is under maintenance", // Error message
                        "status", 403                           // HTTP status code
                ))
                .build();
    }
}