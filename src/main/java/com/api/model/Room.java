package com.api.model;

import java.util.ArrayList;
import java.util.List;

// Represents a Room entity in the system
public class Room {

    private String id;                 // Unique identifier for the room
    private String name;               // Name of the room
    private int capacity;              // Maximum capacity of the room
    private List<String> sensorIds = new ArrayList<>(); // List of associated sensor IDs

    // Returns the room ID
    public String getId() { return id; }

    // Sets the room ID
    public void setId(String id) { this.id = id; }

    // Returns the room name
    public String getName() { return name; }

    // Sets the room name
    public void setName(String name) { this.name = name; }

    // Returns the room capacity
    public int getCapacity() { return capacity; }

    // Sets the room capacity
    public void setCapacity(int capacity) { this.capacity = capacity; }

    // Returns the list of sensor IDs linked to the room
    public List<String> getSensorIds() { return sensorIds; }

    // Sets the list of sensor IDs for the room
    public void setSensorIds(List<String> sensorIds) { this.sensorIds = sensorIds; }
}