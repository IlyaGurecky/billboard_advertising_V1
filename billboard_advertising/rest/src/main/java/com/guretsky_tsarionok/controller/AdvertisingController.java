package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.dto.AdvertisingDto;
import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.service.AdvertisingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/advertising")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdvertisingController {

    AdvertisingService service;

    @GetMapping
    public List<Advertising> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Advertising getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping(value = "/user/{id}")
    public List<Advertising> getAllByUserId(@PathVariable long id) {
        return service.getByUserId(id);
    }

    @PostMapping("/user/{id}")
    public Advertising add(@RequestBody AdvertisingDto advertising,
                           @PathVariable long id) {
        return service.save(advertising, id);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }
}
