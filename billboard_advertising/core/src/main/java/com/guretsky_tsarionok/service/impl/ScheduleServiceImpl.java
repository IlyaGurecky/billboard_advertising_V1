package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.converter.ScheduleFileManager;
import com.guretsky_tsarionok.dto.ScheduleDto;
import com.guretsky_tsarionok.logger.LoggingHelper;
import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.repository.ScheduleRepository;
import com.guretsky_tsarionok.repository.UserRepository;
import com.guretsky_tsarionok.service.ScheduleService;
import com.guretsky_tsarionok.validator.ImportedScheduleValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    ScheduleFileManager fileManager;
    LoggingHelper logger;

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
    @Transactional(readOnly = true)
    public List<Schedule> findByUserId(long userId) {
        return repository.findByUserId(userId);
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
        Schedule schedule = add(Schedule.builder()
                .name(dto.getName())
                .frequency(dto.getFrequency())
                .advertisingList(advertisingList)
                .user(userRepository.findById(userId).orElse(null))
                .build());
        logger.log("add schedule %s", userId, schedule.getName());
        return schedule;
    }

    @Override
    public String export(Long scheduleId) throws IOException {
        Schedule schedule = repository.getOne(scheduleId);
        String path = fileManager.exportSchedule(schedule);
        if (!Strings.isBlank(path)) {
            logger.log("export schedule %s", schedule.getUser().getId(), schedule.getName());
        }
        return path;
    }

    @Override
    public boolean importSchedule(String filePath, String username) {
        Schedule schedule = fileManager.importSchedule(filePath, username);
        if (ImportedScheduleValidator.validateSchedule(schedule, repository, userRepository, advertisingRepository)) {
            User user = userRepository.findByUsername(username).orElse(null);
            List<Advertising> advertising = schedule.getAdvertisingList().stream()
                    .map(ad -> advertisingRepository.findByNameAndUserId(ad.getName(), user.getId()))
                    .collect(Collectors.toList());
            schedule.setAdvertisingList(advertising);
            schedule.setUser(user);
            Schedule savedSchedule = repository.save(schedule);
            logger.log("import schedule %s", savedSchedule.getUser().getId(), savedSchedule.getName());
            return true;
        }
        return false;
    }

    @Override
    public Schedule update(Schedule obj) {
        return repository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        Schedule schedule = repository.getOne(id);
        repository.deleteById(id);
        if (repository.findById(id).isEmpty()) {
            logger.log("delete schedule %s", schedule.getUser().getId(), schedule.getName());
            return true;
        }
        return false;
    }
}
