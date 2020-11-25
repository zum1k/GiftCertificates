package com.epam.esm.exception;

import lombok.Getter;

@Getter
public class EntityAlreadyExists extends ServiceException {
    private final int error_code = 40001;

    public EntityAlreadyExists(String entityName) {
        super(entityName);
    }
}
