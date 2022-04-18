package com.oze.medicalsystem.core.installer;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.persistence.entity.Patient;
import com.oze.medicalsystem.persistence.entity.Staff;
import com.oze.medicalsystem.persistence.repository.PatientRepository;
import com.oze.medicalsystem.persistence.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import static com.oze.medicalsystem.core.constants.AppConstant.DateFormatters.AGES;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:37 PM
 */
@Service
@RequiredArgsConstructor
public class DefaultInstaller implements ApplicationListener<ContextRefreshedEvent> {

    private final Faker faker = new Faker();
    private final StaffRepository staffRepository;
    private final PatientRepository patientRepository;
    private boolean alreadySetup;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            createStaffIfNotExist();
        }
        for (int i = 0; i < 200; i++) {
            createPatientIfNotExist();
        }


        alreadySetup = true;
    }

    protected void createStaffIfNotExist() {
        long count = staffRepository.count();
        if (count > 4) {
            return;
        }
        staffRepository.save(Staff.builder()
                .name(faker.name().name())
                .registrationDate(new Timestamp(faker.date().between(AppUtils.parseDateUtil("2000-01-01"), new Date()).getTime()))
                .build());
    }

    protected void createPatientIfNotExist() {
        long count = patientRepository.count();
        if (count > 199) {
            return;
        }
        Random random = new SecureRandom();
        patientRepository.save(Patient.builder()
                .name(faker.name().name())
                .age(AGES.get(random.nextInt(AGES.size())))
                .lastVisitDate(new Timestamp(faker.date().between(AppUtils.parseDateUtil("2000-01-01"), new Date()).getTime()))
                .build());
    }
}
