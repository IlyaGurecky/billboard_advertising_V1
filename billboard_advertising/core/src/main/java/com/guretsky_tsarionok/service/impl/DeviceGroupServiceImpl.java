package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.model.DeviceGroup;
import com.guretsky_tsarionok.repository.DeviceGroupRepository;
import com.guretsky_tsarionok.service.DeviceGroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeviceGroupServiceImpl implements DeviceGroupService {

    DeviceGroupRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<DeviceGroup> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceGroup> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public DeviceGroup add(DeviceGroup obj) {
        return repository.save(obj);
    }

    @Override
    public DeviceGroup update(DeviceGroup obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}
