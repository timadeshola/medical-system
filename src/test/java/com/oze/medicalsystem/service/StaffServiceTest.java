package com.oze.medicalsystem.service;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.model.request.StaffRequest;
import com.oze.medicalsystem.model.response.StaffResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 5:30 PM
 */
@SpringBootTest
class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    private StaffRequest request;
    private Faker faker;

    @BeforeEach
    public void setup() {
        request = StaffRequest.builder().build();
        faker = new Faker();
    }

    @Test
    @DisplayName("Create Staff Service Test")
    void testCreateStaff() {
        request = StaffRequest.builder()
                .name(faker.name().name())
                .registrationDate(faker.date().between(AppUtils.parseDateUtil("2022-02-01"), new Date()).toString())
                .build();

        StaffResponse response = staffService.createStaff(request);

        assertNotNull(this.request);
        assertNotNull(response);
        assertNotNull(response.getUuid());
        assertThat(request.getName()).isEqualTo(response.getName());
        assertThat(request).isExactlyInstanceOf(StaffRequest.class);
        assertThat(response).isExactlyInstanceOf(StaffResponse.class);
    }

    @Test
    @DisplayName("Update Staff Service Test")
    void testUpdateStaff() {
        request = StaffRequest.builder()
                .name(faker.name().name())
                .registrationDate(faker.date().between(AppUtils.parseDateUtil("2022-02-01"), new Date()).toString())
                .build();
        StaffResponse response = staffService.updateStaff(request, 1L);

        assertNotNull(this.request);
        assertNotNull(response);
        assertNotNull(response.getUuid());
        assertThat(request.getName()).isEqualTo(response.getName());
        assertThat(request).isExactlyInstanceOf(StaffRequest.class);
        assertThat(response).isExactlyInstanceOf(StaffResponse.class);
    }

    @Test
    @DisplayName("Fetch All Staff Service Test")
    void testFetchAllStaff() {

        List<StaffResponse> response = staffService.fetchAll();

        assertNotNull(response);
    }

}
