package com.api.mapper;

import com.api.exception.RoomNotEmptyException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

import java.util.Map;

// Handles RoomNotEmptyException and returns a proper HTTP response
@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {

    // Maps the exception to a 409 Conflict response
    @Override
    public Response toResponse(RoomNotEmptyException e) {

        return Response.status(Response.Status.CONFLICT) // 409 Conflict
                .entity(Map.of(
                        "error", "Room has active sensors", // Error message
                        "status", 409                        // HTTP status code
                ))
                .build();
    }
}