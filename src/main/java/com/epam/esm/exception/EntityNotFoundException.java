package com.epam.esm.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends ServiceException {
    private final int errorCode = 40404;
    private final long entityId;

    public EntityNotFoundException(String entityName, long entityId) {
        super(entityName);
        this.entityId = entityId;
    }

}

