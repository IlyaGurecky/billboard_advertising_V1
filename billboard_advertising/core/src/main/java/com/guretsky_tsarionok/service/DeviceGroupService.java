package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.DeviceGroupDto;
import com.guretsky_tsarionok.model.DeviceGroup;

import java.util.List;

public interface DeviceGroupService extends CrudService<DeviceGroup> {
    DeviceGroup save(DeviceGroupDto dto, Long userId);
    List<DeviceGroup> getGroupsByUserId(Long id);
}
