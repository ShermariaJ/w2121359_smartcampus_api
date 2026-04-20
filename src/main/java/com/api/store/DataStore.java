package com.api.store;

import com.api.model.Room;
import com.api.model.Sensor;
import com.api.model.SensorReading;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// In-memory data storage for the application
public class DataStore {

    // Stores all rooms (key = room ID)
    public static Map<String, Room> rooms = new ConcurrentHashMap<>();

    // Stores all sensors (key = sensor ID)
    public static Map<String, Sensor> sensors = new ConcurrentHashMap<>();

    // Stores sensor readings (key = sensor ID, value = list of readings)
    public static Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();
}