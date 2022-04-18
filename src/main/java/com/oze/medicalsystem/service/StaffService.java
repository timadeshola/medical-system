package com.oze.medicalsystem.service;

import com.oze.medicalsystem.model.request.StaffRequest;
import com.oze.medicalsystem.model.response.StaffResponse;

import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 2:39 PM
 */
public interface StaffService {

    StaffResponse createStaff(StaffRequest request);

    StaffResponse updateStaff(StaffRequest request, Long id);

    List<StaffResponse> fetchAll();
}
