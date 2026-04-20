package com.api.mapper;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

// Handles uncaught exceptions globally
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    // Converts exceptions into HTTP responses
    @Override
    public Response toResponse(Throwable e) {

        // Handle 404 errors
        if (e instanceof NotFoundException) {
            return Response.status(404)
                    .entity(Map.of(
                            "error", "Not Found",
                            "status", 404
                    ))
                    .build();
        }

        // Print unexpected errors for debugging
        e.printStackTrace();

        // Handle all other errors as 500 Internal Server Error
        return Response.status(500)
                .entity(Map.of(
                        "error", "Internal Server Error",
                        "status", 500
                ))
                .build();
    }
}