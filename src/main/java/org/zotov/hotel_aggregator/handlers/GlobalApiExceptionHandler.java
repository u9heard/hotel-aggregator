package org.zotov.hotel_aggregator.handlers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zotov.hotel_aggregator.dto.error.ErrorResponse;
import org.zotov.hotel_aggregator.dto.error.ErrorResponseList;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.validation.DataValidationException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;

import java.util.*;

@RestControllerAdvice(basePackages = "org.zotov.hotel_aggregator.controllers.restcontrollers")
public class GlobalApiExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> modelNotFound(ModelNotFoundException exception){
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        exception.getFieldErrors().forEach(fieldError -> {
            errorResponse.addError(fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseList> constraintViolation(ConstraintViolationException exception){
        exception.printStackTrace();
        ErrorResponseList errorResponse = new ErrorResponseList();
        Map<Integer, List<String>> err = new TreeMap<>();
        exception.getConstraintViolations().forEach(constraintViolation -> {

            constraintViolation.getPropertyPath().forEach(node -> {
                if(node.getKind().equals(ElementKind.PROPERTY)){
                    err.computeIfAbsent(node.getIndex(), k -> new ArrayList<>());
                    err.get(node.getIndex()).add(constraintViolation.getMessage());
                }
            });


        });
        errorResponse.addObject(err);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ModelConflictException.class)
    public ResponseEntity<ErrorResponse> modelConflict(ModelConflictException exception){
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ErrorResponse> dataValidation(DataValidationException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        ex.getErrors().getFieldErrors().forEach(fieldError -> {
            errorResponse.addError(fieldError.getField() + ' ' + fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalError(Exception exception){
        exception.printStackTrace();
        System.out.println("Internal");
        return new ResponseEntity<>(new ErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
