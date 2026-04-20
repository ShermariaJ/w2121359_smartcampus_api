package com.api.resource;

import com.api.model.Room;
import com.api.store.DataStore;
import com.api.exception.RoomNotEmptyException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.Collection;

// Handles all room-related API operations
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    // Retrieves all rooms
    @GET
    public Collection<Room> getRooms() {
        return DataStore.rooms.values();
    }

    // Creates a new room
    @POST
    public Response createRoom(Room room) {

        // Store the room in memory
        DataStore.rooms.put(room.getId(), room);

        // Return 201 Created with location header
        return Response.created(URI.create("/api/v1/rooms/" + room.getId()))
                .entity(room)
                .build();
    }

    // Retrieves a specific room by ID
    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        return DataStore.rooms.get(id);
    }

    // Deletes a room by ID
    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        // Get the room from storage
        Room room = DataStore.rooms.get(id);

        // Return 404 if room not found
        if (room == null) return Response.status(404).build();

        // Prevent deletion if room has linked sensors
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException();
        }

        // Remove room from storage
        DataStore.rooms.remove(id);

        return Response.ok().build(); // 200 OK
    }
}