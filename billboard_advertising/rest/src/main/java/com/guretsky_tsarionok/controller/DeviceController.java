package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.dto.DeviceDto;
import com.guretsky_tsarionok.model.Device;
import com.guretsky_tsarionok.service.DeviceService;
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
@RequestMapping("/api/v1/device")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceController {

    DeviceService service;

    @GetMapping
    public List<Device> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Device getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @PatchMapping(value = "/schedule/{scheduleId}/group/{groupId}/user")
    public List<Device> setScheduleForGroup(@PathVariable long scheduleId,
                                            @PathVariable long groupId) {
        return service.setScheduleForGroup(groupId, scheduleId);
    }

    @PostMapping("/user/{id}")
    public Device add(@RequestBody DeviceDto device,
                      @PathVariable long id) {
        return service.save(device, id);
    }

    @PatchMapping(value = "/{id}")
    public Device update(@RequestBody DeviceDto device,
                         @PathVariable Long id) {
        return service.update(device, id);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping("/count")
    public Long count() {
        return service.count();
    }
}
