package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.ScheduleDto;
import com.guretsky_tsarionok.model.Schedule;

import java.io.IOException;
import java.util.List;

public interface ScheduleService extends CrudService<Schedule> {
    Schedule save(ScheduleDto dto, Long userId);
    String export(Long scheduleId) throws IOException;
    boolean importSchedule(String filePath, String username);
    List<Schedule> findByUserId(long userId);
}
