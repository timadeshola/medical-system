package com.oze.medicalsystem.persistence.entity;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 4:49 PM
 */
class PatientTest {

    private Patient patient;
    private Faker faker;

    @BeforeEach
    public void setup() {
        patient = Patient.builder().build();
        faker = new Faker();
    }

    @Test
    @DisplayName("Patient Entity Test")
    void testPatientEntity() {
        patient = Patient.builder()
                .id(1L)
                .name("John Doe")
                .lastVisitDate(new Timestamp(faker.date().between(AppUtils.parseDateUtil("2000-01-01"), new Date()).getTime()))
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build();

        assertNotNull(patient);
        assertThat(patient.getName()).isEqualTo("John Doe");
    }

}
