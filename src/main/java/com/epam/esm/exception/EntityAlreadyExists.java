package com.epam.esm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


public class EntityAlreadyExists extends ServiceException {
    private final int error_code = 40001;

    public EntityAlreadyExists(String entityName) {
        super(entityName);
    }

    public int getError_code() {
        return this.error_code;
    }
}
