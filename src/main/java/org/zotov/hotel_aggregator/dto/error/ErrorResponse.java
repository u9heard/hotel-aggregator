package org.zotov.hotel_aggregator.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class ErrorResponse {
    private Object errors;

    public ErrorResponse() {
        errors = new ArrayList<>();
    }

    @JsonProperty("errors")
    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

//    public void addError(Object errorObject){
//        this.errors.add(errorObject);
//    }
}
