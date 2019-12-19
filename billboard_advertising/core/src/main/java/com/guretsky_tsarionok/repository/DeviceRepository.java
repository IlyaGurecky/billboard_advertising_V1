package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> getByDeviceGroupNameAndUserId(String name, long id);
    List<Device> getByDeviceGroupId(long id);
    List<Device> findByUserId(long userId);
}
