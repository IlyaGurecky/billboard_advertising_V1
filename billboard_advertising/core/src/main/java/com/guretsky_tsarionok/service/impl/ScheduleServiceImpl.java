package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.dto.ScheduleDto;
import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.repository.ScheduleRepository;
import com.guretsky_tsarionok.repository.UserRepository;
import com.guretsky_tsarionok.service.ScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository repository;
    AdvertisingRepository advertisingRepository;
    UserRepository userRepository;

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
    public Schedule save(ScheduleDto dto, Long userId) {
        List<Advertising> advertisingList = dto.getAdvIds()
                .stream()
                .map(aLong -> advertisingRepository.findById(aLong).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return add(Schedule.builder()
                .name(dto.getName())
                .frequency(dto.getFrequency())
                .advertisingList(advertisingList)
                .user(userRepository.findById(userId).orElse(null))
                .build());
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
