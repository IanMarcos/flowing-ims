package com.ianmarcos.flowingims.advice;

import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.util.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Set;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException exception) {
    ErrorResponse error = new ErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(Instant.now());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<ErrorResponse> handleUniqueConstraint(DataIntegrityViolationException exception) {
    String errorMessage = exception.getMessage();
    ErrorResponse error = new ErrorResponse();

    error.setStatus(HttpStatus.CONFLICT.value());
    error.setMessage(errorMessage);
    error.setTimeStamp(Instant.now());

    if (errorMessage.contains("duplicate key")) {
      int keyIdx = errorMessage.indexOf("Key") + 4;
      int alreadyIdx = errorMessage.indexOf("already exist") - 1;
      error.setMessage("Invalid duplicate entry: " + errorMessage.substring(keyIdx, alreadyIdx));
    }

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception) {
    Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
    ErrorResponse error = new ErrorResponse();
    StringBuilder errorMessage = new StringBuilder("Badly formatted fields:");

    for(ConstraintViolation<?> violation : violations) {
      errorMessage.append(" ")
          .append(violation.getPropertyPath()).append(" ")
          .append(violation.getMessage()).append(".");
    }

    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(errorMessage.toString());
    error.setTimeStamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
    Object[] messageArguments = exception.getDetailMessageArguments();
    ErrorResponse error = new ErrorResponse();
    StringBuilder errorMessage = new StringBuilder("Badly formatted fields:");

    assert messageArguments != null;
    for (Object arg: messageArguments) {
      if (!arg.toString().isEmpty()) {
        errorMessage.append(" ").append(arg);
      }
    }

    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(errorMessage.toString());
    error.setTimeStamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  //JpaObjectRetrievalFailureException
  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleAnyException(JpaObjectRetrievalFailureException exception) {

    ErrorResponse error = new ErrorResponse();
    String errorMessage = exception.getMessage().replace("com.ianmarcos.flowingims.entity.", "");

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(errorMessage);
    error.setTimeStamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse> handleAnyException(Exception exception) {

    ErrorResponse error = new ErrorResponse();

    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
