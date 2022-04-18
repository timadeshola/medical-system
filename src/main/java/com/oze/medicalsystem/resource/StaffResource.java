package com.oze.medicalsystem.resource;

import com.oze.medicalsystem.core.annotations.StaffValidation;
import com.oze.medicalsystem.core.constants.AppConstant;
import com.oze.medicalsystem.model.request.StaffRequest;
import com.oze.medicalsystem.model.response.AppResponse;
import com.oze.medicalsystem.model.response.StaffResponse;
import com.oze.medicalsystem.service.StaffService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 3:01 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("staff")
@Api(tags = "Staff Resource")
public class StaffResource {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<AppResponse<StaffResponse>> createStaff(@Valid @RequestBody StaffRequest request) {
        StaffResponse response = staffService.createStaff(request);
        return ResponseEntity.ok().body(AppResponse.<StaffResponse>builder()
                .data(response)
                .status(HttpStatus.CREATED.value())
                .message(AppConstant.ResponseCode.CREATED)
                .build());
    }

    @PutMapping("{id}")
    @StaffValidation
    public ResponseEntity<AppResponse<StaffResponse>> updateStaff(@Valid @RequestBody StaffRequest request, @PathVariable Long id) {
        StaffResponse response = staffService.updateStaff(request, id);
        return ResponseEntity.ok().body(AppResponse.<StaffResponse>builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message(AppConstant.ResponseCode.OK)
                .build());
    }

    @GetMapping
    @StaffValidation
    public ResponseEntity<AppResponse<List<StaffResponse>>> fetchAll() {
        List<StaffResponse> response = staffService.fetchAll();
        return ResponseEntity.ok().body(AppResponse.<List<StaffResponse>>builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message(AppConstant.ResponseCode.OK)
                .build());
    }


}
