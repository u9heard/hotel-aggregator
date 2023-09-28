package org.zotov.hotel_aggregator.dto.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    List<String> errors;

    public ErrorResponse(){
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(String error){
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
