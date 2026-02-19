package com.workflex.workation.api.dto;

import com.workflex.workation.domain.Risk;

import java.time.LocalDate;

/**
 * API DTO returned to the frontend.
 * We avoid returning JPA entities directly to keep persistence concerns out of the API layer.
 */
public record WorkationResponse(
        String workationId,
        String employee,
        String origin,
        String destination,
        LocalDate startDate,
        LocalDate endDate,
        Integer workingDays,
        Risk risk
) {}