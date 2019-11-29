package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
