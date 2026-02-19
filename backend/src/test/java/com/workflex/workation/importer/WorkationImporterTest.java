package com.workflex.workation.importer;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorkationMapperTest {

    private final WorkationMapper mapper = new WorkationMapper();

    @Test
    void toEntity_mapsCsvRowCorrectly_andMapsRiskNOtoNORISK() {
        String[] row = {
                "w1",
                "Steffen Jacobs",
                "Germany",
                "United States",
                "2024-01-02",
                "2024-01-22",
                "15",
                "NO"
        };

        Workation entity = mapper.toEntity(row);

        assertThat(entity.getWorkationId()).isEqualTo("w1");
        assertThat(entity.getEmployee()).isEqualTo("Steffen Jacobs");
        assertThat(entity.getOrigin()).isEqualTo("Germany");
        assertThat(entity.getDestination()).isEqualTo("United States");
        assertThat(entity.getStart()).hasToString("2024-01-02");
        assertThat(entity.getEnd()).hasToString("2024-01-22");
        assertThat(entity.getWorkingDays()).isEqualTo(15);
        assertThat(entity.getRisk()).isEqualTo(Risk.NO_RISK);
    }
}