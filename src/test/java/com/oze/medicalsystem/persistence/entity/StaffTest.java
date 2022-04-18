package com.oze.medicalsystem.persistence.entity;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 4:49 PM
 */
class StaffTest {

    private Staff staff;
    private Faker faker;

    @BeforeEach
    public void setup() {
        staff = Staff.builder().build();
        faker = new Faker();
    }

    @Test
    @DisplayName("Staff Entity Test")
    void testStaffEntity() {
        staff = Staff.builder()
                .id(1L)
                .name("John Doe")
                .uuid(UUID.randomUUID().toString())
                .registrationDate(new Timestamp(faker.date().between(AppUtils.parseDateUtil("2000-01-01"), new Date()).getTime()))
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build();

        assertNotNull(staff);
        assertThat(staff.getName()).isEqualTo("John Doe");
    }
}
