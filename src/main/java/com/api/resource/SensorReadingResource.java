package com.api.resource;

import com.api.model.*;
import com.api.store.DataStore;
import com.api.exception.SensorUnavailableException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

// Handles sensor reading operations (get and add readings)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    // Retrieves all readings for a specific sensor
    @GET
    public List<SensorReading> getReadings(@PathParam("id") String sensorId) {

        // Return readings list or empty list if none exist
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    // Adds a new reading for a specific sensor
    @POST
    public Response addReading(@PathParam("id") String sensorId, SensorReading reading) {

        // Get the sensor from storage
        Sensor sensor = DataStore.sensors.get(sensorId);

        // Return 404 if sensor not found
        if (sensor == null) return Response.status(404).build();

        // Prevent adding readings if sensor is under maintenance
        if ("MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException();
        }

        // Add reading to the sensor's reading list
        DataStore.readings
                .computeIfAbsent(sensorId, k -> new ArrayList<>())
                .add(reading);

        // Update sensor's current value
        sensor.setCurrentValue(reading.getValue());

        return Response.status(201).build(); // 201 Created
    }
}