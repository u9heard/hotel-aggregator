package org.zotov.hotel_aggregator.exceptions.service;

import java.util.List;

public class ModelMappingException extends RuntimeException {
    private final List<Object> modelList;
    private final Class<?> objectsClass;

    public ModelMappingException(String message, List<Object> modelList, Class<?> objectsClass) {
        super(message);
        this.modelList = modelList;
        this.objectsClass = objectsClass;
    }

    public List<Object> getModelList() {
        return modelList;
    }

    public Class<?> getObjectsClass() {
        return objectsClass;
    }
}
