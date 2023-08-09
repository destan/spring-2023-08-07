package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class DemoController {

    private final PostService postService;

    @ResponseBody
    @GetMapping("hello")
    String hello(@RequestParam(value = "name", required = false) Optional<String> name) {
        postService.search("", Pageable.unpaged());
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

        System.out.println(user.getUsername());
        // System.out.println(user.getProfile().getAge());

        if ("john".equals(user.getUsername())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @ResponseBody
    @PostMapping("sample")
    String sample(User user, HttpServletRequest request, HttpSession httpSession) {
        // httpSession.setAttribute("authenticated", true);
        return user.getUsername();
    }

    @ResponseBody
    @GetMapping("sample")
    String sampleGet(HttpServletRequest request, HttpSession httpSession) {
        Arrays.asList(request.getCookies()).forEach(cookie -> System.out.println(cookie.getName() + " = " + cookie.getValue()));
        return httpSession.getId();
    }

    @ResponseBody
    @PostMapping("hello/json")
    String helloJson(@RequestBody User user) {
        return "Hello world " + user.getUsername();
    }

}
