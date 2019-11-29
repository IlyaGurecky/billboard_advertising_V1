package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.repository.ScheduleRepository;
import com.guretsky_tsarionok.service.ScheduleService;
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
public class ScheduleServiceImpl implements ScheduleService {
    ScheduleRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Schedule> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Schedule add(Schedule obj) {
        return repository.save(obj);
    }

    @Override
    public Schedule update(Schedule obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}
