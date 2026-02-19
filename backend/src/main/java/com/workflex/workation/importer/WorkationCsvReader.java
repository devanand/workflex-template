package com.workflex.workation.importer;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible only for reading raw CSV lines from classpath.
 * Does not perform mapping or persistence.
 */
@Component
public class WorkationCsvReader {

    private static final String FILE_NAME = "workations.csv";

    /**
     * Reads CSV file and returns raw lines (excluding header).
     */
    public List<String[]> readRows() throws Exception {
        ClassPathResource resource = new ClassPathResource(FILE_NAME);

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            reader.readLine(); // Skip header

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    rows.add(line.split(",", -1));
                }
            }
        }

        return rows;
    }
}