package com.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Main {

    public static void main(String[] args) {

        String BASE_URI = "http://localhost:8082/";

        // Scan everything under com.api
        ResourceConfig config = new ResourceConfig()
                .packages("com.api");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI), config
        );

        System.out.println("Server running at " + BASE_URI + "api/v1");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}