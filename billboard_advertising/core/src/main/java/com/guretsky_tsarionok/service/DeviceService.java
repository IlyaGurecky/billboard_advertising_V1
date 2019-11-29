package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.Device;
import com.guretsky_tsarionok.repository.DeviceRepository;
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
public class DeviceService implements IService<Device> {

    DeviceRepository repository;

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
    public Device add(Device obj) {
        return repository.save(obj);
    }

    @Override
    public Device update(Device obj) {
        return repository.save(obj);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
