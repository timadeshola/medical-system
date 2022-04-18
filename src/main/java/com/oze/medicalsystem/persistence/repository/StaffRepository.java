package com.oze.medicalsystem.persistence.repository;

import com.oze.medicalsystem.persistence.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:58 PM
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByUuid(String uuid);
}
