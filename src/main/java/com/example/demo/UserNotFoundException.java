package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// @ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
