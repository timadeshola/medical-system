package com.oze.medicalsystem.core.exceptions;

import com.oze.medicalsystem.core.exceptions.model.ErrorDetails;
import com.oze.medicalsystem.core.exceptions.model.ValidationError;
import com.oze.medicalsystem.model.response.AppResponse;
import org.modelmapper.MappingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.NestedServletException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:32 PM
 */
@RestControllerAdvice
public class ResourceAdvice {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<AppResponse<ErrorDetails>> handleCustomException(CustomException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(AppResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<AppResponse<ErrorDetails>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(AppResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());

    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<AppResponse<ErrorDetails>> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(ex.getStatus().value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(AppResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());

    }

    @ExceptionHandler(NoResultException.class)
    public final ResponseEntity<AppResponse<ErrorDetails>> handleNoResultException(NoResultException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(AppResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());

    }

    @ExceptionHandler({MappingException.class})
    public final ResponseEntity<AppResponse<ErrorDetails>> handleMappingException(MappingException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, NestedServletException.class})
    public ResponseEntity<AppResponse<ErrorDetails>> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AppResponse<ErrorDetails>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<AppResponse<ErrorDetails>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<AppResponse<ErrorDetails>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<AppResponse<ErrorDetails>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .details(request.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(errorDetails.getCode())
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<AppResponse<List<ErrorDetails>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
                errors.add(ValidationError.builder()
                        .field(((FieldError) error).getField())
                        .rejectedValue(((FieldError) error).getRejectedValue())
                        .objectName(error.getObjectName())
                        .build()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppResponse.<List<ErrorDetails>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ErrorDetails.builder()
                        .message("Validation Error")
                        .code(HttpStatus.BAD_REQUEST.value())
                        .details(request.getDescription(true))
                        .timestamp(LocalDateTime.now())
                        .validation(errors)
                        .build())
                .message("Validation error")
                .build());

    }
}
