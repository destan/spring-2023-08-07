package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
class TagController {

    private final TagService tagService;

    @PostMapping
    ResponseEntity<Tag> create(@RequestBody Tag tag) {
        try {
            tagService.create(tag);
        } catch (DataIntegrityViolationException e) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();
            throw e;
        }
        return ResponseEntity.ok(tag);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> exceptionHandler(DataIntegrityViolationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
