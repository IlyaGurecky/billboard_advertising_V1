package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.AdvertisingStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdStatisticRepository extends JpaRepository<AdvertisingStatistic, Long> {
}
