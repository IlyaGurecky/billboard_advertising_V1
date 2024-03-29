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

import java.io.IOException;
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

    @GetMapping(value = "/user/{userId}")
    public List<Schedule> getByUserId(@PathVariable long userId) {
        return service.findByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public Schedule add(@RequestBody ScheduleDto schedule,
                        @PathVariable long userId) {
        return service.save(schedule, userId);
    }

    @PostMapping("/export/{scheduleId}")
    public String export(@PathVariable long scheduleId) throws IOException {
        return service.export(scheduleId);
    }

    @PostMapping("/import/{username}")
    public boolean importSchedule(@PathVariable String username,
                                  @RequestBody String filePath) {
        return service.importSchedule(filePath, username);
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
