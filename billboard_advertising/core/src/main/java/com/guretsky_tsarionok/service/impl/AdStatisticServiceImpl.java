package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.model.AdvertisingStatistic;
import com.guretsky_tsarionok.repository.AdStatisticRepository;
import com.guretsky_tsarionok.service.AdStatisticService;
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
public class AdStatisticServiceImpl implements AdStatisticService {

    AdStatisticRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisingStatistic> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdvertisingStatistic> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public AdvertisingStatistic add(AdvertisingStatistic obj) {
        return repository.save(obj);
    }

    @Override
    public AdvertisingStatistic update(AdvertisingStatistic obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}
