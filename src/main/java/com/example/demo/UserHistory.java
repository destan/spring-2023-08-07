package com.example.demo;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserHistory {

    private LocalDateTime lastVisit;

    public UserHistory() {
        System.out.println("UserHistory Constructor");
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public UserHistory setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }
}
