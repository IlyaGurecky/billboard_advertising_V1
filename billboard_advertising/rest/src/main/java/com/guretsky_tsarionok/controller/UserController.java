package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/u")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<User> getUser() {
        return service.getAll();
    }
}
