package com.epam.esm.exception;

public class EntityNotFoundException extends ServiceException {
    private final int errorCode = 40404;
    private long entityId;

    public EntityNotFoundException(String entityName, long entityId) {
        super(entityName);
        this.entityId = entityId;
    }

//<editor-fold defaultstate="collapsed" desc="delombok">
//</editor-fold>
    public EntityNotFoundException(String entityName) {
        super(entityName);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public int getErrorCode() {
        return this.errorCode;
    }

    @SuppressWarnings("all")
    public long getEntityId() {
        return this.entityId;
    }
    //</editor-fold>
}
