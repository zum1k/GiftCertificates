package com.epam.esm.exception;

import lombok.Getter;

@Getter
public class EntityNotAddedException extends ServiceException {
    private final int errorCode = 40002;

    public EntityNotAddedException(String entityName) {
        super(entityName);
    }
}
