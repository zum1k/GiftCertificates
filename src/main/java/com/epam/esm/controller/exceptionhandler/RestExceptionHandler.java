package com.epam.esm.controller.exceptionhandler;

import com.epam.esm.exception.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e) {
        return buildNotFoundResponseEntity(e.getMessage());
    }

    @ExceptionHandler(EntityNotDeletedException.class)
    protected ResponseEntity<Object> handleEntityNotDeleted(EntityNotDeletedException e) {
        return buildInternalErrorResponseEntity(e.getMessage());
    }

    @ExceptionHandler(EntityNotUpdatedException.class)
    protected ResponseEntity<Object> handleEntityNotUpdated(EntityNotUpdatedException e) {
        return buildInternalErrorResponseEntity(e.getMessage());
    }

    @ExceptionHandler(EntityNotAddedException.class)
    protected ResponseEntity<Object> EntityNotCreated(EntityNotAddedException e) {
        return buildInternalErrorResponseEntity(e.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    protected ResponseEntity<Object> handleEntityAlreadyExists(EntityAlreadyExists e) {
        return buildBadRequestResponseEntity(e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        return buildResponseEntity(e.getMessage(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> messages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(o -> o.getField() + ": " + o.getDefaultMessage())
                .collect(Collectors.toList());


        return buildResponseEntity(messages, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        return buildResponseEntity(ex.getMessage(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        return buildResponseEntity(ex.getMessage(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        String message = ex.getContentType() + " media type is not supported";
        return buildResponseEntity(message, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String message = "Malformed JSON request";
        return buildResponseEntity(message, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Cannot find resource with URI " + ex.getRequestURL();
        return buildResponseEntity(message, status);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        return buildInternalErrorResponseEntity("Something got wrong");
    }

    private ResponseEntity<Object> buildResponseEntity(final String message, final HttpStatus status) {
        return new ResponseEntity<>(new ApiError(message), status);
    }

    private ResponseEntity<Object> buildResponseEntity(final List<String> messages, final HttpStatus status) {
        return new ResponseEntity<>(new ApiError(messages), status);
    }

    private ResponseEntity<Object> buildNotFoundResponseEntity(final String message) {
        return new ResponseEntity<>(new ApiError(message), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildBadRequestResponseEntity(final String message) {
        return new ResponseEntity<>(new ApiError(message), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildInternalErrorResponseEntity(final String message) {
        return new ResponseEntity<>(new ApiError(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class ApiError implements Serializable {
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private OffsetDateTime time = OffsetDateTime.now();

        private List<String> messages;

        private ApiError(final List<String> messages) {
            this.messages = messages;
        }

        private ApiError(final String message) {
            this.messages = Collections.singletonList(message);
        }

        public OffsetDateTime getTime() {
            return time;
        }

        public void setTime(OffsetDateTime time) {
            this.time = time;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }
    }
}
