package com.api.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// Configures the base path for all API endpoints
@ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {
}