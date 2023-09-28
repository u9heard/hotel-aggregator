package org.zotov.hotel_aggregator.exceptions.service;

public class ModelNotFoundException extends RuntimeException{

    /**
     * @param message Client will see message from this exception
     */
    public ModelNotFoundException(String message) {
        super(message);
    }
}
