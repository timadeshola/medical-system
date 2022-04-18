package com.oze.medicalsystem.resource;

import com.oze.medicalsystem.core.annotations.StaffValidation;
import com.oze.medicalsystem.core.constants.AppConstant;
import com.oze.medicalsystem.model.response.AppResponse;
import com.oze.medicalsystem.model.response.PatientResponse;
import com.oze.medicalsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 3:08 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("patient")
public class PatientResource {

    private final PatientService patientService;

    @GetMapping
    @StaffValidation
    public ResponseEntity<AppResponse<List<PatientResponse>>> fetchAllPatientUpTo2Years() {
        List<PatientResponse> response = patientService.fetchAllPatientUpTo2Years();
        return ResponseEntity.ok().body(AppResponse.<List<PatientResponse>>builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message(AppConstant.ResponseCode.OK)
                .build());
    }


    @GetMapping("download/{id}")
    @StaffValidation
    public void downloadPatienceProfile(@PathVariable Long id, HttpServletResponse response) {
        patientService.downloadPatienceProfile(id, response);
    }

    @DeleteMapping
    @StaffValidation
    public ResponseEntity<AppResponse<Boolean>> deletePatients(@RequestParam String startDate, @RequestParam String endDate) {
        Boolean response = patientService.deletePatients(startDate, endDate);
        return ResponseEntity.ok().body(AppResponse.<Boolean>builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message(AppConstant.ResponseCode.OK)
                .build());
    }


}
