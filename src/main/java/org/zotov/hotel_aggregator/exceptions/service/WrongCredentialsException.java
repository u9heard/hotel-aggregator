package org.zotov.hotel_aggregator.exceptions.service;

public class WrongCredentialsException extends RuntimeException{
    public WrongCredentialsException(String message) {
        super(message);
    }
}
