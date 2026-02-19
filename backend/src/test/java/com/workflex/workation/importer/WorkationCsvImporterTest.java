package com.workflex.workation.importer;

import com.workflex.workation.domain.Workation;
import com.workflex.workation.persistence.WorkationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WorkationCsvImporterTest {

    @Test
    void run_skipsImportWhenDbNotEmpty() throws Exception {
        WorkationRepository repo = mock(WorkationRepository.class);
        WorkationCsvReader reader = mock(WorkationCsvReader.class);
        WorkationMapper mapper = mock(WorkationMapper.class);

        when(repo.count()).thenReturn(10L);

        WorkationCsvImporter importer = new WorkationCsvImporter(repo, reader, mapper);
        importer.run(new DefaultApplicationArguments(new String[]{}));

        verifyNoInteractions(reader);
        verifyNoInteractions(mapper);
        verify(repo, never()).save(any());
        verify(repo, never()).saveAll(any());
    }

    @Test
    void run_importsWhenDbEmpty_andSavesNewRecords() throws Exception {
        WorkationRepository repo = mock(WorkationRepository.class);
        WorkationCsvReader reader = mock(WorkationCsvReader.class);
        WorkationMapper mapper = mock(WorkationMapper.class);

        when(repo.count()).thenReturn(0L);

        // 2 CSV rows
        when(reader.readRows()).thenReturn(List.of(
                new String[]{"w1", "Emp1", "Germany", "Spain", "2024-01-01", "2024-01-02", "2", "LOW"},
                new String[]{"w2", "Emp2", "India", "Germany", "2024-02-01", "2024-02-10", "8", "HIGH"}
        ));

        Workation e1 = new Workation(); e1.setWorkationId("w1");
        Workation e2 = new Workation(); e2.setWorkationId("w2");

        when(mapper.toEntity(any())).thenReturn(e1, e2);

        when(repo.findByWorkationId("w1")).thenReturn(Optional.empty());
        when(repo.findByWorkationId("w2")).thenReturn(Optional.empty());

        WorkationCsvImporter importer = new WorkationCsvImporter(repo, reader, mapper);
        importer.run(new DefaultApplicationArguments(new String[]{}));

        ArgumentCaptor<Workation> captor = ArgumentCaptor.forClass(Workation.class);
        verify(repo, times(2)).save(captor.capture());

        assertThat(captor.getAllValues())
                .extracting(Workation::getWorkationId)
                .containsExactlyInAnyOrder("w1", "w2");
    }

    @Test
    void run_doesNotSaveWhenRecordAlreadyExists() throws Exception {
        WorkationRepository repo = mock(WorkationRepository.class);
        WorkationCsvReader reader = mock(WorkationCsvReader.class);
        WorkationMapper mapper = mock(WorkationMapper.class);

        when(repo.count()).thenReturn(0L);

        when(reader.readRows()).thenReturn(List.<String[]>of(
                new String[]{"w1", "Emp1", "Germany", "Spain", "2024-01-01", "2024-01-02", "2", "LOW"}
        ));

        Workation e1 = new Workation(); e1.setWorkationId("w1");
        when(mapper.toEntity(any())).thenReturn(e1);

        when(repo.findByWorkationId("w1")).thenReturn(Optional.of(new Workation()));

        WorkationCsvImporter importer = new WorkationCsvImporter(repo, reader, mapper);
        importer.run(new DefaultApplicationArguments(new String[]{}));

        verify(repo, never()).save(any());
    }
}