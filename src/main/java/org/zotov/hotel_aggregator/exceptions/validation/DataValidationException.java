package org.zotov.hotel_aggregator.exceptions.validation;

import org.springframework.validation.Errors;

public class DataValidationException extends RuntimeException{
    private final Errors errors;

    public DataValidationException(String message, Errors errors){
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
