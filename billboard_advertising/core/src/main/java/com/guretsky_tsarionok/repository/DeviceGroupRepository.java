package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroup, Long> {
    List<DeviceGroup> getByUserId(long id);
}
