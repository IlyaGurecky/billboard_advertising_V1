package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.dto.DeviceDto;
import com.guretsky_tsarionok.model.Device;
import com.guretsky_tsarionok.server.Server;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/device")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceController {

    DeviceService service;
    Server server;

    @GetMapping
    public List<Device> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Device getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping(value = "/group/{groupName}/user/{userId}")
    public List<Device> getByGroup(@PathVariable String groupName,
                                   @PathVariable long userId) {
        return service.getByGroupAndUserId(groupName, userId);
    }

    @GetMapping(value = "/user/{userId}")
    public List<Device> getByUserId(@PathVariable long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping(value = "/advertising/{deviceId}")
    public String getNextVideo(@PathVariable long deviceId) {
        return server.getNextAd(deviceId);
    }

    @PatchMapping(value = "/group/{groupId}")
    public List<Device> setGroupForDevices(@PathVariable long groupId,
                                           @RequestParam Long[] deviceIds) {
        return service.setGroup(groupId, deviceIds);
    }

    @PatchMapping(value = "/schedule/{scheduleId}/group/{groupId}/user")
    public List<Device> setScheduleForGroup(@PathVariable long scheduleId,
                                            @PathVariable long groupId) {
        server.setScheduleForGroup(groupId, scheduleId);
        return service.setScheduleForGroup(groupId, scheduleId);
    }

    @PatchMapping(value = "/schedule/{scheduleId}/device/{deviceId}")
    public Device setScheduleForDevice(@PathVariable long scheduleId,
                                       @PathVariable long deviceId) {
        server.setScheduleForDevice(deviceId, scheduleId);
        return service.setScheduleForDevice(scheduleId, deviceId);
    }

    @PostMapping("/user/{id}")
    public Device add(@RequestBody DeviceDto device,
                      @PathVariable long id) {
        return service.save(device, id);
    }

    @PatchMapping
    public Device update(@RequestBody Device device) {
        return service.update(device);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping("/count")
    public Long count() {
        return service.count();
    }

    @DeleteMapping("/delete/device/{deviceId}/group/{groupId}")
    public boolean deleteDeviceFromGroup(@PathVariable long deviceId,
                                         @PathVariable long groupId) {
        return service.deleteDeviceFromGroup(deviceId, groupId);
    }
}
