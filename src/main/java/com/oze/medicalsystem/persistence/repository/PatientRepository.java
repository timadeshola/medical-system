package com.oze.medicalsystem.persistence.repository;

import com.oze.medicalsystem.persistence.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:59 PM
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDateCreatedBetween(Timestamp startDate, Timestamp endDate);

    @Query("select u from Patient u where TIMESTAMPDIFF(YEAR , u.dateCreated, CURDATE()) <= 2")
    List<Patient> findAllByPatient2YearOld();
}
