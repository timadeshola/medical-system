package com.oze.medicalsystem.service.impl;

import com.oze.medicalsystem.core.exceptions.CustomException;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.core.utils.ModelMapperUtils;
import com.oze.medicalsystem.model.request.StaffRequest;
import com.oze.medicalsystem.model.response.StaffResponse;
import com.oze.medicalsystem.persistence.entity.Staff;
import com.oze.medicalsystem.persistence.repository.StaffRepository;
import com.oze.medicalsystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public StaffResponse createStaff(StaffRequest request) {
        return ModelMapperUtils.map(staffRepository.save(Staff.builder()
                .name(request.getName())
                .registrationDate(AppUtils.parseDate(request.getRegistrationDate()))
                .build()), StaffResponse.class);
    }

    @Override
    public StaffResponse updateStaff(StaffRequest request, Long id) {
        Staff staff = staffRepository.findById(id).<CustomException>orElseThrow(() -> {
            throw new CustomException("Staff info cannot be found", HttpStatus.NOT_FOUND);
        });
        if (StringUtils.isNotBlank(request.getName())) {
            staff.setName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getRegistrationDate())) {
            staff.setRegistrationDate(AppUtils.parseDate(request.getRegistrationDate()));
        }
        return ModelMapperUtils.map(staffRepository.save(staff), StaffResponse.class);
    }

    @Override
    public List<StaffResponse> fetchAll() {
        return ModelMapperUtils.mapAll(staffRepository.findAll(), StaffResponse.class);
    }
}
