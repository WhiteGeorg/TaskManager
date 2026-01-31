package com.example.TaskManager;


import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRecordDpo> internalErrorHandler(Exception e) {
        ErrorRecordDpo recordDpo = new ErrorRecordDpo(
                "Internal server error",
                e.getMessage(),
                LocalDateTime.now()
        );
        log.info("internalErrorHandler:{}", recordDpo);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(recordDpo);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorRecordDpo> notFoundHandler(Exception e) {
        ErrorRecordDpo recordDpo = new ErrorRecordDpo(
                "Not Found",
                e.getMessage(),
                LocalDateTime.now()
        );
        log.info("notFoundHandler:{}", recordDpo);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recordDpo);
    }

    @ExceptionHandler(exception = {
            IllegalStateException.class,
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorRecordDpo> badRequestHandler(Exception e) {
        ErrorRecordDpo recordDpo = new ErrorRecordDpo(
                "Bad Request",
                e.getMessage(),
                LocalDateTime.now()
        );
        log.info("notFoundHandler:{}", recordDpo);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(recordDpo);
    }
}
