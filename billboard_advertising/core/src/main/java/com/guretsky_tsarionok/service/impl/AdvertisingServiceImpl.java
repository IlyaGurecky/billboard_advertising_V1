package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.dto.AdvertisingDto;
import com.guretsky_tsarionok.logger.LoggingHelper;
import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.repository.UserRepository;
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
    UserRepository userRepository;
    LoggingHelper logger;

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
    public Advertising save(AdvertisingDto dto, Long userId) {
        Advertising advertising = Advertising.builder()
                .name(dto.getName())
                .contentPath(dto.getContentPath())
                .user(userRepository.findById(userId).orElse(null))
                .build();
        logger.log("add advertising %s", userId, advertising.getName());
        return add(advertising);
    }

    @Override
    public Advertising update(Advertising obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        Advertising advertising = repository.getOne(id);
        long userId = advertising.getUser().getId();
        logger.log("delete advertising %s", userId, advertising.getName());
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertising> getByUserId(long id) {
        return repository.getByUserId(id);
    }
}
