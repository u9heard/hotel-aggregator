package org.zotov.hotel_aggregator.exceptions.service;

public class ModelConflictException extends RuntimeException {
    public ModelConflictException(String message) {
        super(message);
    }
}
