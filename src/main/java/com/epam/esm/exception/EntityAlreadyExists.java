package com.epam.esm.exception;

public class EntityAlreadyExists extends ServiceException {
    public EntityAlreadyExists(String entityName, String... parameterMap) {
        super(entityName, "already exists with");
    }
}
