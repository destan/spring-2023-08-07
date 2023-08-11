package com.example.demo.report;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportController {

    @GetMapping("public")
    public ResponseEntity<String> publicReport() {
        return ResponseEntity.ok("PUBLIC report");
    }

    @GetMapping("admin")
    ResponseEntity<String> adminReport() {
        return ResponseEntity.ok("ADMIN report");
    }

    @GetMapping("user")
    ResponseEntity<String> userReport() {
        return ResponseEntity.ok("USER report");
    }

    @GetMapping("private")
    ResponseEntity<String> privateReport() {
        return ResponseEntity.ok("PRIVATE report");
    }

}
