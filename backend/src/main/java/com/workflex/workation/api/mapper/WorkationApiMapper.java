package com.workflex.workation.api.mapper;

import com.workflex.workation.api.dto.WorkationResponse;
import com.workflex.workation.domain.Workation;
import org.springframework.stereotype.Component;

/**
 * Maps domain entities to API DTOs.
 * Single Responsibility: mapping only.
 */
@Component
public class WorkationApiMapper {

    public WorkationResponse toResponse(Workation workation) {
        return new WorkationResponse(
                workation.getWorkationId(),
                workation.getEmployee(),
                workation.getOrigin(),
                workation.getDestination(),
                workation.getStart(),
                workation.getEnd(),
                workation.getWorkingDays(),
                workation.getRisk()
        );
    }
}