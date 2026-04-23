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

### Part 1 Setup & Discovery
 
**Q1. Explain the default lifecycle of a JAX-RS Resource class. Is a new instance created per request or treated as a singleton? How does this affect in-memory data structures?**
 
By default, JAX-RS resource classes are usually created for each request. This means a new object is made when a request comes in. This helps avoid problems with shared data inside the resource class. However, in this coursework the main data is stored in shared collections like `HashMap` and `ArrayList`, so that data stays available between requests. Because more than one request could use the data at the same time, updates should be handled carefully to avoid conflicts or lost data.
 
**Q2. Why is Hypermedia (HATEOAS) considered advanced RESTful design? How does it help clients?**
 
Hypermedia means the API response gives links or paths to other related resources. It is seen as a strong REST feature because clients can understand where to go next without relying only on outside documentation. In my API, the discovery endpoint shows links such as `/rooms` and `/sensors`, which helps users navigate the system more easily.
 
---
 
### Part 2 Room Management
 
**Q3. What are the implications of returning only room IDs vs full room objects?**
 
Returning only room IDs makes the response smaller and faster because less data is sent. However, the client would need extra requests to get more details about each room. Returning full room objects gives all important information at once, such as room name and capacity, but uses more bandwidth. In this project, returning full room objects is reasonable because the amount of data is not very large.
 
**Q4. Is DELETE idempotent in your implementation?**
 
Yes, DELETE is idempotent because repeating the same delete request should leave the final result the same. If a room is deleted once, sending the same request again will not delete it again because it no longer exists. The second request may return a not found response, but the final state of the system is still the same.
 
---
 
### Part 3 Sensor Operations
 
**Q5. What happens if a client sends data in a format other than JSON when using `@Consumes(MediaType.APPLICATION_JSON)`?**
 
This annotation means the method only accepts JSON request bodies. If a client sends data as `text/plain` or `application/xml`, JAX-RS will normally reject the request because the content type does not match. This usually returns HTTP 415 Unsupported Media Type. It helps make sure the API receives data in the correct format.
 
**Q6. Why is `@QueryParam` better than using the path for filtering?**
 
Query parameters are better for filtering because they are used to narrow down an existing collection. For example, `/sensors?type=CO2` means the client wants only CO2 sensors from the sensors list. This is cleaner than using a path like `/sensors/type/CO2`, especially if more filters are added later such as status or room.
 
---
 
### Part 4 Deep Nesting with Sub-Resources
 
**Q7. What are the benefits of the Sub-Resource Locator pattern?**
 
The Sub-Resource Locator pattern helps keep the code organized by separating nested resources into their own classes. In this coursework, readings can be handled in a separate resource instead of putting all sensor and reading logic in one large class. This makes the project easier to read, manage, and expand later.
 
---
 
### Part 5 Error Handling, Exception Mapping & Logging
 
**Q8. Why is HTTP 422 more accurate than 404 for a missing linked room inside valid JSON?**
 
HTTP 422 is more suitable because the request itself is valid and the endpoint exists, but the data inside the request cannot be processed correctly. For example, creating a sensor with a room ID that does not exist is a data validation issue. A 404 usually means the requested URL or resource cannot be found.
 
**Q9. What are the risks of exposing Java stack traces to API users?**
 
Showing stack traces can reveal internal information about the system. This may include class names, package names, file paths, method names, or libraries being used. Attackers could use this information to understand the system better and look for weaknesses. Because of this, APIs should return safe error messages instead.
 
**Q10. Why use JAX-RS filters for logging instead of writing `Logger.info()` in every method?**
 
Using JAX-RS filters for logging is better because many endpoints need the same logging behavior. A filter can log all requests and responses in one place. This keeps the resource methods cleaner and avoids repeating the same logging code in every method. It is also easier to maintain later.
