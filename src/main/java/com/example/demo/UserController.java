package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class UserController {

    private final UserService usersService;

    private final UserHistory userHistory;

    public UserController(UserService usersService, UserHistory userHistory) {
        this.usersService = usersService;
        this.userHistory = userHistory;
    }

    @ResponseBody
    @GetMapping("user")
    List<User> list() {

        userHistory.setLastVisit(LocalDateTime.now());

        return usersService.listAll();
    }

    @ResponseBody
    @PostMapping("user")
    void create(@RequestBody User user, @RequestHeader("visit-time") String visitTimeStr) {

        // String visitTimeStr = request.getHeader("visit-time");

        if (visitTimeStr != null) {
            Instant instant = Instant.ofEpochMilli(Long.parseLong(visitTimeStr));
            userHistory.setLastVisit(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
        }
        else {
            userHistory.setLastVisit(LocalDateTime.now());
        }


        usersService.register(user);
    }

    @ResponseBody
    @GetMapping("user/last-visit")
    LocalDateTime lastVisit() {
        return userHistory.getLastVisit();
    }

}
