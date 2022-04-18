package com.oze.medicalsystem.core.annotations;

import com.oze.medicalsystem.core.constants.AppConstant;
import com.oze.medicalsystem.core.exceptions.CustomException;
import com.oze.medicalsystem.persistence.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.oze.medicalsystem.core.constants.AppConstant.Headers.AUTHORIZATION;
import static com.oze.medicalsystem.core.constants.AppConstant.ResponseCode.UNKNOWN_STAFF;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 2:28 PM
 */
@Service
@Aspect
@RequiredArgsConstructor
@Slf4j
public class StaffValidationAspect {

    private final StaffRepository staffRepository;
    private final HttpServletRequest httpServletRequest;

    @Pointcut("@annotation(com.oze.medicalsystem.core.annotations.StaffValidation)")
    public void postAction() {
    }

    @After("postAction()")
    public void validateStaff() {
        String staffUUID = httpServletRequest.getHeader(AUTHORIZATION);
        if (Objects.isNull(staffUUID)) {
            throw new CustomException(UNKNOWN_STAFF, HttpStatus.UNAUTHORIZED);
        }
        staffRepository.findByUuid(staffUUID).<CustomException>orElseThrow(() -> {
            throw new CustomException(AppConstant.ResponseCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        });
    }

}
