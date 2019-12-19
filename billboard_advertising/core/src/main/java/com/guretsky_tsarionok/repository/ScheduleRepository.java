package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByNameAndUserId(String name, long userId);
}
