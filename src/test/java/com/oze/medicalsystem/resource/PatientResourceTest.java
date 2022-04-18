package com.oze.medicalsystem.resource;

import com.oze.medicalsystem.service.PatientService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.oze.medicalsystem.core.constants.AppConstant.ResponseCode.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 5:55 PM
 */
@WebMvcTest(PatientResource.class)
class PatientResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @BeforeEach
    public void setup() {

    }


    @SneakyThrows
    @Test
    @DisplayName("Fetch All Patient Up to 2 Years Endpoint - Test")
    void testFetchAllPatientUpTo2YearsEndpoint() {

        mockMvc.perform(get("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(OK))
                .andExpect(jsonPath("$.status").value(200));
    }

    @SneakyThrows
    @Test
    @DisplayName("Delete Patients With Date Range Endpoint - Test")
    void testDeletePatientsEndpoint() {

        mockMvc.perform(delete("/patient")
                        .queryParam("startDate", "2020-01-01")
                        .queryParam("endDate", "2022-01-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(OK))
                .andExpect(jsonPath("$.status").value(200));
    }


}
