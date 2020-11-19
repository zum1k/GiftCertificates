package com.epam.esm.exception;

public class EntityNotAddedException extends ServiceException {
    private final int errorCode = 40002;

    public EntityNotAddedException(String entityName) {
        super(entityName);
    }
}
