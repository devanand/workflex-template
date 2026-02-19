package com.workflex.workation.api;

import com.workflex.workation.api.dto.WorkationResponse;
import com.workflex.workation.api.mapper.WorkationApiMapper;
import com.workflex.workation.service.WorkationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller for Workation endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/workflex")
public class WorkationController {

    private final WorkationService service;
    private final WorkationApiMapper mapper;

    /**
     * GET /workflex/workation
     */
    @GetMapping("/workation")
    public List<WorkationResponse> getWorkations() {
        return service.getAllWorkations()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}