package com.example.application.api.dto;

import java.time.Instant;

public record ItemResponse(
        Long id,
        String name,
        Instant createdAt
) {}