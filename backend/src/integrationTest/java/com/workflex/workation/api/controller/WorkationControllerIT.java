package com.workflex.workation.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WorkationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWorkations_returnsSeededWorkationsFromCsv() throws Exception {
        mockMvc.perform(get("/workflex/workation")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // response should be a JSON array
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                // should have at least one element
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)))
                // spot check fields exist on the first element
                .andExpect(jsonPath("$[0].employee").isNotEmpty())
                .andExpect(jsonPath("$[0].origin").isNotEmpty())
                .andExpect(jsonPath("$[0].destination").isNotEmpty())
                .andExpect(jsonPath("$[0].startDate").exists())
                .andExpect(jsonPath("$[0].endDate").exists())
                .andExpect(jsonPath("$[0].workingDays").exists())
                .andExpect(jsonPath("$[0].risk").exists());
    }
}