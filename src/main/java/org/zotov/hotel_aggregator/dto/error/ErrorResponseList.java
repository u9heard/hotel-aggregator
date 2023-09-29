package org.zotov.hotel_aggregator.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class ErrorResponseList {
    private Map<String, List<Object>> errors;

    public ErrorResponseList() {
        errors = new TreeMap<>();
        errors.put("errors", new ArrayList<>());
    }

    public ErrorResponseList(Map<String, List<Object>> errors) {
        this.errors = errors;
    }

    @JsonIgnore
    public Map<String, List<Object>> getErrorsMap() {
        return errors;
    }

    @JsonProperty("errors")
    public List<Object> getErrorsList(){
        return errors.get("errors");
    }

    public void addObject(Object o){
        errors.get("errors").add(o);
    }

    public void setErrors(Map<String, List<Object>> errors) {
        this.errors = errors;
    }
}
