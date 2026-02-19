package com.workflex.workation.domain;

/**
 * Represents the risk classification of a workation.
 */
public enum Risk {
    HIGH,
    LOW,
    NO_RISK;

    public static Risk fromCsvValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Risk value cannot be null");
        }

        return switch (value.trim().toUpperCase()) {
            case "HIGH" -> HIGH;
            case "LOW" -> LOW;
            case "NO" -> NO_RISK;       // CSV uses NO
            case "NO_RISK" -> NO_RISK;  // Future-proof
            default -> throw new IllegalArgumentException(
                    "Unknown risk value from CSV: " + value
            );
        };
    }
}