package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * com.example.demo.ApplicationExceptionHandler
     */
    private static Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<?> exceptionHandler(UserNotFoundException ex, HttpServletRequest request) {

        log.trace("this trace");
        log.debug("this is debug");
        log.info("this is info");
        log.warn("this is warning");
        log.error("this is error");

        log.error("Developer's custom message", ex);

        System.out.println("blabla");
        System.err.println(ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
