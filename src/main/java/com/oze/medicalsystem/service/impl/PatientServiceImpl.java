package com.oze.medicalsystem.service.impl;

import com.oze.medicalsystem.core.exceptions.CustomException;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.core.utils.ExportUtils;
import com.oze.medicalsystem.core.utils.ModelMapperUtils;
import com.oze.medicalsystem.model.response.PatientResponse;
import com.oze.medicalsystem.persistence.entity.Patient;
import com.oze.medicalsystem.persistence.repository.PatientRepository;
import com.oze.medicalsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 2:39 PM
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponse> fetchAllPatientUpTo2Years() {
        return ModelMapperUtils.mapAll(patientRepository.findAllByPatient2YearOld(), PatientResponse.class);
    }

    @Override
    public void downloadPatienceProfile(Long id, HttpServletResponse response) {
        Patient patient = patientRepository.findById(id).<CustomException>orElseThrow(() -> {
            throw new CustomException("Patient record cannot be found", HttpStatus.NOT_FOUND);
        });
        ExportUtils.csvWriter(response, List.of(patient), patient.getName().concat(".csv"));
    }

    @Override
    public Boolean deletePatients(String startDate, String endDate) {
        List<Patient> patients = patientRepository.findAllByDateCreatedBetween(AppUtils.parseDate(startDate), AppUtils.parseDate(endDate));
        if (patients.isEmpty()) {
            throw new CustomException("No Record Found", HttpStatus.NOT_FOUND);
        }
        patientRepository.deleteAllInBatch(patients);
        return true;
    }

}
