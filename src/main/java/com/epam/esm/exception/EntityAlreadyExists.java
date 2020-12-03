package com.epam.esm.exception;

public class EntityAlreadyExists extends ServiceException {
    private final int error_code = 40001;

    public EntityAlreadyExists(String entityName) {
        super(entityName);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public int getError_code() {
        return this.error_code;
    }
    //</editor-fold>
}
