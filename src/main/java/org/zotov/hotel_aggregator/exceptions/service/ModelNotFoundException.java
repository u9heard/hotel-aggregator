package org.zotov.hotel_aggregator.exceptions.service;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message) {
        super(message);
    }
}
