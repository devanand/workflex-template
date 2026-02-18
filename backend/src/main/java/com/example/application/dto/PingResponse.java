package com.example.application.dto;

import java.time.Instant;

public record PingResponse(String status, Instant timestamp) {}
