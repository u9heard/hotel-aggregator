package org.zotov.hotel_aggregator.mappers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.zotov.hotel_aggregator.dto.error.ErrorResponse;

import java.util.*;

public class ErrorResponseMapper {
    public static ErrorResponse fromConstraintViolation(Set<ConstraintViolation<?>> constraintViolations){
        ErrorResponse errorResponse = new ErrorResponse();

        Map<String, Object> fieldErrorsMap = new TreeMap<>();

        Map<Integer, List<Object>> fieldErrorMap = new TreeMap<>();
        List<String> generalErrorList = new ArrayList<>();


        constraintViolations.forEach(constraintViolation -> {
            boolean propertyFind = false;

            for(Path.Node node : constraintViolation.getPropertyPath()){
                if(node.getKind().equals(ElementKind.PROPERTY)){
                    fieldErrorMap.computeIfAbsent(node.getIndex(), k -> new ArrayList<>());
                    fieldErrorMap.get(node.getIndex()).add(constraintViolation.getMessage());
                    propertyFind = true;
                }
            }

            if(propertyFind == false){
               generalErrorList.add(constraintViolation.getMessage());
            }
        });

        fieldErrorsMap.put("fieldErrors", fieldErrorMap);
        fieldErrorsMap.put("generalErrors", generalErrorList);
        errorResponse.setErrors(fieldErrorsMap);
        //errorResponse.addError(generalErrors);
        System.out.println("Size = " + fieldErrorsMap.size());
        return errorResponse;
    }

    public static ErrorResponse fromString(String message){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrors(Map.of("error", message));
        return errorResponse;
    }

    public static ErrorResponse fromFieldErrorsList(List<FieldError> fieldErrors){
        List<String> errors = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrors(errors);
        return errorResponse;
    }
}
