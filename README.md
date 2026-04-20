# Smart Campus API

Student ID: w2121359  
Student Name: Shermara Jayasooriya  
Module: Client-Server Architectures

## Overview

RESTful Smart Campus API built using Java JAX-RS (Jersey).

Manages:

- Rooms
- Sensors
- Sensor Readings

## Technologies

- Java
- Maven
- JAX-RS (Jersey)
- Grizzly HTTP Server
- HashMap / ArrayList

## Base URL

`http://localhost:8082/api/v1`

## How to Run

1. Open project in NetBeans
2. Clean and Build
3. Run `Main.java`
4. Test using Postman or cURL

## Endpoints

### Discovery
GET `/api/v1`

### Rooms
GET `/api/v1/rooms`  
POST `/api/v1/rooms`  
GET `/api/v1/rooms/{roomId}`  
DELETE `/api/v1/rooms/{roomId}`

### Sensors
GET `/api/v1/sensors`  
GET `/api/v1/sensors?type=CO2`  
POST `/api/v1/sensors`

### Readings
GET `/api/v1/sensors/{sensorId}/readings`  
POST `/api/v1/sensors/{sensorId}/readings`

## Sample cURL Commands

```bash
curl http://localhost:8082/api/v1
curl http://localhost:8082/api/v1/rooms
curl -X POST http://localhost:8082/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"LIB-301\",\"name\":\"Library Study\",\"capacity\":40}"
curl http://localhost:8082/api/v1/sensors?type=Temperature
curl -X POST http://localhost:8082/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"value\":26.1}"
