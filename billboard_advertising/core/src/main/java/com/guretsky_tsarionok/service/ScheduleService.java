package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.ScheduleDto;
import com.guretsky_tsarionok.model.Schedule;

public interface ScheduleService extends CrudService<Schedule> {
    Schedule save(ScheduleDto dto, Long userId);
}
