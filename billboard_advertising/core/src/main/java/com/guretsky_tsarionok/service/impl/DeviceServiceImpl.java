package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.dto.DeviceDto;
import com.guretsky_tsarionok.logger.LoggingHelper;
import com.guretsky_tsarionok.model.Device;
import com.guretsky_tsarionok.model.DeviceGroup;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.repository.DeviceGroupRepository;
import com.guretsky_tsarionok.repository.DeviceRepository;
import com.guretsky_tsarionok.repository.ScheduleRepository;
import com.guretsky_tsarionok.repository.UserRepository;
import com.guretsky_tsarionok.service.DeviceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository repository;
    UserRepository userRepository;
    AdvertisingRepository adRepository;
    ScheduleRepository scheduleRepository;
    DeviceGroupRepository deviceGroupRepository;
    LoggingHelper logger;

    @Override
    @Transactional(readOnly = true)
    public List<Device> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> getByGroupAndUserId(String name, long userId) {
        return repository.getByDeviceGroupNameAndUserId(name, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> getByUserId(long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Device save(DeviceDto dto, Long userId) {
        Device device = add(Device.builder()
                .name(dto.getName())
                .user(userRepository.findById(userId).orElse(null))
                .build());
        logger.log("add device %s", userId, device.getName());
        return device;
    }

    @Override
    public Device add(Device obj) {
        return repository.save(obj);
    }

    @Override
    public Device update(DeviceDto dto, Long id) {
        Optional<Device> deviceOpt = repository.findById(id);
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();
            if (nonNull(dto.getName())) {
                device.setName(dto.getName());
            }
            if (nonNull(dto.getScheduleId())) {
                device.setSchedule(scheduleRepository.getOne(dto.getScheduleId()));
            }
            if (nonNull(dto.getDeviceGroupId())) {
                device.setDeviceGroup(deviceGroupRepository.getOne(dto.getDeviceGroupId()));
            }
            if (nonNull(dto.getAdvIds()) && !dto.getAdvIds().isEmpty()) {
                device.setAdvertisingList(
                        dto.getAdvIds().stream()
                                .map(aLong -> adRepository.findById(aLong).orElse(null))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()));
            }
            return add(device);
        }
        return null;
    }

    @Override
    public Device update(Device obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        Device device = repository.getOne(id);
        logger.log("delete device %s", device.getUser().getId(), device.getName());
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }

    @Override
    public List<Device> setScheduleForGroup(long groupId, long scheduleId) {
        DeviceGroup group = deviceGroupRepository.getOne(groupId);
        Schedule schedule = scheduleRepository.getOne(scheduleId);
        if (nonNull(group)) {
            List<Device> devices = repository.getByDeviceGroupId(group.getId());
            if (nonNull(devices) && !devices.isEmpty()) {
                devices.forEach(device -> {
                    device.setSchedule(schedule);
                    repository.save(device);
                });
                logger.log("set schedule % for group %s", group.getUser().getId(), schedule.getName(), group.getName());
                return devices;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Device setScheduleForDevice(long scheduleId, long deviceId) {
        Device device = repository.getOne(deviceId);
        Schedule schedule = scheduleRepository.getOne(scheduleId);
        device.setSchedule(schedule);
        logger.log("set schedule %s for device: %s", device.getUser().getId(), schedule.getName(), device.getName());
        return repository.save(device);
    }

    @Override
    public List<Device> setGroup(long groupId, Long[] deviceIds) {
        DeviceGroup deviceGroup = deviceGroupRepository.getOne(groupId);
        long userId = deviceGroup.getUser().getId();
        List<Device> devices = Arrays.stream(deviceIds)
                .map(repository::getOne)
                .collect(Collectors.toList());
        String devicesNames = devices.stream()
                .map(Device::getName)
                .collect(Collectors.joining(","));
        logger.log("set group %s for devices: %s", userId, deviceGroup.getName(), devicesNames);
        return devices.stream()
                .peek(device -> device.setDeviceGroup(deviceGroup))
                .map(repository::save)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteDeviceFromGroup(long deviceId, long groupId) {
        Device device = repository.getOne(deviceId);
        DeviceGroup group = deviceGroupRepository.getOne(groupId);
        if (nonNull(device) && nonNull(group)) {
            if (device.getDeviceGroup().getName().equals(group.getName())) {
                device.setDeviceGroup(null);
                repository.save(device);
                logger.log("delete device %s from group %s", device.getUser().getId(), device.getName(), group.getName());
                return true;
            }
            return false;
        }
        return false;
    }
}
