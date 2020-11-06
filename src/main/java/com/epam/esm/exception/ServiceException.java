package com.epam.esm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String entityName, String message) {
        super(generateMessage(entityName, message));
    }

    private static String generateMessage(String entity, String message) {
        return entity + " " + message;
    }
}
