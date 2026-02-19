package com.example.application.api.dto;

import java.time.Instant;

public record PingResponse(String status, Instant timestamp) {}
