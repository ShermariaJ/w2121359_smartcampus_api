package com.api.resource;

import com.api.model.Sensor;
import com.api.store.DataStore;
import com.api.exception.LinkedResourceNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Collection;

// Handles all sensor-related API operations
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // Retrieves all sensors, with optional filtering by type
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        // Return all sensors if no type filter is provided
        if (type == null) return DataStore.sensors.values();

        // Filter sensors by type (case-insensitive)
        return DataStore.sensors.values().stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .toList();
    }

    // Creates a new sensor
    @POST
    public Response createSensor(Sensor sensor) {

        // Check if the referenced room exists
        if (!DataStore.rooms.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException();
        }

        // Store the sensor
        DataStore.sensors.put(sensor.getId(), sensor);

        // Link sensor to the room
        DataStore.rooms.get(sensor.getRoomId())
                .getSensorIds().add(sensor.getId());

        return Response.status(201).entity(sensor).build(); // 201 Created
    }

    // Sub-resource locator for sensor readings
    @Path("/{id}/readings")
    public SensorReadingResource getReadings() {
        return new SensorReadingResource();
    }
}