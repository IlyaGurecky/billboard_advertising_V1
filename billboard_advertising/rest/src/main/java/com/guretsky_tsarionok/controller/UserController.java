package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping(value = "/device-owners")
    public List<User> getAllDeviceOwners() {
        return service.getDeviceOwners();
    }

    @GetMapping(value = "/name/{username}")
    public User getByUsername(@PathVariable String username) {
        return service.findByUsername(username).orElse(null);
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return service.add(user);
    }

    @PatchMapping
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }
}
