package com.epam.esm.exception;

import lombok.Getter;

@Getter
public class EntityNotUpdatedException extends ServiceException {
    private final int errorCode = 40004;
    private final long entityId;

    public EntityNotUpdatedException(String entityName, long entityId) {
        super(entityName);
        this.entityId = entityId;
    }
}
