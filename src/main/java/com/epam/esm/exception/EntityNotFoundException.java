package com.epam.esm.exception;

public class EntityNotFoundException  extends ServiceException{
    public EntityNotFoundException(String entityName) {
        super(entityName, "not found");
    }
}
