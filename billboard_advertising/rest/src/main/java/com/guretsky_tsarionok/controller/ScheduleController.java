package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.dto.ScheduleDto;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.service.ScheduleService;
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
@RequestMapping("/api/v1/schedule")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleController {

    ScheduleService service;

    @GetMapping
    public List<Schedule> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Schedule getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping("/user/{userId}")
    public Schedule add(@RequestBody ScheduleDto schedule,
                        @PathVariable long userId) {
        return service.save(schedule, userId);
    }

    @PatchMapping
    public Schedule update(@RequestBody Schedule schedule) {
        return service.update(schedule);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }
}
