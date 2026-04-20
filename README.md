# Smart Campus API

Student ID: w2121359  
Module: Client-Server Architectures

## Project Overview

Smart Campus API is a RESTful web service built using Java JAX-RS (Jersey).

The system manages university campus resources including:

- Rooms
- Sensors
- Sensor Readings

The API follows REST principles using resource-based endpoints, JSON responses, HTTP status codes, nested resources, and exception handling.

## Features

- Create, retrieve and delete rooms
- Register sensors linked to rooms
- Filter sensors by type
- Add and retrieve historical sensor readings
- Automatic current sensor value updates
- Custom exception mappers
- Request and response logging

## Technologies Used

- Java
- Maven
- JAX-RS (Jersey)
- Grizzly HTTP Server
- HashMap / ArrayList (in-memory storage)

## Base URL
`http://localhost:8082/api/v1`


## How to Build and Run

1. Open the project in NetBeans
2. Clean and Build the Maven project
3. Run the Main class
4. Wait for server startup confirmation
5. Use Postman or cURL to test endpoints

## API Endpoints

### Discovery

GET /api/v1

### Rooms

GET /api/v1/rooms  
POST /api/v1/rooms  
GET /api/v1/rooms/{roomId}  
DELETE /api/v1/rooms/{roomId}

### Sensors

GET /api/v1/sensors  
GET /api/v1/sensors?type=CO2  
POST /api/v1/sensors

### Sensor Readings

GET /api/v1/sensors/{sensorId}/readings  
POST /api/v1/sensors/{sensorId}/readings

## Sample cURL Commands

### 1. Discovery

curl http://localhost:8080/api/v1

### 2. Get Rooms

curl http://localhost:8080/api/v1/rooms

### 3. Create Room

curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"LIB-301\",\"name\":\"Library Study\",\"capacity\":40}"

### 4. Register Sensor

curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":24.5,\"roomId\":\"LIB-301\"}"

### 5. Filter Sensors

curl http://localhost:8080/api/v1/sensors?type=Temperature

### 6. Add Reading

curl -X POST http://localhost:8080/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"value\":26.1}"

## Error Handling

- 409 Conflict – deleting room with assigned sensors
- 422 Unprocessable Entity – invalid linked room
- 403 Forbidden – unavailable maintenance sensor
- 500 Internal Server Error – global fallback

## Author

Shermara Jayasooriya
