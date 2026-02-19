package com.workflex.workation.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RiskTest {

    @Test
    void fromCsvValue_mapsNoToNoRisk_caseInsensitive() {
        assertThat(Risk.fromCsvValue("no")).isEqualTo(Risk.NO_RISK);
        assertThat(Risk.fromCsvValue("NO")).isEqualTo(Risk.NO_RISK);
        assertThat(Risk.fromCsvValue("No")).isEqualTo(Risk.NO_RISK);
    }

    @Test
    void fromCsvValue_throwsOnUnknownValue() {
        assertThatThrownBy(() -> Risk.fromCsvValue("MAYBE"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}