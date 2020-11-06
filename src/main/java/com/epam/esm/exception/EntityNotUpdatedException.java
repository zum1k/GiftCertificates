package com.epam.esm.exception;

public class EntityNotUpdatedException extends ServiceException {
    public EntityNotUpdatedException(String entityName) {
        super(entityName, "not updated ");
    }
}
