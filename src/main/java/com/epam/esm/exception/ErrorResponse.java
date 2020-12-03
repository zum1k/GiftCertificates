package com.epam.esm.exception;

public class ErrorResponse {
    private String errorMessage;
    private int errorCode;

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @SuppressWarnings("all")
    public int getErrorCode() {
        return this.errorCode;
    }

    @SuppressWarnings("all")
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @SuppressWarnings("all")
    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ErrorResponse)) return false;
        final ErrorResponse other = (ErrorResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getErrorCode() != other.getErrorCode()) return false;
        final Object this$errorMessage = this.getErrorMessage();
        final Object other$errorMessage = other.getErrorMessage();
        if (this$errorMessage == null ? other$errorMessage != null : !this$errorMessage.equals(other$errorMessage)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResponse;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getErrorCode();
        final Object $errorMessage = this.getErrorMessage();
        result = result * PRIME + ($errorMessage == null ? 43 : $errorMessage.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "ErrorResponse(errorMessage=" + this.getErrorMessage() + ", errorCode=" + this.getErrorCode() + ")";
    }

    @SuppressWarnings("all")
    public ErrorResponse(final String errorMessage, final int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    //</editor-fold>
}
