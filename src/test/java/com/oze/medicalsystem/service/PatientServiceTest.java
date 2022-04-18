package com.oze.medicalsystem.service;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.model.request.PatientRequest;
import com.oze.medicalsystem.model.response.PatientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.oze.medicalsystem.core.constants.AppConstant.DateFormatters.AGES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 5:42 PM
 */
@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    private PatientRequest request;
    private Faker faker;

    @BeforeEach
    public void setup() {
        request = PatientRequest.builder().build();
        faker = new Faker();
    }

    @Test
    @DisplayName("Fetch All Patient Up to 2 Years Service Test")
    @Order(0)
    void testFetchAllPatientUpTo2Years() {
        List<PatientResponse> response = patientService.fetchAllPatientUpTo2Years();
        assertNotNull(response);
        assertThat(response.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("Delete Patient Record Service Test")
    @Order(1)
    void testDeletePatients() {
        Boolean response = patientService.deletePatients("2000-01-01", "2022-04-30");
        assertNotNull(response);
        assertTrue(response);
    }

}
