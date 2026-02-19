package com.workflex.workation.persistence;

import com.workflex.workation.domain.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository abstraction for accessing Workation data.
 * Spring Data JPA provides implementation automatically.
 */
public interface WorkationRepository extends JpaRepository<Workation, Long> {

    /**
     * Used to avoid duplicate imports.
     */
    Optional<Workation> findByWorkationId(String workationId);
}