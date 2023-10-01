package org.zotov.hotel_aggregator.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zotov.hotel_aggregator.dto.error.ErrorResponse;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.validation.DataValidationException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.mappers.ErrorResponseMapper;

@RestControllerAdvice(basePackages = "org.zotov.hotel_aggregator.controllers.restcontrollers")
public class GlobalApiExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> modelNotFound(ModelNotFoundException exception){
        return ResponseEntity.badRequest().body(ErrorResponseMapper.fromString(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(ErrorResponseMapper.fromFieldErrorsList(exception.getFieldErrors()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> messageNotReadable(HttpMessageNotReadableException exception){
        return ResponseEntity.badRequest().body(ErrorResponseMapper.fromString("Check input data"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolation(ConstraintViolationException exception){
        exception.printStackTrace();

        return ResponseEntity.badRequest().body(ErrorResponseMapper.fromConstraintViolation(exception.getConstraintViolations()));
    }

    @ExceptionHandler(ModelConflictException.class)
    public ResponseEntity<ErrorResponse> modelConflict(ModelConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseMapper.fromString(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalError(Exception exception){
        exception.printStackTrace();
        System.out.println("Internal");
        return new ResponseEntity<>(ErrorResponseMapper.fromString("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
