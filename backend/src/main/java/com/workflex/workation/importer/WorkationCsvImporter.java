package com.workflex.workation.importer;

import com.workflex.workation.domain.Workation;
import com.workflex.workation.persistence.WorkationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Coordinates CSV import at application startup.
 * Responsible for orchestration only (not parsing or mapping).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WorkationCsvImporter implements ApplicationRunner {

    private final WorkationRepository repository;
    private final WorkationCsvReader reader;
    private final WorkationMapper mapper;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        if (repository.count() > 0) {
            log.info("Workations already exist. Skipping CSV import.");
            return;
        }

        log.info("Starting workations CSV import...");

        List<String[]> rows = reader.readRows();

        for (String[] row : rows) {
            Workation workation = mapper.toEntity(row);

            // Avoid duplicates in edge cases
            repository.findByWorkationId(workation.getWorkationId())
                    .orElseGet(() -> repository.save(workation));
        }

        log.info("CSV import completed. {} records imported.", rows.size());
    }
}