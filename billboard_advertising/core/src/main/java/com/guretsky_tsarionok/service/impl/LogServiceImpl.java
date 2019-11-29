package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.model.Log;
import com.guretsky_tsarionok.repository.LogRepository;
import com.guretsky_tsarionok.service.LogService;
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
public class LogServiceImpl implements LogService {

    LogRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Log> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Log> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Log add(Log obj) {
        return repository.save(obj);
    }

    @Override
    public Log update(Log obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}
