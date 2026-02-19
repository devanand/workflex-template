package com.workflex.workation.importer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkationCsvReaderTest {

    private final WorkationCsvReader reader = new WorkationCsvReader();

    @Test
    void readRows_readsRowsAndSkipsHeader() throws Exception {
        List<String[]> rows = reader.readRows();

        // Should read at least one record from your real CSV in src/main/resources.
        assertThat(rows).isNotEmpty();

        // Header should NOT be present as a data row.
        // First column is workationId like "w1", not "workationId".
        assertThat(rows.get(0)[0]).isNotEqualTo("workationId");
    }
}