package com.epam.ahnl.handler;

import com.epam.ahnl.exception.ResourceNotFoundException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String INTERNAL_ERROR_MESSAGE = "Server error has occurred.";

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
    log.error(e.getMessage(),e);
    return new ResponseEntity<>(
        new APIError(INTERNAL_ERROR_MESSAGE), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
    return new ResponseEntity<>(
        new APIError(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
    return new ResponseEntity<>(
        new APIError(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

}
