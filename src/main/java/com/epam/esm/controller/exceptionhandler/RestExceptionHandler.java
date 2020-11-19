package com.epam.esm.controller.exceptionhandler;

import com.epam.esm.exception.EntityAlreadyExists;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messages;

    @ExceptionHandler(EntityAlreadyExists.class)
    protected ResponseEntity<Object> handleEntityAlreadyExists(EntityAlreadyExists exception, WebRequest request) {
        String message = messages.getMessage("exception.entity-already-exists", new Object[]{}, request.getLocale());
        ErrorResponse errorResponse = new ErrorResponse(message, exception.getError_code());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotAdded(EntityNotFoundException exception, WebRequest request) {
        String message = messages.getMessage("exception.entity-not-added", new Object[]{}, request.getLocale());
        ErrorResponse errorResponse = new ErrorResponse(message, exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotDeleted(EntityNotFoundException exception, WebRequest request) {
        String message = messages.getMessage("exception.entity-not-deleted", new Object[]{}, request.getLocale());
        ErrorResponse errorResponse = new ErrorResponse(message, exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception, WebRequest request) {
        String message = messages.getMessage("exception.entity-not-found", new Object[]{}, request.getLocale());
        ErrorResponse errorResponse = new ErrorResponse(message, exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotUpdated(EntityNotFoundException exception, WebRequest request) {
        String message = messages.getMessage("exception.entity-not-updated", new Object[]{}, request.getLocale());
        ErrorResponse errorResponse = new ErrorResponse(message, exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
        String message = messages.getMessage("Something wrong", new Object[]{},
                request.getLocale());
        ErrorResponse response = new ErrorResponse(message, 50001);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
