package com.workflex.workation.service;

import com.workflex.workation.domain.Workation;
import com.workflex.workation.persistence.WorkationRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WorkationServiceTest {

    @Test
    void getAllWorkations_delegatesToRepository() {
        WorkationRepository repo = mock(WorkationRepository.class);
        WorkationService service = new WorkationService(repo);

        when(repo.findAll()).thenReturn(List.of(new Workation(), new Workation()));

        List<Workation> result = service.getAllWorkations();

        assertThat(result).hasSize(2);
        verify(repo, times(1)).findAll();
    }
}