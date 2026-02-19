package com.workflex.workation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a workation record stored in the database.
 * This entity mirrors the structure of the CSV file.
 */
@Entity
@Table(
        name = "workations",
        uniqueConstraints = @UniqueConstraint(columnNames = "workationId")
)
@Getter
@Setter
public class Workation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * External business identifier from CSV.
     * Must be unique to ensure idempotent imports.
     */
    @Column(nullable = false, unique = true)
    private String workationId;

    @Column(nullable = false)
    private String employee;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate start;

    @Column(name = "end_date", nullable = false)
    private LocalDate end;

    @Column(nullable = false)
    private Integer workingDays;

    @Enumerated(EnumType.STRING)
    @Column(name="risk", nullable = false, length=20)
    private Risk risk;
}