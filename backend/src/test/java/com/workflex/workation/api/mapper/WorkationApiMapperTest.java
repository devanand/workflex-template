package com.workflex.workation.api.mapper;

import com.workflex.workation.api.dto.WorkationResponse;
import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WorkationApiMapperTest {

    private final WorkationApiMapper mapper = new WorkationApiMapper();

    @Test
    void toResponse_mapsEntityToDto() {
        Workation w = new Workation();
        w.setWorkationId("w99");
        w.setEmployee("A");
        w.setOrigin("Germany");
        w.setDestination("India");
        w.setStart(LocalDate.of(2024, 1, 1));
        w.setEnd(LocalDate.of(2024, 1, 10));
        w.setWorkingDays(7);
        w.setRisk(Risk.HIGH);

        WorkationResponse dto = mapper.toResponse(w);

        assertThat(dto.workationId()).isEqualTo("w99");
        assertThat(dto.employee()).isEqualTo("A");
        assertThat(dto.origin()).isEqualTo("Germany");
        assertThat(dto.destination()).isEqualTo("India");
        assertThat(dto.startDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(dto.endDate()).isEqualTo(LocalDate.of(2024, 1, 10));
        assertThat(dto.workingDays()).isEqualTo(7);
        assertThat(dto.risk()).isEqualTo(Risk.HIGH);
    }
}