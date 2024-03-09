package com.ianmarcos.flowingims.advice;

import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.util.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException exception) {
    ErrorResponse error = new ErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(Instant.now());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleUniqueConstraintException(DataIntegrityViolationException exception) {
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
  public ResponseEntity<ErrorResponse> handleAnyException(Exception exception) {

    ErrorResponse error = new ErrorResponse();

    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
