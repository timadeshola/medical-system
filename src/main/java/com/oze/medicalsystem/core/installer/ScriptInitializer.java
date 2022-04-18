package com.oze.medicalsystem.core.installer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 7:20 PM
 */
//@Service
@RequiredArgsConstructor
public class ScriptInitializer {

    private final DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        ResourceDatabasePopulator staffDatabasePopulator = new ResourceDatabasePopulator(true, false, "UTF-8", new ClassPathResource("staff.sql"));
        ResourceDatabasePopulator patientDatabasePopulator = new ResourceDatabasePopulator(true, false, "UTF-8", new ClassPathResource("patient.sql"));
        staffDatabasePopulator.execute(dataSource);
        patientDatabasePopulator.execute(dataSource);
    }

}
