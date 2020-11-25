package com.epam.esm.exception;

import lombok.Getter;

@Getter
public class EntityNotDeletedException extends ServiceException {
    private final int errorCode = 40003;
    private final long entityId;

    public EntityNotDeletedException(String entityName, long entityId) {
        super(entityName);
        this.entityId = entityId;
    }
}
