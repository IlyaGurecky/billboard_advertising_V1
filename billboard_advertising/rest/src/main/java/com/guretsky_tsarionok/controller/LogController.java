package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.converter.UserLogsFileManager;
import com.guretsky_tsarionok.model.Log;
import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.service.LogService;
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

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/log")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogController {

    LogService service;
    UserLogsFileManager exporter;
    UserService userService;

    @GetMapping
    public List<Log> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Log getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public Log add(@RequestBody Log log) {
        return service.add(log);
    }

    @PostMapping(value = "/export/{userId}")
    public String exportLogs(@PathVariable long userId) throws IOException { //TODO:
        List<Log> userLogs = service.findByUserId(userId);
        User user = userService.findById(userId).get(); //TODO:
        return exporter.exportToFile(userLogs, user.getUsername());
    }

    @PatchMapping
    public Log update(@RequestBody Log log) {
        return service.update(log);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }
}
