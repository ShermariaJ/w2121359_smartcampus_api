package com.api.model;

// Represents a Sensor entity in the system
public class Sensor {

    private String id;          // Unique identifier for the sensor
    private String type;        // Type of sensor (e.g., temperature, motion)
    private String status;      // Current status (e.g., active, inactive, maintenance)
    private double currentValue; // Latest recorded value from the sensor
    private String roomId;      // ID of the room the sensor is assigned to

    // Returns the sensor ID
    public String getId() { return id; }

    // Sets the sensor ID
    public void setId(String id) { this.id = id; }

    // Returns the sensor type
    public String getType() { return type; }

    // Sets the sensor type
    public void setType(String type) { this.type = type; }

    // Returns the sensor status
    public String getStatus() { return status; }

    // Sets the sensor status
    public void setStatus(String status) { this.status = status; }

    // Returns the current sensor value
    public double getCurrentValue() { return currentValue; }

    // Sets the current sensor value
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }

    // Returns the associated room ID
    public String getRoomId() { return roomId; }

    // Sets the associated room ID
    public void setRoomId(String roomId) { this.roomId = roomId; }
}