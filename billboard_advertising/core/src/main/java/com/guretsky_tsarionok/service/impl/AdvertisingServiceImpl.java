package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.service.AdvertisingService;
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
public class AdvertisingServiceImpl implements AdvertisingService {

    AdvertisingRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Advertising> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Advertising> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Advertising add(Advertising obj) {
        return repository.save(obj);
    }

    @Override
    public Advertising update(Advertising obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}
