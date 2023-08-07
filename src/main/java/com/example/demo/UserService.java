package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    List<User> users = Collections.synchronizedList(new ArrayList<>());

    User register(User user) {
        users.add(user);
        return user;
    }

    List<User> listAll() {

        return users;
    }

}
