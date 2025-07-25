package com.dharaneesh.trade_nest.exception;

public class ResourceNotFoundException extends RuntimeException{

    Long fieldId;
    String fieldName;
    String resourceName;

    public ResourceNotFoundException(Long fieldId, String fieldName,String resourceName) {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldId));
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.resourceName=resourceName;
    }
}
