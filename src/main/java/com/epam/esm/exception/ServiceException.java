package com.epam.esm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String entityName) {
        super(entityName);
    }
}
