package com.api.mapper;

import com.api.exception.LinkedResourceNotFoundException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

import java.util.Map;

// Handles LinkedResourceNotFoundException and converts it into an HTTP response
@Provider
public class LinkedResourceMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    // Maps the exception to a 422 Unprocessable Entity response
    @Override
    public Response toResponse(LinkedResourceNotFoundException e) {

        return Response.status(422)
                .entity(Map.of(
                        "error", "Referenced room does not exist", // Error message
                        "status", 422                               // HTTP status code
                ))
                .build();
    }
}