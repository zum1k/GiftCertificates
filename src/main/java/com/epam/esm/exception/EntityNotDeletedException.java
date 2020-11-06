package com.epam.esm.exception;

public class EntityNotDeletedException extends ServiceException{
    public EntityNotDeletedException(String entityName) {
        super(entityName, "not deleted");
    }
}
