package com.epam.esm.exception;

public class EntityNotAddedException extends ServiceException {
    public EntityNotAddedException(String entityName) {
        super(entityName, "not created");
    }
}
