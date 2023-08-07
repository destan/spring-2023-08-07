package com.example.demo;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
public class DemoController {

    @ResponseBody
    @GetMapping("hello")
    String hello(@RequestParam(value = "name", required = false) Optional<String> name) {
        return "Hello world " + name;
    }

    @ResponseBody
    @GetMapping("hellos")
    String hellos(@RequestParam(value = "names") Set<String> names) {
        return "Hello world " + names;
    }

    @ResponseBody
    @GetMapping("demo/{name}/age/{age}")
    String demo(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "Hello world " + name + " " + age;
    }

    //@ResponseStatus(code = HttpStatus.ACCEPTED)
    @ResponseBody
    @PostMapping("hello")
    ResponseEntity<User> helloPost(User user) {

        System.out.println(user.username());
        System.out.println(user.age());

        if ("john".equals(user.username())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @ResponseBody
    @PostMapping("sample")
    String sample(User user, HttpServletRequest request, HttpSession httpSession) {
        // httpSession.setAttribute("authenticated", true);
        return user.username();
    }

    @ResponseBody
    @GetMapping("sample")
    String sampleGet(HttpServletRequest request, HttpSession httpSession) {
        Arrays.asList(request.getCookies())
                .forEach(cookie -> System.out.println(cookie.getName() + " = " + cookie.getValue()));
        return httpSession.getId();
    }

    @ResponseBody
    @PostMapping("hello/json")
    String helloJson(@RequestBody User user) {
        return "Hello world " + user.username();
    }

}
