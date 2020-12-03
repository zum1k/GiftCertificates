package com.epam.esm.exception;

public class EntityNotUpdatedException extends ServiceException {
    private final int errorCode = 40004;
    private final long entityId;

    public EntityNotUpdatedException(String entityName, long entityId) {
        super(entityName);
        this.entityId = entityId;
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
