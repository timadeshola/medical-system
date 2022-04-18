package com.oze.medicalsystem.service;

import com.oze.medicalsystem.model.response.PatientResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 2:39 PM
 */
public interface PatientService {

    List<PatientResponse> fetchAllPatientUpTo2Years();

    void downloadPatienceProfile(Long id, HttpServletResponse response);

    Boolean deletePatients(String startDate, String endDate);
}
