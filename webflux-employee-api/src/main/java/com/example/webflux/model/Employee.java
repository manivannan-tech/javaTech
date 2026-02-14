
package com.example.webflux.model;

public record Employee(
        String id,
        String name,
        String role,
        double salary
) {}
