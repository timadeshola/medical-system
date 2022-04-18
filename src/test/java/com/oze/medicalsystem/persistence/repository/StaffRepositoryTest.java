package com.oze.medicalsystem.persistence.repository;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.persistence.entity.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 4:56 PM
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    private Staff staff;
    private Faker faker;

    @BeforeEach
    public void setup() {
        staff = Staff.builder().build();
        faker = new Faker();
    }

    @Test
    @DisplayName("Create Staff Test")
    void testSaveAndFetchStaff() {
        staff = Staff.builder()
                .id(1L)
                .name(faker.name().name())
                .registrationDate(new Timestamp(faker.date().between(AppUtils.parseDateUtil("2000-01-01"), new Date()).getTime()))
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build();

        staff = staffRepository.save(staff);

        Staff fetchStaff = staffRepository.findByUuid(this.staff.getUuid()).get();

        assertNotNull(this.staff);
        assertNotNull(fetchStaff);
        assertNotNull(this.staff.getUuid());
        assertThat(staff).isEqualTo(fetchStaff);
        assertThat(staff.getName()).isEqualTo(fetchStaff.getName());
        assertThat(staff).isExactlyInstanceOf(Staff.class);
        assertThat(fetchStaff).isExactlyInstanceOf(Staff.class);
    }
}
