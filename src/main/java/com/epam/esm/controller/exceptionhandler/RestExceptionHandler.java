package com.epam.esm.controller.exceptionhandler;

import com.epam.esm.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler(EntityAlreadyExists.class)
  protected ResponseEntity<Object> handleEntityAlreadyExists(
      EntityAlreadyExists exception, WebRequest request) {
    String message =
        messageSource.getMessage(
            "exception.entity-already-exists", new Object[]{}, request.getLocale());
    ErrorResponse errorResponse =
        new ErrorResponse(exception.getMessage() + " " + message, exception.getError_code());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotAddedException.class)
  protected ResponseEntity<Object> handleEntityNotAdded(
      EntityNotAddedException exception, WebRequest request) {
    String message =
        messageSource.getMessage(
            "exception.entity-not-added", new Object[]{}, request.getLocale());
    ErrorResponse errorResponse =
        new ErrorResponse(exception.getMessage() + " " + message, exception.getErrorCode());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotDeletedException.class)
  protected ResponseEntity<Object> handleEntityNotDeleted(
      EntityNotDeletedException exception, WebRequest request) {
    String message =
        messageSource.getMessage(
            "exception.entity-not-deleted", new Object[]{}, request.getLocale());
    ErrorResponse errorResponse =
        new ErrorResponse(
            exception.getMessage() + " " + exception.getEntityId() + " " + message,
            exception.getErrorCode());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      EntityNotFoundException exception, WebRequest request) {
    String message =
        messageSource.getMessage(
            "exception.entity-not-found", new Object[]{}, request.getLocale());
    ErrorResponse errorResponse =
        new ErrorResponse(
            exception.getMessage() + " " + exception.getEntityId() + " " + message,
            exception.getErrorCode());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityNotUpdatedException.class)
  protected ResponseEntity<Object> handleEntityNotUpdated(
      EntityNotUpdatedException exception, WebRequest request) {
    String message =
        messageSource.getMessage(
            "exception.entity-not-updated", new Object[]{}, request.getLocale());
    ErrorResponse errorResponse =
        new ErrorResponse(
            exception.getMessage() + " " + exception.getEntityId() + " " + message,
            exception.getErrorCode());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handleRuntimeException(
      RuntimeException exception, WebRequest request) {
    String message =
        messageSource.getMessage("exception.something-wrong", new Object[]{}, request.getLocale());
    ErrorResponse response = new ErrorResponse(message, 50001);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
    List<String> messages =
        e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    ErrorResponse response = new ErrorResponse(String.join(", " +
        "", messages), 40001);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
