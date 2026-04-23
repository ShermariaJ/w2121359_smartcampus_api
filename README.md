# Smart Campus API

**Student ID:** w2121359  
**Student Name:** Shermara Jayasooriya  
**Module:** Client-Server Architectures

---

## Overview

This project is a RESTful Smart Campus API built using **Java JAX-RS (Jersey)**.  
It is designed to manage rooms across campus, sensors placed inside rooms, and historical sensor readings.

The API manages:
- Rooms
- Sensors
- Sensor Readings

---

## Technologies Used

- Java
- Maven
- JAX-RS (Jersey)
- Grizzly HTTP Server
- HashMap
- ArrayList

---

## Base URL

```
http://localhost:8082/api/v1
```

---

## How to Run

1. Open the project in NetBeans
2. Clean and Build the project
3. Run `Main.java`
4. Use Postman or cURL to test the endpoints

---

## API Endpoints

### Discovery

| Method | Endpoint |
|--------|----------|
| GET | `/api/v1` |

### Rooms

| Method | Endpoint |
|--------|----------|
| GET | `/api/v1/rooms` |
| POST | `/api/v1/rooms` |
| GET | `/api/v1/rooms/{roomId}` |
| DELETE | `/api/v1/rooms/{roomId}` |

### Sensors

| Method | Endpoint |
|--------|----------|
| GET | `/api/v1/sensors` |
| GET | `/api/v1/sensors?type=CO2` |
| POST | `/api/v1/sensors` |

### Readings

| Method | Endpoint |
|--------|----------|
| GET | `/api/v1/sensors/{sensorId}/readings` |
| POST | `/api/v1/sensors/{sensorId}/readings` |

---

## Sample cURL Commands

```bash
# Discovery
curl http://localhost:8082/api/v1

# Get all rooms
curl http://localhost:8082/api/v1/rooms

# Create a room
curl -X POST http://localhost:8082/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d '{"id":"LIB-301","name":"Library Study","capacity":40}'

# Filter sensors by type
curl http://localhost:8082/api/v1/sensors?type=Temperature

# Post a sensor reading
curl -X POST http://localhost:8082/api/v1/sensors/TEMP-001/readings \
  -H "Content-Type: application/json" \
  -d '{"value":24.7}'
```

---

## Coursework Report Answers

### Part 1 – Setup & Discovery

**Q1. Explain the default lifecycle of a JAX-RS Resource class. Is a new instance created per request or treated as a singleton? How does this affect in-memory data structures?**

By default, JAX-RS resource classes are created per request — a new object is instantiated each time a request comes in. This helps avoid problems with shared state inside the resource class. However, in this project the main data is stored in shared collections like `HashMap` and `ArrayList`, so that data persists between requests. Because multiple requests could access the data simultaneously, updates should be handled carefully to avoid conflicts or data loss.

**Q2. Why is Hypermedia (HATEOAS) considered advanced RESTful design? How does it help clients?**

Hypermedia means the API response includes links or paths to other related resources. It is considered a strong REST feature because clients can understand where to go next without relying solely on external documentation. In this API, the discovery endpoint exposes links such as `/rooms` and `/sensors`, helping users navigate the system more easily.

---

### Part 2 – Room Management

**Q3. What are the implications of returning only room IDs vs full room objects?**

Returning only room IDs makes responses smaller and faster as less data is transferred. However, the client would need to make additional requests to retrieve details about each room. Returning full room objects provides all relevant information at once — such as room name and capacity — at the cost of higher bandwidth. In this project, returning full room objects is reasonable given the small data size.

**Q4. Is DELETE idempotent in your implementation?**

Yes, DELETE is idempotent. Repeating the same delete request leaves the system in the same final state. If a room is deleted once, sending the same request again will not delete anything further because the room no longer exists. The second request may return `404 Not Found`, but the overall state of the system remains the same.

---

### Part 3 – Sensor Operations

**Q5. What happens if a client sends data in a format other than JSON when using `@Consumes(MediaType.APPLICATION_JSON)`?**

This annotation restricts the method to JSON request bodies only. If a client sends data as `text/plain` or `application/xml`, JAX-RS will reject the request since the content type does not match, typically returning `HTTP 415 Unsupported Media Type`. This ensures the API only receives data in the expected format.

**Q6. Why is `@QueryParam` better than using the path for filtering?**

Query parameters are more appropriate for filtering because they narrow down an existing collection. For example, `/sensors?type=CO2` clearly expresses that the client wants only CO2 sensors from the sensors list. This is cleaner than a path like `/sensors/type/CO2`, and scales better when additional filters (such as status or room) are introduced later.

---

### Part 4 – Deep Nesting with Sub-Resources

**Q7. What are the benefits of the Sub-Resource Locator pattern?**

The Sub-Resource Locator pattern keeps the code organised by separating nested resources into their own classes. In this project, readings are handled in a dedicated resource class rather than combining all sensor and reading logic in one place. This makes the project easier to read, maintain, and extend over time.

---

### Part 5 – Error Handling, Exception Mapping & Logging

**Q8. Why is HTTP 422 more accurate than 404 for a missing linked room inside valid JSON?**

`HTTP 422 Unprocessable Entity` is more suitable because the request itself is valid and the endpoint exists — the problem is that the data inside the request cannot be processed correctly. For example, creating a sensor with a non-existent room ID is a data validation failure, not a missing URL. `404` typically indicates the requested path or resource was not found.

**Q9. What are the risks of exposing Java stack traces to API users?**

Stack traces can expose sensitive internal details such as class names, package paths, method names, file locations, and library versions. Attackers can use this information to better understand the system architecture and identify potential vulnerabilities. APIs should always return safe, generic error messages in production.

**Q10. Why use JAX-RS filters for logging instead of writing `Logger.info()` in every method?**

A JAX-RS filter handles logging for all requests and responses in a single, centralised location. This keeps individual resource methods clean and avoids duplicating the same logging code across every endpoint. It is also far easier to update or modify logging behaviour in one place rather than across the entire codebase.
