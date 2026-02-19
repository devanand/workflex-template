package com.workflex.workation.importer;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Converts raw CSV row data into a Workation entity.
 */
@Component
public class WorkationMapper {

    public Workation toEntity(String[] row) {

        Workation workation = new Workation();
        workation.setWorkationId(row[0]);
        workation.setEmployee(row[1]);
        workation.setOrigin(row[2]);
        workation.setDestination(row[3]);
        workation.setStart(LocalDate.parse(row[4]));
        workation.setEnd(LocalDate.parse(row[5]));
        workation.setWorkingDays(Integer.parseInt(row[6]));
        workation.setRisk(Risk.fromCsvValue(row[7]));

        return workation;
    }
}