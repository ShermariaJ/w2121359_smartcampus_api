package com.api.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

// Root endpoint that provides basic API information
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    // Handles GET request for API discovery details
    @GET
    public Map<String, Object> getInfo() {

        // Main response object
        Map<String, Object> response = new HashMap<>();

        // API details
        response.put("version", "v1");
        response.put("contact", "admin@smartcampus.com");

        // Available endpoints
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        // Add endpoints to response
        response.put("resources", resources);

        return response; // Return JSON response
    }
}