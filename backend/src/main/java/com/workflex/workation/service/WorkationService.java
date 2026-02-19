package com.workflex.workation.service;

import com.workflex.workation.domain.Workation;
import com.workflex.workation.persistence.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Encapsulates business use-cases around Workations.
 */
@Service
@RequiredArgsConstructor
public class WorkationService {

    private final WorkationRepository repository;

    /**
     * Returns all workations.
     * For this challenge, simple list retrieval is sufficient.
     * TODO: Paginate the workation results as improvement
     */
    public List<Workation> getAllWorkations() {
        return repository.findAll();
    }
}