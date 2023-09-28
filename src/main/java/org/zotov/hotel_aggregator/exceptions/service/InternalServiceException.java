package org.zotov.hotel_aggregator.exceptions.service;

public class InternalServiceException extends RuntimeException{
    public InternalServiceException(String message) {
        super(message);
    }
}
