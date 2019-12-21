package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.DeviceDto;
import com.guretsky_tsarionok.model.Device;

import java.util.List;

public interface DeviceService extends CrudService<Device> {
    Device save(DeviceDto dto, Long userId);
    long count();
    List<Device> getByGroupAndUserId(String name, long userId);
    List<Device> setScheduleForGroup(long groupId, long scheduleId);
    Device setScheduleForDevice(long scheduleId, long deviceId);
    List<Device> getByUserId(long userId);
    List<Device> setGroup(long groupId, Long[] deviceIds);
    boolean deleteDeviceFromGroup(long deviceId, long groupId);
}
