package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.model.DeviceGroup;
import com.guretsky_tsarionok.service.DeviceGroupService;
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
@RequestMapping("/api/v1/device_group")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceGroupController {

    DeviceGroupService service;

    @GetMapping
    public List<DeviceGroup> getDeviceGroups() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public DeviceGroup getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public DeviceGroup add(@RequestBody DeviceGroup deviceGroup) {
        return service.add(deviceGroup);
    }

    @PatchMapping
    public DeviceGroup update(@RequestBody DeviceGroup deviceGroup) {
        return service.update(deviceGroup);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }

}
