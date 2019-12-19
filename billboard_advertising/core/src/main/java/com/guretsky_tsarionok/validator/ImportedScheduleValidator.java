package com.guretsky_tsarionok.validator;

import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.repository.AdvertisingRepository;
import com.guretsky_tsarionok.repository.ScheduleRepository;
import com.guretsky_tsarionok.repository.UserRepository;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ImportedScheduleValidator {
    public boolean validateSchedule(Schedule schedule,
                                    ScheduleRepository repository,
                                    UserRepository userRepository,
                                    AdvertisingRepository adRepository) {
        User user = userRepository.findByUsername(schedule.getUser().getUsername()).get();

        if (!checkScheduleName(schedule, repository, user)) return false;
        return checkAd(schedule.getAdvertisingList(), adRepository, user);
    }

    private boolean checkScheduleName(Schedule schedule, ScheduleRepository repository, User user) {
        return repository.findByNameAndUserId(schedule.getName(), user.getId()) == null;
    }

    private boolean checkAd(List<Advertising> ads, AdvertisingRepository repository, User user) {
        return ads.stream().noneMatch(advertising -> repository.findByNameAndUserId(advertising.getName(), user.getId()) == null);

    }
}
