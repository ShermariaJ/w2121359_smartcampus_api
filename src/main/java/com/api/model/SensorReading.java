package com.api.model;

// Represents a sensor reading captured at a specific time
public class SensorReading {

    private String id;        // Unique identifier for the reading
    private long timestamp;   // Time the reading was recorded (e.g., epoch time)
    private double value;     // Measured value from the sensor

    // Returns the reading ID
    public String getId() { return id; }

    // Sets the reading ID
    public void setId(String id) { this.id = id; }

    // Returns the timestamp of the reading
    public long getTimestamp() { return timestamp; }

    // Sets the timestamp of the reading
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    // Returns the sensor value
    public double getValue() { return value; }

    // Sets the sensor value
    public void setValue(double value) { this.value = value; }
}